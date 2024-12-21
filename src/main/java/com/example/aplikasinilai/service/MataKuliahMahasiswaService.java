package com.example.aplikasinilai.service;

import com.example.aplikasinilai.model.Mahasiswa;
import com.example.aplikasinilai.model.MataKuliah;
import com.example.aplikasinilai.model.MataKuliahMahasiswa;
import com.example.aplikasinilai.repository.MahasiswaRepository;
import com.example.aplikasinilai.repository.MataKuliahMahasiswaRepository;
import com.example.aplikasinilai.repository.MataKuliahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MataKuliahMahasiswaService {

    @Autowired
    private MataKuliahMahasiswaRepository mataKuliahMahasiswaRepository;

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @Autowired
    private MataKuliahRepository mataKuliahRepository;

    // Assign Mahasiswa to MataKuliah
    public MataKuliahMahasiswa assignMataKuliahToMahasiswa(Long mahasiswaId, Long mataKuliahId) {
        Mahasiswa mahasiswa = mahasiswaRepository.findById(mahasiswaId)
                .orElseThrow(() -> new RuntimeException("Mahasiswa not found with ID: " + mahasiswaId));

        MataKuliah mataKuliah = mataKuliahRepository.findById(mataKuliahId)
                .orElseThrow(() -> new RuntimeException("MataKuliah not found with ID: " + mataKuliahId));

        MataKuliahMahasiswa mataKuliahMahasiswa = new MataKuliahMahasiswa();
        mataKuliahMahasiswa.setMahasiswa(mahasiswa);
        mataKuliahMahasiswa.setMataKuliah(mataKuliah);

        return mataKuliahMahasiswaRepository.save(mataKuliahMahasiswa);
    }

    // Delete Relationship
    public void deleteMataKuliahMahasiswaByParams(Long mahasiswaId, Long mataKuliahId) {
        MataKuliahMahasiswa relation = mataKuliahMahasiswaRepository.findByMahasiswaIdAndMataKuliahId(mahasiswaId, mataKuliahId)
                .orElseThrow(() -> new RuntimeException("Relasi tidak ditemukan untuk Mahasiswa ID: " + mahasiswaId + " dan MataKuliah ID: " + mataKuliahId));
        mataKuliahMahasiswaRepository.delete(relation);
    }


    // Get MataKuliahMahasiswa by ID
    public MataKuliahMahasiswa getById(Long id) {
        return mataKuliahMahasiswaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MataKuliahMahasiswa not found with ID: " + id));
    }
}
