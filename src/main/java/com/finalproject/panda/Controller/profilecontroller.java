package com.finalproject.panda.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finalproject.panda.Service.PengaduanService;
import com.finalproject.panda.Service.UserService;
import com.finalproject.panda.model.Pengaduan;
import com.finalproject.panda.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/panda")
public class profilecontroller {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private PengaduanService pengaduanService;

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session, User user) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        try {
            if (loggedInUser != null) {
                log.info(loggedInUser.getNama_lengkap() + " disini");
                model.addAttribute("user", loggedInUser);
                session.setAttribute("loggedInUser", loggedInUser);

                return "User/ProfileUser"; 
            } else {
                log.info("User is null. Redirecting to login page.");
                return "redirect:/panda/login";
            }
        } catch (Exception e) {
            // Handle exceptions if needed
            log.error("Error in profile controller", e);
        }

        return "redirect:/panda/login";
    }


    

    @GetMapping("/riwayat")
    public String riwayat(Model model, HttpSession session) {
        try {
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            if (loggedInUser != null) {
                log.info("Logged in user: " + loggedInUser.getNama_lengkap());
                List<Pengaduan> pengaduanList = pengaduanService.getPengaduanByNik(loggedInUser.getNik());

                model.addAttribute("user", loggedInUser);
                model.addAttribute("pengaduan", pengaduanList);

                return "User/RiwayatPengaduan";
            } else {
                log.info("User is null. Redirecting to login page.");
                return "redirect:/panda/login";
            }
        } catch (Exception e) {
            log.error("Error in riwayat method", e);
            return "redirect:/panda/login";
        }
    }
    
    @GetMapping("/editProfile")
    public String editProfile(){
        return "user/editProfile";
    }

}
