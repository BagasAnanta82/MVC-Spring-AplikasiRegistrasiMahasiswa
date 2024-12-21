package com.example.aplikasinilai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Mahasiswa extends User {

    private String nim; // Nomor Induk Mahasiswa
    private String prodi; // Program studi

    @JsonIgnore
    @OneToMany(mappedBy = "mahasiswa", cascade = CascadeType.ALL)
    private List<MataKuliahMahasiswa> mataKuliahMahasiswa;

    @Transient
    private List<MataKuliah> mataKuliah;

    // Default Constructor
    public Mahasiswa() {}

    // Parameterized Constructor
    public Mahasiswa(String username, String password, Role role, String nim, String prodi) {
        super(username, password, role);
        this.nim = nim;
        this.prodi = prodi;
    }

    // Getters and Setters
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public List<MataKuliahMahasiswa> getMataKuliahMahasiswa() {
        return mataKuliahMahasiswa;
    }

    public void setMataKuliahMahasiswa(List<MataKuliahMahasiswa> mataKuliahMahasiswa) {
        this.mataKuliahMahasiswa = mataKuliahMahasiswa;
    }

    public List<MataKuliah> getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(List<MataKuliah> mataKuliah) {
        this.mataKuliah = mataKuliah;
    }
}
