package com.houser.devtrac.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.houser.devtrac.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}