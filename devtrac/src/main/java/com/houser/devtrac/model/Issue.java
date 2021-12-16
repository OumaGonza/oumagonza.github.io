package com.houser.devtrac.model;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "issues")
public class Issue {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
@Column(name = "issuetitle")
private String issueTitle;
@Column(name = "description")
private String description;
@Column(name = "projectid", columnDefinition = "integer default 0")
private long projectID;

@Column(name = "design")
private String design;
@Column(name = "datereported")
private Date dateReported;
@Column(name = "dateclosed")
private Date dateClosed;
@Column(name = "issuestatus", columnDefinition = "integer default 0")
private int issueStatus;
@Column(name = "developer", columnDefinition = "integer default 0")
private int developer;
@Column(name = "qa", columnDefinition = "integer default 0")
private int qa;
@Column(name = "issuetype", columnDefinition = "integer default 0")
private int issueType;
@Column(name = "issuepriority", columnDefinition = "integer default 0")
private int issuePriority;
@Column(name = "hourstaken")
private double hoursTaken;
@Column(name = "estimatedtime")
private double estimatedTime;
public Issue() {
}
public Issue(String issueTitle, String description, String design, Date dateReported,
Date dateClosed, int issueStatus, int developer,
int qa, int issueType, int issuePriority, double hoursTaken, double
estimatedTime, long projectID) {
super();
this.issueTitle = issueTitle;
this.description = description;
this.design = design;
this.dateReported = dateReported;
this.dateClosed = dateClosed;
this.issueStatus = issueStatus;
this.developer = developer;
this.qa = qa;
this.issueType = issueType;
this.issuePriority = issuePriority;
this.hoursTaken = hoursTaken;
this.estimatedTime = estimatedTime;
this.projectID = projectID;

}
public long getId() {
return id;
}
public void setId(long id) {
this.id = id;
}
public String getIssueTitle() {
return issueTitle;
}
public void setIssueTitle(String issueTitle) {
this.issueTitle = issueTitle;
}
public String getDescription() {
return description;
}
public void setDescription(String description) {
this.description = description;
}
public String getDesign() {
return design;
}
public void setDesign(String design) {
this.design = design;
}
public Date getDateReported() {
return dateReported;
}
public void setDateReported(Date dateReported) {
this.dateReported = dateReported;
}
public Date getDateClosed() {
return dateClosed;
}
public void setDateClosed(Date dateClosed) {
this.dateClosed = dateClosed;
}
public int getIssueStatus() {

return issueStatus;
}
public void setIssueStatus(int issueStatus) {
this.issueStatus = issueStatus;
}
public int getDeveloper() {
return developer;
}
public void setDeveloper(int developer) {
this.developer = developer;
}
public int getQa() {
return qa;
}
public void setQa(int qa) {
this.qa = qa;
}
public int getIssueType() {
return issueType;
}
public void setIssueType(int issueType) {
this.issueType = issueType;
}
public int getIssuePriority() {
return issuePriority;
}
public void setIssuePriority(int issuePriority) {
this.issuePriority = issuePriority;
}
public double getHoursTaken() {
return hoursTaken;
}
public void setHoursTaken(double hoursTaken) {
this.hoursTaken = hoursTaken;
}
public double getEstimatedTime() {
return estimatedTime;
}

public void setEstimatedTime(double estimatedTime) {
this.estimatedTime = estimatedTime;
}
public long getProjectID() {
return projectID;
}
public void setProjectID(long projectid2) {
this.projectID = projectid2;
}
}