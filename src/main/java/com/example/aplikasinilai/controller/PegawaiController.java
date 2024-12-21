package com.example.aplikasinilai.controller;

import com.example.aplikasinilai.model.Pegawai;
import com.example.aplikasinilai.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pegawai")
public class PegawaiController {

    @Autowired
    private PegawaiService pegawaiService;

    // Create Pegawai
    @PostMapping
    public ResponseEntity<Pegawai> createPegawai(@RequestBody Pegawai pegawai) {
        return ResponseEntity.ok(pegawaiService.createPegawai(pegawai));
    }

    // Get All Pegawai
    @GetMapping
    public ResponseEntity<List<Pegawai>> getAllPegawai() {
        return ResponseEntity.ok(pegawaiService.getAllPegawai());
    }

    // Get Pegawai By ID
    @GetMapping("/{id}")
    public ResponseEntity<Pegawai> getPegawaiById(@PathVariable Long id) {
        return ResponseEntity.ok(pegawaiService.getPegawaiById(id));
    }

    // Update Pegawai
    @PutMapping("/{id}")
    public ResponseEntity<Pegawai> updatePegawai(@PathVariable Long id, @RequestBody Pegawai pegawai) {
        return ResponseEntity.ok(pegawaiService.updatePegawai(id, pegawai));
    }

    // Delete Pegawai
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePegawai(@PathVariable Long id) {
        pegawaiService.deletePegawai(id);
        return ResponseEntity.noContent().build();
    }
}
