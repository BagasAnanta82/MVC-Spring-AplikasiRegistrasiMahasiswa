package com.example.aplikasinilai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class MataKuliah {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    private String kode;
    private int sks;
    private int uts;
    private int uas;
    private int kuis;
    private int total;
    private String grade;

    @JsonIgnore
    @OneToMany(mappedBy = "mataKuliah", cascade = CascadeType.ALL)
    private List<MataKuliahMahasiswa> mataKuliahMahasiswa;

    @Transient
    private List<Mahasiswa> mahasiswa;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public List<MataKuliahMahasiswa> getMataKuliahMahasiswa() {
        return mataKuliahMahasiswa;
    }

    public void setMataKuliahMahasiswa(List<MataKuliahMahasiswa> mataKuliahMahasiswa) {
        this.mataKuliahMahasiswa = mataKuliahMahasiswa;
    }

    public List<Mahasiswa> getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(List<Mahasiswa> mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public void setUts(int uts) { this.uts = uts; }
    public int getUts() { return uts; }

    public void setUas(int uas) { this.uas = uas; }
    public int getUas() { return uas; }

    public void setKuis(int kuis) { this.kuis = kuis; }
    public int getKuis() { return kuis; }

    public void setTotal(int total) { this.total = total; }
    public int getTotal() { return total; }

    public void setGrade(String grade) { this.grade = grade; }
    public String getGrade() { return grade; }
}

