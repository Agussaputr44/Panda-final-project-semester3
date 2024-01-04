package com.finalproject.panda.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/panda")
public class adminController {
   

    @GetMapping("/admin")
    public String admin (){
        return "admin/KelolaPengaduan";
    }
  
}
