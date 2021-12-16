package com.houser.devtrac.service;
import java.util.List;
import com.houser.devtrac.model.Issue;
public interface IssueService {
List < Issue > getAllIssues();
void saveIssue(Issue issue);
Issue getIssueById(long id);
void deleteIssueById(long id);
List<Issue> getIssuesByProjectID(long projectID);
}