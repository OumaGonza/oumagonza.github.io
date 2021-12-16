package com.houser.devtrac.service;
import java.util.List;
import com.houser.devtrac.model.Role;
public interface RoleService {
List < Role > getAllRoles();
void saveRole(Role role);
Role getRoleById(long id);
void deleteRoleById(long id);
}