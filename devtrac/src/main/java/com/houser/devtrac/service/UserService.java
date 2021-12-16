package com.houser.devtrac.service;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.houser.devtrac.model.Role;
import com.houser.devtrac.model.User;
public interface UserService extends UserDetailsService{
List < User > getAllUsers();
void saveUser(User user);
User save(User registrationDto);
User getUserById(long id);
void deleteUserById(long id);
List<Role> listRoles();
}