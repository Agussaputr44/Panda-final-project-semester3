package com.finalproject.panda.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.panda.Repository.StatusRepo;
import com.finalproject.panda.model.Status;

@Service
public class StatusService {

    @Autowired
    private StatusRepo statusRepo;


    public List<Status> getAll(){
        return statusRepo.findAll();
        
    }
    
}
