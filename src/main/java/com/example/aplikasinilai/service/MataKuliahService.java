package com.example.aplikasinilai.service;

import com.example.aplikasinilai.model.Mahasiswa;
import com.example.aplikasinilai.model.MataKuliah;
import com.example.aplikasinilai.model.MataKuliahMahasiswa;
import com.example.aplikasinilai.repository.MataKuliahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MataKuliahService {

    @Autowired
    private MataKuliahRepository mataKuliahRepository;

    // Create MataKuliah
    public MataKuliah createMataKuliah(MataKuliah mataKuliah) {
        return mataKuliahRepository.save(mataKuliah);
    }

    // Get All MataKuliah
    public List<MataKuliah> getAllMataKuliah() {
        return mataKuliahRepository.findAll();
    }

    // Get MataKuliah By ID
    public MataKuliah getMataKuliahById(Long id) {
        return mataKuliahRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mata Kuliah tidak ditemukan dengan ID: " + id));
    }


    public MataKuliah getMataKuliahWithDetails(Long id) {
        MataKuliah mataKuliah = mataKuliahRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MataKuliah not found with ID: " + id));

        // Mencari semua Mahasiswa yang terhubung dengan MataKuliah ini
        List<Mahasiswa> mahasiswaList = mataKuliah.getMataKuliahMahasiswa().stream()
                .map(MataKuliahMahasiswa::getMahasiswa)
                .collect(Collectors.toList());

        // Menyimpan daftar mahasiswa ke MataKuliah
        mataKuliah.setMahasiswa(mahasiswaList);

        return mataKuliah;
    }

    // Update MataKuliah
    public MataKuliah updateMataKuliah(Long id, MataKuliah updatedMataKuliah) {
        MataKuliah mataKuliah = getMataKuliahById(id);
        mataKuliah.setNama(updatedMataKuliah.getNama());
        mataKuliah.setKode(updatedMataKuliah.getKode());
        mataKuliah.setSks(updatedMataKuliah.getSks());
        return mataKuliahRepository.save(mataKuliah);
    }

    // Delete MataKuliah
    public void deleteMataKuliah(Long id) {
        mataKuliahRepository.deleteById(id);
    }
}
