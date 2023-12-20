package com.finalproject.panda.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalproject.panda.Service.UserService;
import com.finalproject.panda.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/panda")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "LoginPage";
    }

    @PostMapping("/login")
    public String loginSuccess(
            @RequestParam String nik,
            @RequestParam String password,
            Model model, HttpSession session) {
        User user = userService.checkLogin(nik, password);
        if (user != null) {
            model.addAttribute("loginError", "false");
            model.addAttribute("user", user);
            session.setAttribute("loggedInUser", user);
            return "redirect:/panda/pengaduan";
        } else {
            model.addAttribute("loginError", "true");
            return "redirect:/panda/login";
        }
    }

    @GetMapping("/daftar")
    public String daftarPage(Model model) {
        try {
            User user = new User();
            model.addAttribute("user", user);
        } catch (Exception e) {
            // Handle exceptions
        }
        return "DaftarPage";
    }

    @PostMapping("/daftar")
    public String saveUser(Model model, User users) {
        try {
            userService.saveUser(users);
            model.addAttribute("user", users);
        } catch (Exception e) {
            // Handle exceptions
        }
        return "redirect:/panda/login";
    }
}
