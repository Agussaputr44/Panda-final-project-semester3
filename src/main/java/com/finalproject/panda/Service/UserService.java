package com.finalproject.panda.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.finalproject.panda.Repository.UserRepo;
import com.finalproject.panda.model.User;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;

    public User saveUser(User users) {
        User saveUser = userRepo.save(users);
        // log.info(users.getNama_lengkap() + " berhasil mendaftar");
        return saveUser;
    }

    public User checkLogin(String nik, String password) {
        User user = userRepo.findByNik(nik);

        if (user != null) {
            String passFromDb = user.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if (passwordEncoder.matches(password, passFromDb)) {
                log.info(user.getNama_lengkap() + " berhasil login");
                return user;
            } else {
                log.info("Password salah");
            }

        } else {
            log.info("User tidak ditemukan");
        }
        return null;
    }

    public User getUserByNik(String nik) {
        return userRepo.findByNik(nik);
    }

}
