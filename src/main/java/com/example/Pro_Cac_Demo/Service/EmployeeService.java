package com.example.Pro_Cac_Demo.Service;

import com.example.Pro_Cac_Demo.Entity.Employee;
import com.example.Pro_Cac_Demo.Repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
@Transactional
public class EmployeeService implements UserDetailsService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository ) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }


    @Cacheable(cacheNames = "employeeCache",key = "#employeeId")
    public Employee getById(Long employeeId){
            return employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new RuntimeException(employeeId + " Not Found..!!"));

    }

    public Employee create(Employee employee){
        return employeeRepository.save(employee);
    }

    @CacheEvict(cacheNames = "employeeCache",allEntries = true)
    public void clearAllCache(){
        System.out.println("All Caches Cleared..!!!");
    }

    @CacheEvict(cacheNames = "employeeCache",key = "#employeeId")
    public void clearCacheById(Long employeeId){
        Optional<Employee> employee=employeeRepository.findById(employeeId);
        if (employee.isEmpty() ){
            throw new RuntimeException("EmployeeID : " + employeeId + " Not Found..!");
        }
        System.out.println(employeeId + " Caches Cleared..!!!");
    }

    @Override//Authorize a Employee to Project
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee=employeeRepository.getByName(username)
                .orElseThrow(()->new UsernameNotFoundException("Employee Not Found "+ username));
        return new org.springframework.security.core.userdetails.User(employee.getEmployeeName(),employee.getPassword(),
                employee.getProjects().stream().map(projects->new SimpleGrantedAuthority(projects.getProjectName())).collect(Collectors.toList()));
    }
}
