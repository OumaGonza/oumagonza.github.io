package com.houser.devtrac.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.houser.devtrac.model.IssueStatus;
@Repository
public interface IssueStatusRepository extends JpaRepository<IssueStatus, Long> {
}