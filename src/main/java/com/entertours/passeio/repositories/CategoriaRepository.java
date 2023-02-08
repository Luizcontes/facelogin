package com.entertours.passeio.repositories;


import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entertours.passeio.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
 
    Optional<Categoria> findById(UUID uuid);
}
