package com.houser.devtrac.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.houser.devtrac.model.Issue;
import com.houser.devtrac.service.IssueService;
import com.houser.devtrac.service.ProjectService;
@Controller
@RequestMapping("/issues")
public class IssuesController {
@Autowired
private IssueService issueService;
@Autowired
private ProjectService projectService;
@GetMapping("/list")
public String listBlankIssues(Model model) {
model.addAttribute("listProjects", projectService.getAllProjects());
model.addAttribute("listIssues" , issueService.getAllIssues());
return "issueslist";
}
@GetMapping("/list/{projectid}")
public String listProjectIssues(@PathVariable(value = "projectid") long projectid,
Model model) {
model.addAttribute("listProjects", projectService.getAllProjects());
model.addAttribute("listIssues" ,
issueService.getIssuesByProjectID(projectid));
model.addAttribute("ProjectDets" , projectService.getProjectById(projectid));
model.addAttribute("CurrProjectID", projectid);
return "issues";
}
@GetMapping("/list/{projectid}/{issid}")
public String listProjectIssues2(@PathVariable(value = "projectid") long projectid,
@PathVariable(value = "issid") long issid, Model model) {

model.addAttribute("listProjects", projectService.getAllProjects());
model.addAttribute("listIssues" ,
issueService.getIssuesByProjectID(projectid));
model.addAttribute("issueDetails", issueService.getIssueById(issid));
model.addAttribute("ProjectDets" , projectService.getProjectById(projectid));
model.addAttribute("CurrProjectID", projectid);
return "issues";
}
@GetMapping("/list/{projectid}/{id}")
public String showIssueDetails(@PathVariable(value = "projectid") long projectid,
@PathVariable(value = "id") long id, Model model) {
model.addAttribute("listProjects", projectService.getAllProjects());
model.addAttribute("listIssues" , issueService.getAllIssues());
model.addAttribute("CurrProjectID", projectid);
return "issuesdetails";
}
@GetMapping("/NewIssue/{projectid}")
public String showNewIssueForm(@PathVariable(value = "projectid") long projectid,
Model model) {
Issue issue = new Issue();
issue.setProjectID(projectid);
model.addAttribute("issue", issue);
model.addAttribute("ProjectDets" , projectService.getProjectById(projectid));
model.addAttribute("CurrProjectID", projectid);
return "issues/newissue";
}
@PostMapping("/saveissue/{projectid}")
public String saveProjectIssue(@PathVariable(value = "projectid") String projectid,
@ModelAttribute("issue") Issue issue) {
issueService.saveIssue(issue); // save Issue to database
return "redirect:/issues/list/" + projectid;
}
}