package dev.wcs.nad.tariffmanager.identity.user;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.wcs.nad.tariffmanager.identity.entity.User;
import dev.wcs.nad.tariffmanager.identity.helpers.JwtUtils;

@Controller
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    public AuthController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Map<String, ?> doAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUser(auth.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<String> rolesNames = user.securityUser().getAuthorities().stream().map(authority -> authority.getAuthority()).toList();
        String token = jwtUtils.generateToken(user.getUsername(),rolesNames);
        return Map.of("token", token);
    }
    @GetMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Map<String, ?> testAuth() {
        return Map.of("success", true);
    }
}
