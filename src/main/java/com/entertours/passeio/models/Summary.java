package com.entertours.passeio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import java.util.UUID;

@Entity
@Immutable
@Subselect(
    value = "SELECT p.id as id, p.nome as nome, p.valor as valor, p.duracao as duracao, l.localidade as local, f.id as foto_id FROM passeios p INNER JOIN foto f ON p.id = f.passeio_id INNER JOIN localidade l ON p.localidade_id = l.id AND f.id = (SELECT MIN(z.id) FROM foto z WHERE z.passeio_id = p.id)"
)
@Synchronize({"passeios", "foto"})
public class Summary {
    
    
    private UUID id;
    
    private String nome;
    
    private Double valor;
    
    private int duracao;
    
    private String local;
    
    @Id    
    private UUID fotoId;

    public Summary() {
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public UUID getFotoId() {
        return fotoId;
    }

    public Double getValor() {
        return valor;
    }

    public int getDuracao() {
        return duracao;
    }

    public String getLocal() {
        return local;
    }
}
