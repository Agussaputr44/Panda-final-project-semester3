package com.finalproject.panda.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finalproject.panda.Service.PengaduanService;
import com.finalproject.panda.Service.UserService;
import com.finalproject.panda.model.Pengaduan;
import com.finalproject.panda.model.User;

@Controller
@RequestMapping("/panda")
public class AdminController {

    @Autowired
    private PengaduanService pengaduanService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/admin/dashboard")
    public String admin (Model model){
        List <Pengaduan> pengaduan = pengaduanService.getAllPengaduan();
        List <User> user = userService.getAll();
        long jumlahPengaduan = pengaduanService.jumlahPengaduan();
        long jumlahPengaduanBulanIni = pengaduanService.jumlahPengaduanBulanIni();
        long jumlahUser = userService.jumlahUser();
        model.addAttribute("user", user);
        model.addAttribute("pengaduan", pengaduan);
        model.addAttribute("jumlahPengaduan", jumlahPengaduan);
        model.addAttribute("jumlahPengaduanBulanIni", jumlahPengaduanBulanIni);
        model.addAttribute("jumlahUser", jumlahUser);
        return "admin/dasboard";
    }

     @GetMapping("/admin/delete/{id_register}")
    public String deleteByAdmin(@PathVariable("id_register") Integer id_registrasi) {
        pengaduanService.deletePengaduan(id_registrasi);
        return "redirect:/panda/admin/dashboard"; 
    }
}
