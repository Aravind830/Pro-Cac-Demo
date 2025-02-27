//package com.example.Pro_Cac_Demo.Config;
//
//import com.example.Pro_Cac_Demo.Entity.Employee;
//import com.example.Pro_Cac_Demo.Service.EmployeeService;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.util.List;
//
//
//@Configuration
//@EnableCaching
//public class CacheConfig {
//
//    @Autowired
//    CacheManager cacheManager;
//
//    @Autowired
//    EmployeeService employeeService;
//
//    @PostConstruct
//    public void preCache(){
//        Cache cache=cacheManager.getCache("applicationCache");
//        List<Employee> employeeList=employeeService.getAll();
//
//        for (Employee employee:employeeList){
//            cache.put(employee.getEmployeeId(),employee);
//        }
//        System.out.println("Cache Caught SuccessFully..!!");
//    }
//
////    @Scheduled(fixedRate = 60000)
////    public void clearCache(){
////        cacheManager.getCacheNames().parallelStream().forEach(
////                name->cacheManager.getCache(name).clear()
////        );
////        System.out.println("Cache Cleared..!!!");
////    }
//}
