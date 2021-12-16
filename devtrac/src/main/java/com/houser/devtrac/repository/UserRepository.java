package com.houser.devtrac.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.houser.devtrac.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
User findByUserLogon(String username);
}
