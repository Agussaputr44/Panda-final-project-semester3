package com.finalproject.panda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.panda.model.User;


public interface UserRepo extends JpaRepository<User, Integer> {

    User findByNik(String nik);
   
}
