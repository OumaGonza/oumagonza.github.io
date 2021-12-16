package com.houser.devtrac.service;
import java.util.List;
import com.houser.devtrac.model.Issue;
import com.houser.devtrac.model.IssueStatus;
public interface IssueStatusService {
List < Issue > getAllIssues();
void saveIssue(Issue issue);
Issue getIssueById(long id);
void deleteIssueById(long id);
void deleteIssueStatusById(long id);
IssueStatus getIssueStatusById(long id);
void saveIssueStatus(IssueStatus issueStatus);
List<IssueStatus> getAllIssueStatus();
}