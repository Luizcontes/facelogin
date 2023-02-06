package com.entertours.passeio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entertours.passeio.models.Localidade;

public interface LocalidadeRepository extends JpaRepository<Localidade, String> {
    
}
