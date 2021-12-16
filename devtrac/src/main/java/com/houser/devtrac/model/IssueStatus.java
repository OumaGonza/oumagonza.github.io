package com.houser.devtrac.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name = "issuestatus", uniqueConstraints = @UniqueConstraint(columnNames =
"statusname"))
public class IssueStatus {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
@Column(name = "statusname")
private String statusName;
public long getId() {
return id;
}
public void setId(long id) {

this.id = id;
}
public String getStatusName() {
return statusName;
}
public void setStatusName(String statusName) {
this.statusName = statusName;
}
}