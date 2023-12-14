package com.finalproject.panda.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "registrasi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registrasi {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_registrasi;

    private Date created_at;

    @OneToOne(mappedBy = "registrasi")
    private Identitas identitas;

}
