package com.example.aplikasinilai.controller;

import com.example.aplikasinilai.model.MataKuliahMahasiswa;
import com.example.aplikasinilai.repository.MataKuliahMahasiswaRepository;
import com.example.aplikasinilai.service.MataKuliahMahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matakuliahmahasiswa")
public class MataKuliahMahasiswaController {

    @Autowired
    private MataKuliahMahasiswaService service;

    // Assign Mahasiswa to MataKuliah
    @PostMapping("/assign")
    public ResponseEntity<MataKuliahMahasiswa> assignMataKuliahToMahasiswa(
            @RequestParam Long mahasiswaId,
            @RequestParam Long mataKuliahId) {
        System.out.println("Assigning Mahasiswa ID: " + mahasiswaId + " to MataKuliah ID: " + mataKuliahId);
        MataKuliahMahasiswa assigned = service.assignMataKuliahToMahasiswa(mahasiswaId, mataKuliahId);
        return ResponseEntity.ok(assigned);
    }

    // Delete Relationship by Mahasiswa ID and MataKuliah ID
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteMataKuliahMahasiswaByParams(
            @RequestParam Long mahasiswaId,
            @RequestParam Long mataKuliahId) {
        service.deleteMataKuliahMahasiswaByParams(mahasiswaId, mataKuliahId);
        return ResponseEntity.noContent().build();
    }


    // Get Detailed MataKuliahMahasiswa by ID
    @GetMapping("/{id}")
    public ResponseEntity<MataKuliahMahasiswa> getMataKuliahMahasiswaById(@PathVariable Long id) {
        MataKuliahMahasiswa mataKuliahMahasiswa = service.getById(id);
        return ResponseEntity.ok(mataKuliahMahasiswa);
    }

    @Autowired
    private MataKuliahMahasiswaRepository mataKuliahMahasiswaRepository;

    @PostMapping("/update-nilai")
    public ResponseEntity<?> updateNilai(@RequestParam Long id, @RequestParam int uts, @RequestParam int uas, @RequestParam int kuis) {
        MataKuliahMahasiswa mkMahasiswa = mataKuliahMahasiswaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data not found"));

        mkMahasiswa.setUts(uts);
        mkMahasiswa.setUas(uas);
        mkMahasiswa.setKuis(kuis);

        // Perhitungan otomatis total dan grade dipanggil
        mataKuliahMahasiswaRepository.save(mkMahasiswa);
        return ResponseEntity.ok("Nilai berhasil diperbarui.");
    }
}
