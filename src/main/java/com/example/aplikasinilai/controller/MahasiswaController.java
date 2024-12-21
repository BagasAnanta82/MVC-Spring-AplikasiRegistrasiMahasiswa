package com.example.aplikasinilai.controller;

import com.example.aplikasinilai.model.Mahasiswa;
import com.example.aplikasinilai.model.MataKuliah;
import com.example.aplikasinilai.model.User;
import com.example.aplikasinilai.repository.UserRepository;
import com.example.aplikasinilai.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mahasiswa")
public class MahasiswaController {

    @Autowired
    private MahasiswaService mahasiswaService;
    private UserRepository userRepository;

    // Create Mahasiswa
    @PostMapping
    public ResponseEntity<Mahasiswa> createMahasiswa(@RequestBody Mahasiswa mahasiswa) {
        return ResponseEntity.ok(mahasiswaService.createMahasiswa(mahasiswa));
    }

    // Get All Mahasiswa
    @GetMapping
    public ResponseEntity<List<Mahasiswa>> getAllMahasiswa() {
        return ResponseEntity.ok(mahasiswaService.getAllMahasiswa());
    }

    // Get Mahasiswa By ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getMahasiswaById(@PathVariable Long id) {
        if (id == 0) {
            return ResponseEntity.badRequest().body("ID Mahasiswa tidak valid.");
        }
        Mahasiswa mahasiswa = mahasiswaService.getMahasiswaWithDetails(id);
        return ResponseEntity.ok(mahasiswa);
    }

    // Update Mahasiswa
    @PutMapping("/{id}")
    public ResponseEntity<Mahasiswa> updateMahasiswa(@PathVariable Long id, @RequestBody Mahasiswa mahasiswa) {
        return ResponseEntity.ok(mahasiswaService.updateMahasiswa(id, mahasiswa));
    }

    // Delete Mahasiswa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMahasiswa(@PathVariable Long id) {
        mahasiswaService.deleteMahasiswa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nim/{nim}/matakuliah")
    public ResponseEntity<List<MataKuliah>> getMataKuliahByNim(@PathVariable String nim) {
        List<MataKuliah> mataKuliahList = mahasiswaService.getMataKuliahByNim(nim);
        return ResponseEntity.ok(mataKuliahList);
    }

    @GetMapping("/api/mahasiswa/current")
    public ResponseEntity<?> getCurrentMahasiswa(Authentication auth) {
        String username = auth.getName();
        System.out.println("DEBUG: Logged-in username = " + username);

        // Cari user berdasarkan username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        // Casting ke Mahasiswa
        if (user instanceof Mahasiswa) {
            Mahasiswa mahasiswa = (Mahasiswa) user;
            System.out.println("DEBUG: Mahasiswa ID = " + mahasiswa.getId());
            return ResponseEntity.ok(Map.of("mahasiswaId", mahasiswa.getId()));
        } else {
            throw new RuntimeException("Logged-in user is not a Mahasiswa");
        }
    }
}
