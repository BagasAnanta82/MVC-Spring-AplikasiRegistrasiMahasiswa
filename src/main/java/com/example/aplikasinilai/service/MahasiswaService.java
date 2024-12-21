package com.example.aplikasinilai.service;

import com.example.aplikasinilai.model.Mahasiswa;
import com.example.aplikasinilai.model.MataKuliah;
import com.example.aplikasinilai.model.MataKuliahMahasiswa;
import com.example.aplikasinilai.repository.MahasiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MahasiswaService {

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    // Create Mahasiswa
    public Mahasiswa createMahasiswa(Mahasiswa mahasiswa) {
        return mahasiswaRepository.save(mahasiswa);
    }

    // Get All Mahasiswa
    public List<Mahasiswa> getAllMahasiswa() {
        return mahasiswaRepository.findAll();
    }

    public List<MataKuliah> getMataKuliahByNim(String nim) {
        Mahasiswa mahasiswa = mahasiswaRepository.findByNim(nim)
                .orElseThrow(() -> new RuntimeException("Mahasiswa tidak ditemukan dengan NIM: " + nim));

        // Ambil daftar MataKuliah melalui relasi MataKuliahMahasiswa
        return mahasiswa.getMataKuliahMahasiswa().stream()
                .map(MataKuliahMahasiswa::getMataKuliah)
                .collect(Collectors.toList());
    }


    // Get Mahasiswa By ID
    public Mahasiswa getMahasiswaById(Long id) {
        return mahasiswaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mahasiswa tidak ditemukan dengan ID: " + id));
    }

    public Mahasiswa getMahasiswaWithDetails(Long id) {
        Mahasiswa mahasiswa = mahasiswaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mahasiswa not found with ID: " + id));

        // Map MataKuliahMahasiswa untuk menambahkan data nilai ke MataKuliah
        List<MataKuliah> mataKuliahList = mahasiswa.getMataKuliahMahasiswa().stream()
                .map(mkm -> {
                    MataKuliah mk = mkm.getMataKuliah(); // Ambil data MataKuliah
                    // Set properti tambahan dari MataKuliahMahasiswa
                    mk.setUts(mkm.getUts());
                    mk.setUas(mkm.getUas());
                    mk.setKuis(mkm.getKuis());
                    mk.setTotal(mkm.getTotal());
                    mk.setGrade(mkm.getGrade());
                    return mk;
                }).collect(Collectors.toList());

        mahasiswa.setMataKuliah(mataKuliahList);

        return mahasiswa;
    }


    // Update Mahasiswa
    public Mahasiswa updateMahasiswa(Long id, Mahasiswa updatedMahasiswa) {
        Mahasiswa mahasiswa = getMahasiswaById(id);
        mahasiswa.setUsername(updatedMahasiswa.getUsername());
        mahasiswa.setPassword(updatedMahasiswa.getPassword());
        mahasiswa.setNim(updatedMahasiswa.getNim());
        mahasiswa.setProdi(updatedMahasiswa.getProdi());
        return mahasiswaRepository.save(mahasiswa);
    }

    // Delete Mahasiswa
    public void deleteMahasiswa(Long id) {
        mahasiswaRepository.deleteById(id);
    }
}
