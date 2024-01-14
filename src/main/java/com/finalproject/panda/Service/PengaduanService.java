package com.finalproject.panda.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.panda.Repository.PengaduanRepo;
import com.finalproject.panda.model.Pengaduan;
import com.finalproject.panda.model.Status;
// import com.finalproject.panda.model.Status;
import com.finalproject.panda.model.User;

import lombok.NonNull;

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

    public void deletePengaduan(@NonNull Integer id_registrasi) {
        log.info("Deleting pengaduan with id register: " + id_registrasi);
        
        try {
            pengaduanRepo.deleteById(id_registrasi);
            log.info("Pengaduan dengan id register: " + id_registrasi + " berhasil dihapus");
        } catch (Exception e) {
            log.error("Error deleting pengaduan with id register: " + id_registrasi, e);
        }
    }
    

    public Pengaduan getPengaduanById(@NonNull Integer id) {
        return pengaduanRepo.findById(id).orElse(null);
    }


    public List<Pengaduan> getPengaduanByNik(String nik){
        if(nik != null){
            return pengaduanRepo.getByUserNik(nik);
        }

        return null;
        
    }
    // public Status getStatusByIdRegister(Integer id_register){
    //    Pengaduan pengaduan = getPengaduanById(id_register);
    //    return pengaduan.getStatus();

        
    // }

    public long jumlahPengaduan(){
        return pengaduanRepo.count();
    }

    public long jumlahPengaduanBulanIni() {
        return pengaduanRepo.countByMonth();
    }
}