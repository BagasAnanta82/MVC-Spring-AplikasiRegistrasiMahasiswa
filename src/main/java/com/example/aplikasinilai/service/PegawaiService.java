package com.example.aplikasinilai.service;

import com.example.aplikasinilai.model.Pegawai;
import com.example.aplikasinilai.repository.PegawaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PegawaiService {

    @Autowired
    private PegawaiRepository pegawaiRepository;

    // Create Pegawai
    public Pegawai createPegawai(Pegawai pegawai) {
        return pegawaiRepository.save(pegawai);
    }

    // Get All Pegawai
    public List<Pegawai> getAllPegawai() {
        return pegawaiRepository.findAll();
    }

    // Get Pegawai By ID
    public Pegawai getPegawaiById(Long id) {
        return pegawaiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pegawai tidak ditemukan dengan ID: " + id));
    }

    // Update Pegawai
    public Pegawai updatePegawai(Long id, Pegawai updatedPegawai) {
        Pegawai pegawai = getPegawaiById(id);
        pegawai.setUsername(updatedPegawai.getUsername());
        pegawai.setPassword(updatedPegawai.getPassword());
        pegawai.setNip(updatedPegawai.getNip());
        pegawai.setPosisi(updatedPegawai.getPosisi());
        return pegawaiRepository.save(pegawai);
    }

    // Delete Pegawai
    public void deletePegawai(Long id) {
        pegawaiRepository.deleteById(id);
    }
}
