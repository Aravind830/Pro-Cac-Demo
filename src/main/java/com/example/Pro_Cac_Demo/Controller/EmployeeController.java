package com.example.Pro_Cac_Demo.Controller;

import com.example.Pro_Cac_Demo.Dto.RequestDto;
import com.example.Pro_Cac_Demo.Entity.Employee;
import com.example.Pro_Cac_Demo.Entity.Projects;
import com.example.Pro_Cac_Demo.Repository.EmployeeRepository;
import com.example.Pro_Cac_Demo.Repository.ProjectRepository;
import com.example.Pro_Cac_Demo.Security.JwtUtil;
import com.example.Pro_Cac_Demo.Service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final ProjectRepository projectRepository;

    @Autowired
    private final JwtUtil jwtUtil;

    private final AuthenticationProvider authenticationProvider;
    private final PasswordEncoder passwordEncoder;

    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository, JwtUtil jwtUtil, AuthenticationProvider authenticationProvider, PasswordEncoder passwordEncoder,ProjectRepository projectRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationProvider = authenticationProvider;
        this.passwordEncoder = passwordEncoder;
        this.projectRepository=projectRepository;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("get/{employeeId}")
    public ResponseEntity<Employee> getById(@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.getById(employeeId));
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> create(@RequestBody Employee employee, HttpSession session){
        session.setAttribute("username","user");
        System.out.println(session.getId());
        return ResponseEntity.ok(employeeService.create(employee));
    }

    @GetMapping("/clearAllCache")
    public ResponseEntity<String> clearAll(){
        employeeService.clearAllCache();
        return ResponseEntity.ok("Cache Cleared");
    }

    @GetMapping("/clearCacheById/{employeeId}")
    public ResponseEntity<String> clearById(@PathVariable Long employeeId){
        employeeService.clearCacheById(employeeId);
        return ResponseEntity.ok("EmployeeId : "+employeeId + " Cache Cleared..!!");
    }

    @GetMapping("/csrf")
    public CsrfToken getToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerEmployee(@RequestBody RequestDto requestDto){
        if (employeeRepository.getByName(requestDto.getEmployeeName()).isPresent()){
            return ResponseEntity.badRequest().body(requestDto.getEmployeeName() + " Already Exists.");
        }
        Employee employee=new Employee();
        employee.setEmployeeName(requestDto.getEmployeeName());
        employee.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        List<Projects> projects=requestDto.getProjectsList();
        for (Projects projects1:projects){
            Projects project=projectRepository.findByName(projects1.getProjectName())
                    .orElseThrow(()->new RuntimeException(projects1.getProjectName() +" Not found."));
            projects.add(project);
        }
        employee.setProjects(projects);
        return ResponseEntity.ok("Registered SuccessFully..!");
    }
}
