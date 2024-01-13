package com.finalproject.panda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.panda.model.Status;

public interface StatusRepo extends JpaRepository<Status, Integer> {
    

}
