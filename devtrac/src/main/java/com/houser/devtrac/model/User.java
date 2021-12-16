package com.houser.devtrac.model;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames =
"userlogon"))
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(name = "firstname")
private String firstName;

@Column(name = "lastname")
private String lastName;
@Column(name = "userpassword")
private String userPassword;
@Column(name = "userlogon", unique = true)
private String userLogon;
@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
@JoinTable(
name = "usersroles",
joinColumns = @JoinColumn(
name = "userID", referencedColumnName = "id"),
inverseJoinColumns = @JoinColumn(
name = "roleID", referencedColumnName = "id"))
private Collection<Role> roles;
public User() {
}
public User(String firstName, String lastName, String email, String userpassword,
Collection<Role> roles) {
super();
this.firstName = firstName;
this.lastName = lastName;
this.userLogon = email;
this.userPassword = userpassword;
this.roles = roles;
}
public void addRole(Role role) {
this.roles.add(role);
}
public Long getId() {
return id;
}
public void setId(Long id) {
this.id = id;
}
public String getFirstName() {
return firstName;
}
public void setFirstName(String firstName) {
this.firstName = firstName;
}
public String getLastName() {
return lastName;
}

public void setLastName(String lastName) {
this.lastName = lastName;
}
public String getUserLogon() {
return userLogon;
}
public void setUserLogon(String email) {
this.userLogon = email;
}
public String getUserPassword() {
return userPassword;
}
public void setUserPassword(String password) {
this.userPassword = password;
}
public Collection<Role> getRoles() {
return roles;
}
public void setRoles(Collection<Role> roles) {
this.roles = roles;
}
}