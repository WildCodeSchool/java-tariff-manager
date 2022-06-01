package dev.wcs.nad.tariffmanager.adapter.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public/admin")
public class AdminView {


    @GetMapping("/view")
    public String editview(Model model) {
        return "admin";
    }

}
