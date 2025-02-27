package com.example.Pro_Cac_Demo.Repository;

import com.example.Pro_Cac_Demo.Entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Projects,Long> {
    Optional<Projects> findByName(String projectName);
}
