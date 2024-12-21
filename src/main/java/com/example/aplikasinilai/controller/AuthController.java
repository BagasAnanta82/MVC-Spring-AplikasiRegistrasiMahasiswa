package com.example.aplikasinilai.controller;

import com.example.aplikasinilai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test-user")
    public ResponseEntity<?> testUser(@RequestParam String username) {
        return userRepository.findByUsername(username)
                .map(user -> ResponseEntity.ok("Found: " + user.getUsername() + ", Role: " + user.getRole()))
                .orElse(ResponseEntity.status(404).body("User not found"));
    }

    @GetMapping("/dashboard")
    public String redirectDashboard(Authentication auth) {
        String role = auth.getAuthorities().iterator().next().getAuthority();

        if (role.equals("ROLE_MAHASISWA")) {
            return "redirect:/dashboard_mahasiswa";
        } else if (role.equals("ROLE_PEGAWAI")) {
            return "redirect:/dashboard_pegawai";
        }

        return "redirect:/login.html";
    }
}
