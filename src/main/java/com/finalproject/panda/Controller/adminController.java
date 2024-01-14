package com.finalproject.panda.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finalproject.panda.Service.PengaduanService;
// import com.finalproject.panda.Service.StatusService;
import com.finalproject.panda.Service.UserService;
import com.finalproject.panda.model.Pengaduan;
// import com.finalproject.panda.model.Status;
import com.finalproject.panda.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/panda")
public class AdminController {

    @Autowired
    private PengaduanService pengaduanService;

    @Autowired
    private UserService userService;

    // @Autowired
    // private StatusService statusService;
    @GetMapping("/admin/dashboard")
    public String admin(Model model, HttpSession session) {
        List<Pengaduan> pengaduan = pengaduanService.getAllPengaduan();
        List<User> user = userService.getAll();
        // List<Status> status = statusService.getAll();
        long jumlahPengaduan = pengaduanService.jumlahPengaduan();
        long jumlahPengaduanBulanIni = pengaduanService.jumlahPengaduanBulanIni();
        long jumlahUser = userService.jumlahUser();
        User loggedInUser = (User)session.getAttribute("loggedInUser");

        if(loggedInUser != null){

            model.addAttribute("user", user);
            // model.addAttribute("status", status);
            model.addAttribute("pengaduan", pengaduan);
            model.addAttribute("jumlahPengaduan", jumlahPengaduan);
            model.addAttribute("jumlahPengaduanBulanIni", jumlahPengaduanBulanIni);
            model.addAttribute("jumlahUser", jumlahUser);
            return "admin/dasboard";
        }else{
            return "redirect:/panda/login";
        }
    }

    @GetMapping("/admin/delete/{id_register}")
    public String deleteByAdmin(@PathVariable("id_register") Integer id_registrasi) {
        pengaduanService.deletePengaduan(id_registrasi);
        return "redirect:/panda/admin/dashboard";
    }

    @GetMapping("/admin/logoutt")
    public String logout(HttpServletRequest request) {
        if (request.getParameter("logout") != null) {
            request.getSession().invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/admin/profileAdmin")
    public String profile(Model model, HttpSession session, User user) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        try {
            if (loggedInUser != null) {

                byte[] fotoData = loggedInUser.getFoto();

                Path fotoPath = Paths.get("src/main/resources/static/imgEncode/photo.jpg");

                Files.write(fotoPath, fotoData);

                model.addAttribute("fotoPath", fotoPath.toString());

                model.addAttribute("user", loggedInUser);

                return "Admin/profileAdmin";
            } else {
                return "redirect:/panda/login";
            }
        } catch (Exception e) {
        }

        return "redirect:/panda/login";
    }

}
