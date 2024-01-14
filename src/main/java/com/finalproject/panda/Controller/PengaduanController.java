package com.finalproject.panda.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.finalproject.panda.Service.PengaduanService;
import com.finalproject.panda.model.Pengaduan;
import com.finalproject.panda.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/panda")
public class PengaduanController {

    private static final Logger log = LoggerFactory.getLogger(PengaduanController.class);

    @Autowired
    private PengaduanService pengaduanService;

    @GetMapping("/pengaduan")
    public String formPengaduan(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            log.info(loggedInUser.getNama_lengkap() + " disini");
            model.addAttribute("pengaduan", new Pengaduan());
            model.addAttribute("loggedInUser", loggedInUser);
            return "User/PengaduanPage";
        } else {
            return "redirect:/panda/login";
        }
    }

    @PostMapping("/pengaduan")
    public String savePengaduan(Model model, HttpSession session, @ModelAttribute Pengaduan pengaduan) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            pengaduanService.savePengaduan(pengaduan, loggedInUser);
            model.addAttribute("pengaduan", pengaduan);
        }
        return "redirect:/panda/riwayat";
    }

    @GetMapping("/delete")
    public String deletePengaduan(@RequestParam("id_register") Integer id_registrasi) {
        pengaduanService.deletePengaduan(id_registrasi);
        return "redirect:/panda/riwayat"; 
    }
   
}
