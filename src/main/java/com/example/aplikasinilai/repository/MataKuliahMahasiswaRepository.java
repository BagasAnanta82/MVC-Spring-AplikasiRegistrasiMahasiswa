package com.example.aplikasinilai.repository;

import com.example.aplikasinilai.model.Mahasiswa;
import com.example.aplikasinilai.model.MataKuliah;
import com.example.aplikasinilai.model.MataKuliahMahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MataKuliahMahasiswaRepository extends JpaRepository<MataKuliahMahasiswa, Long> {

    Optional<MataKuliahMahasiswa> findByMahasiswaIdAndMataKuliahId(Long mahasiswaId, Long mataKuliahId);
}
