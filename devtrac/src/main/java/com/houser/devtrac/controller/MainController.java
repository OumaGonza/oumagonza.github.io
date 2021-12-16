package com.houser.devtrac.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class MainController {
@GetMapping("/login")
public String login() {
return "login";
}
@GetMapping("/")
public String home() {
return "index";
}
}