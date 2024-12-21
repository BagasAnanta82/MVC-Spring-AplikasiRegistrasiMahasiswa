package com.example.aplikasinilai.controller;

import com.example.aplikasinilai.model.Mahasiswa;
import com.example.aplikasinilai.repository.MahasiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

@Controller
public class ViewController {

    @GetMapping("/dashboard_mahasiswa")
    public String dashboardMahasiswa(Authentication auth, Model model) {
        String username = auth.getName(); // Ambil username dari sesi pengguna
        model.addAttribute("username", username); // Kirim username ke view
        return "dashboard_mahasiswa"; // Return ke template dashboard_mahasiswa.html
    }

    @GetMapping("/dashboard_pegawai")
    public String dashboardPegawai(Authentication auth, Model model) {
        String username = auth.getName(); // Ambil username dari sesi pengguna
        model.addAttribute("username", username); // Kirim username ke view
        return "dashboard_pegawai"; // Return ke template dashboard_pegawai.html
    }

    @GetMapping("/dashboard_pegawai/mahasiswa")
    public String showCrudMahasiswaPage() {
        return "crud_mahasiswa";
    }

    @GetMapping("/dashboard_pegawai/pegawai")
    public String showCrudPegawaiPage() {
        return "crud_pegawai";
    }

    @GetMapping("/dashboard_pegawai/matakuliah")
    public String showCrudMataKuliahPage() {
        return "crud_matakuliah";
    }

    @GetMapping("/dashboard_pegawai/lookup_nim")
    public String showNimPage() {
        return "lookup_nim";
    }

    @GetMapping("/dashboard_pegawai/lookup_id")
    public String showIdPage() {
        return "lookup_id";
    }

    @GetMapping("/dashboard_pegawai/lookup_matkul")
    public String showMatkulPage() {
        return "lookup_matkul";
    }

    @GetMapping("/dashboard_mahasiswa/registrasi_matakuliah")
    public String registrasiMataKuliah(Authentication auth, Model model) {
        // Ambil username pengguna yang sedang login
        String username = auth.getName();
        System.out.println("DEBUG: Logged-in username = " + username);

        // Cari ID mahasiswa berdasarkan username
        Long mahasiswaId = cariIdMahasiswaByUsername(username); // Pastikan fungsi ini benar
        System.out.println("DEBUG: Mahasiswa ID = " + mahasiswaId);

        // Tambahkan mahasiswaId ke model
        model.addAttribute("mahasiswaId", mahasiswaId);
        return "regis_matakuliah";
    }

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    // Contoh service method, implementasikan sesuai kebutuhan
    private Long cariIdMahasiswaByUsername(String username) {
        return mahasiswaRepository.findByUsername(username)
                .map(Mahasiswa::getId)
                .orElseThrow(() -> new RuntimeException("Mahasiswa not found with username: " + username));
    }

    @GetMapping("/dashboard_mahasiswa/view_transkrip")
    public String showMahasiswaTranskripPage(Authentication auth, Model model) {
        // Ambil username pengguna yang sedang login
        String username = auth.getName();
        // Cari ID mahasiswa berdasarkan username
        Long mahasiswaId = cariIdMahasiswaByUsername(username); // Pastikan fungsi ini benar

        // Tambahkan mahasiswaId ke model
        model.addAttribute("mahasiswaId", mahasiswaId);
        return "view_transkrip";
    }

    @GetMapping("dashboard_pegawai/update-nilai-page")
    public String updateNilaiPage() {
        return "update_nilai"; // Mengarah ke file `update_nilai.html`
    }

}
