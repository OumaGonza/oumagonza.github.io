package com.houser.devtrac.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.houser.devtrac.model.IssueStatus;
import com.houser.devtrac.repository.IssueStatusRepository;
public class IssueStatusServiceImpl implements IssueStatusService {
@Autowired
private IssueStatusRepository issueStatusRepository;
@Override
public List < IssueStatus > getAllIssueStatus() {
return issueStatusRepository.findAll();
}
@Override
public void saveIssueStatus(IssueStatus issueStatus) {
this.issueStatusRepository.save(issueStatus);
}
@Override
public IssueStatus getIssueStatusById(long id) {
Optional < IssueStatus > optional = issueStatusRepository.findById(id);
IssueStatus issue = null;
if (optional.isPresent()) {
issue = optional.get();
} else {
throw new RuntimeException(" IssueStatus not found for id :: " + id);
}
return issue;
}
@Override
public void deleteIssueStatusById(long id) {
this.issueStatusRepository.deleteById(id);
}
}