package com.finalproject.panda.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.panda.Repository.PengaduanRepo;
import com.finalproject.panda.Repository.StatusRepo;
import com.finalproject.panda.model.Pengaduan;
import com.finalproject.panda.model.Status;
import com.finalproject.panda.model.User;

@Service
public class PengaduanService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private PengaduanRepo pengaduanRepo;
    @Autowired
    private StatusService statusService;

    @Autowired
    private UserService userService;

    public List<Pengaduan> getAllPengaduan() {
        return pengaduanRepo.findAll();
    }

    public Pengaduan savePengaduan(Pengaduan pengaduan, User user) {
        User user1 = userService.getUserByNik(user.getNik());
        List<Status> all = statusService.getAll();
        pengaduan.setStatus(all.get(0));
        pengaduan.setCreated_at(LocalDateTime.now());
        pengaduan.setUser(user1);
        Pengaduan savedPengaduan = pengaduanRepo.save(pengaduan);
        return savedPengaduan;
    }

    public void deletePengaduan(Integer id) {
        pengaduanRepo.deleteById(id);
        log.info("pengaduan dengan id register: " + id + " berhasil di hapus");
    }

    public Pengaduan getPengaduanById(Integer id) {
        return pengaduanRepo.findById(id).orElse(null);
    }

    public Pengaduan updatePengaduan(Integer id, Pengaduan pengaduan, User user) {

        Pengaduan pengaduan2 = getPengaduanById(id);

        if (pengaduan2 != null) {
            pengaduan2.setAduan(pengaduan.getAduan());
            pengaduan2.setAlamat(pengaduan.getAlamat());
            pengaduan2.setHarapan(pengaduan.getHarapan());
            pengaduan2.setId_registrasi(pengaduan.getId_registrasi());
            pengaduan2.setNama(pengaduan.getNama());
            pengaduan2.setPekerjaan(pengaduan.getPekerjaan());
            pengaduan2.setTTL(pengaduan.getTTL());
            pengaduan2.setPendidikan(pengaduan.getPendidikan());
            pengaduan2.setCreated_at(pengaduan.getCreated_at());

            Pengaduan newPengaduan = pengaduanRepo.save(pengaduan2);
            return newPengaduan;
        }

        return null;
    }

    public List<Pengaduan> getPengaduanByNik(String nik){
        if(nik != null){
            return pengaduanRepo.getByUserNik(nik);
        }

        return null;
        
    }
    public Status getStatusByIdRegister(Integer id_register){
       Pengaduan pengaduan = getPengaduanById(id_register);
       return pengaduan.getStatus();

        
    }

    public long jumlahPengaduan(){
        return pengaduanRepo.count();
    }

    public long jumlahPengaduanBulanIni() {
        return pengaduanRepo.countByMonth();
    }
}
