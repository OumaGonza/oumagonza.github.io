package com.houser.devtrac.service;
import java.util.List;
import com.houser.devtrac.model.Project;
public interface ProjectService {
List < Project > getAllProjects();
void saveProject(Project project);
Project getProjectById(long id);
void deleteProjectById(long id);
}