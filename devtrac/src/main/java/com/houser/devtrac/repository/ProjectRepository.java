package com.houser.devtrac.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.houser.devtrac.model.Project;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}