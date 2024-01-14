package com.finalproject.panda.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finalproject.panda.model.Pengaduan;
// import com.finalproject.panda.model.Status;

public interface PengaduanRepo extends JpaRepository<Pengaduan, Integer> {
    List<Pengaduan> getByUserNik(String nik);

    @Query("SELECT COUNT(p) FROM pengaduan p WHERE MONTH(p.created_at) = MONTH(CURRENT_DATE) AND YEAR(p.created_at) = YEAR(CURRENT_DATE)")
    long countByMonth();
}
