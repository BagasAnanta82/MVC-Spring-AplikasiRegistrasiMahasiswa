package com.example.aplikasinilai.repository;

import com.example.aplikasinilai.model.Pegawai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PegawaiRepository extends JpaRepository<Pegawai, Long> {
    // Cari pegawai berdasarkan username
    Optional<Pegawai> findByUsername(String username);
}
