package com.houser.devtrac.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.houser.devtrac.model.Project;
import com.houser.devtrac.service.ProjectService;
@Controller
@RequestMapping("/projects")
public class ProjectsController {
@Autowired
private ProjectService projectService;
// display list of projects
@GetMapping("/list")
public String viewHomePage(Model model) {
model.addAttribute("listProjects", projectService.getAllProjects());
return "projects";
}
@GetMapping("/NewProject")
public String showNewProjectForm(Model model) {
Project project = new Project(); // create model attribute to bind form data
model.addAttribute("project", project);
return "projectsadd";
}
@PostMapping("/saveProject")
public String saveProject(@ModelAttribute("project") Project project) {
projectService.saveProject(project); // save project to database
return "redirect:/projects/list"; //"projects";//
}
@GetMapping("/ProjectUpdate/{id}")

public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
Project project = projectService.getProjectById(id); // get project from the service
model.addAttribute("project", project); // set project as a model attribute to pre-populate the form
return "projectsupdate";
}
@GetMapping("/deleteProject/{id}")
public String deleteProject(@PathVariable(value = "id") long id) {
this.projectService.deleteProjectById(id); // call delete project method
return "redirect:/projects/list";
}
}