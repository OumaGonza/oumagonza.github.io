package com.houser.devtrac.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.houser.devtrac.model.Issue;
@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

	List<Issue> findByProjectID(long projectID);
}