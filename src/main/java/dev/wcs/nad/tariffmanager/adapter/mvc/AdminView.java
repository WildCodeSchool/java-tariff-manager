package dev.wcs.nad.tariffmanager.adapter.mvc;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.wcs.nad.tariffmanager.identity.entity.User;
import dev.wcs.nad.tariffmanager.identity.user.UserService;

@Controller
@RequestMapping("/public/admin")
public class AdminView {

    @Autowired
    UserService userService;

    @GetMapping("")
    public String editview(Model model, @RequestParam(required = false) Optional<String> role) {

        List<User> users = userService.findUsers(role);
        model.addAttribute("users", users);

        return "admin";
    }

}
