package com.example.aplikasinilai.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MataKuliahMahasiswa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mahasiswa_id")
    @JsonIgnore // Prevents serialization of Mahasiswa in this context
    private Mahasiswa mahasiswa;

    @ManyToOne
    @JoinColumn(name = "mata_kuliah_id")
    @JsonIgnore // Prevents serialization of MataKuliah in this context
    private MataKuliah mataKuliah;

    private int uts;
    private int uas;
    private int kuis;
    private int total;
    private String grade;

    // Default Constructor
    public MataKuliahMahasiswa() {}

    // Parameterized Constructor
    public MataKuliahMahasiswa(Mahasiswa mahasiswa, MataKuliah mataKuliah) {
        this.mahasiswa = mahasiswa;
        this.mataKuliah = mataKuliah;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public MataKuliah getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(MataKuliah mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public int getUts() {
        return uts;
    }

    public void setUts(int uts) {
        this.uts = uts;
        calculateTotalAndGrade();
    }

    public int getUas() {
        return uas;
    }

    public void setUas(int uas) {
        this.uas = uas;
        calculateTotalAndGrade();
    }

    public int getKuis() {
        return kuis;
    }

    public void setKuis(int kuis) {
        this.kuis = kuis;
        calculateTotalAndGrade();
    }

    public int getTotal() {
        return total;
    }

    public String getGrade() {
        return grade;
    }

    // Fungsi untuk menghitung total dan grade
    private void calculateTotalAndGrade() {
        this.total = (this.uts + this.uas + this.kuis) / 3;

        if (total > 80) {
            this.grade = "A";
        } else if (total >= 75) {
            this.grade = "AB";
        } else if (total >= 70) {
            this.grade = "B";
        } else if (total >= 60) {
            this.grade = "BC";
        } else if (total >= 50) {
            this.grade = "C";
        } else {
            this.grade = "D";
        }
    }
}
