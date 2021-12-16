package com.houser.devtrac.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.houser.devtrac.model.Role;
import com.houser.devtrac.repository.RoleRepository;
@Service
public class RoleServiceImpl implements RoleService{
@Autowired
private RoleRepository roleRepository;
@Override
public List<Role> getAllRoles() {
return roleRepository.findAll();
}
@Override
public void saveRole(Role role) {
this.roleRepository.save(role);
}
@Override
public Role getRoleById(long id) {
return roleRepository.getById(id);
}
@Override
public void deleteRoleById(long id) {
roleRepository.deleteById(id);
}
}