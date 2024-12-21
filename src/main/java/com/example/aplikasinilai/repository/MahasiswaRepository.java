package com.example.aplikasinilai.repository;

import com.example.aplikasinilai.model.Mahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {
    // Cari mahasiswa berdasarkan username
    Optional<Mahasiswa> findByNim(String nim);
    Optional<Mahasiswa> findByUsername(String username);
}
