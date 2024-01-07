package com.finalproject.panda.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/panda")
public class adminController {

    @Autowired

    @GetMapping("/admin")
    public String admin (){
        return "admin/dasboard";
    }
    @GetMapping ("/kelola")
    public String kelolaPengaduan (){
        return "admin/kelolaPengaduan";
    }
  
}
