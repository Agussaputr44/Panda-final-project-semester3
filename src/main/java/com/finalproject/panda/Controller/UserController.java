package com.finalproject.panda.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.finalproject.panda.Service.UserService;
import com.finalproject.panda.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/panda")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @GetMapping("/login")
    public String login(Model model) {
        try {
            User user = new User();
            model.addAttribute("user", user);
        } catch (Exception e) {
        }
        return "User/LoginNew";
    }

    @PostMapping("/login")
    public String loginSuccess(
            @RequestParam String nik,
            @RequestParam String password,
            Model model, HttpSession session) {
        User user = userService.checkLogin(nik, password);
        if (user != null) {

            if (user.getNik().equals("0000")) {

                model.addAttribute("user", user);
                session.setAttribute("loggedInUser", user);

                return "redirect:/panda/admin/dashboard";
            } else {
                model.addAttribute("user", user);
                session.setAttribute("loggedInUser", user);
                return "redirect:/panda/pengaduan";
            }
        } else {
            model.addAttribute("loginError", "true");
            return "redirect:/panda/login";
        }
    }

    @PostMapping("/update")
    public String saveUser(@ModelAttribute("user") User updatedUser,
            @RequestParam("fotoFile") MultipartFile fotoFile,
            Model model, HttpSession session) {
        try {
            User currentUser = (User) session.getAttribute("loggedInUser");

            if (currentUser != null) {
                currentUser.setNama_lengkap(updatedUser.getNama_lengkap());
                currentUser.setAlamat(updatedUser.getAlamat());
                currentUser.setNomor_hp(updatedUser.getNomor_hp());

                if (fotoFile != null && !fotoFile.isEmpty()) {
                    byte[] fotoBytes = fotoFile.getBytes();
                    currentUser.setFoto(fotoBytes);
                }

                userService.saveUser(currentUser);

                model.addAttribute("user", currentUser);
                log.info(currentUser.getNama_lengkap() + " berhasil update profile");

                model.addAttribute("successMessage", "Profile updated successfully");

                if (currentUser.getNik().equals("0000")) {
                    return "redirect:/panda/admin/dashboard";
                }
                return "redirect:/panda/profile";
            } else {
                model.addAttribute("errorMessage", "User not found in the session");
                return "redirect:/panda/error"; // Redirect to an error page
            }

        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while updating the profile");
            e.printStackTrace();
            return "redirect:/panda/error"; // Redirect to an error page
        }
    }

    @GetMapping("/daftar")
    public String daftarPage(Model model) {
        return "User/login";
    }

    @PostMapping("/daftar")
    public String saveUser(Model model, @RequestPart("fotoFile") MultipartFile fotoFile, User users) {
        try {
            if (fotoFile != null && !fotoFile.isEmpty()) {
                byte[] fotoBytes = fotoFile.getBytes();
                users.setFoto(fotoBytes);
            }

            userService.saveUser(users);
            log.info(users.getNama_lengkap() + " berhasil mendaftar");

            model.addAttribute("user", users);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/panda/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        if (request.getParameter("logout") != null) {
            request.getSession().invalidate();
        }
        return "redirect:/";
    }
}
