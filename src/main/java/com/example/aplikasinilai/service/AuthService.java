package com.example.aplikasinilai.service;

import com.example.aplikasinilai.model.User;
import com.example.aplikasinilai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User authenticate(String username, String password) {
        // Cari user berdasarkan username
        User user = userRepository.findByUsername(username).orElse(null);

        // Verifikasi password
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        // Return null jika autentikasi gagal
        return null;
    }
}
