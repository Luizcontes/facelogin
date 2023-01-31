package com.entertours.passeio.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entertours.passeio.models.Foto;

public interface FotoRepository extends JpaRepository<Foto, UUID>{

}
