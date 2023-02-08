package com.entertours.passeio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;

import com.entertours.passeio.models.Localidade;

public interface LocalidadeRepository extends JpaRepository<Localidade, UUID> {
 
    Optional<Localidade> findById(UUID uuid);
}
