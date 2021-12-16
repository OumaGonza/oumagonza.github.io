package com.houser.devtrac.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name = "projects", uniqueConstraints = @UniqueConstraint(columnNames =
"projecttitle"))
public class Project {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
@Column(name = "projecttitle")
private String projectTitle;
public long getId() {
return id;
}
public void setId(long id) {
this.id = id;
}
public String getProjectTitle() {
return projectTitle;
}
public void setProjectTitle(String projectTitle) {
this.projectTitle = projectTitle;
}
}