package dev.wcs.nad.tariffmanager.adapter.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/public/")
public class IndexView {

    @GetMapping("/index")
    public String list(Model model) {
        model.addAttribute("now", new Date().toInstant());
        return "indexview";
    }

}
