package com.example.aplikasinilai.controller;

import com.example.aplikasinilai.model.MataKuliah;
import com.example.aplikasinilai.service.MataKuliahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matakuliah")
public class MataKuliahController {

    @Autowired
    private MataKuliahService mataKuliahService;

    // Create Mata Kuliah
    @PostMapping
    public ResponseEntity<MataKuliah> createMataKuliah(@RequestBody MataKuliah mataKuliah) {
        return ResponseEntity.ok(mataKuliahService.createMataKuliah(mataKuliah));
    }

    // Get All Mata Kuliah
    @GetMapping
    public ResponseEntity<List<MataKuliah>> getAllMataKuliah() {
        List<MataKuliah> mataKuliahList = mataKuliahService.getAllMataKuliah();
        System.out.println("Data Mata Kuliah: " + mataKuliahList); // Debugging
        return ResponseEntity.ok(mataKuliahList);
    }


    // Get Mata Kuliah By ID
    @GetMapping("/{id}")
    public ResponseEntity<MataKuliah> getById(@PathVariable Long id) {
        MataKuliah mataKuliahWithDetails = mataKuliahService.getMataKuliahWithDetails(id);
        return ResponseEntity.ok(mataKuliahWithDetails);
    }


    // Update Mata Kuliah
    @PutMapping("/{id}")
    public ResponseEntity<MataKuliah> updateMataKuliah(@PathVariable Long id, @RequestBody MataKuliah mataKuliah) {
        return ResponseEntity.ok(mataKuliahService.updateMataKuliah(id, mataKuliah));
    }

    // Delete Mata Kuliah
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMataKuliah(@PathVariable Long id) {
        mataKuliahService.deleteMataKuliah(id);
        return ResponseEntity.noContent().build();
    }
}
