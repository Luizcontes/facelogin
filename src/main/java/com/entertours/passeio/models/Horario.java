package com.entertours.passeio.models;

import java.time.LocalTime;

import javax.persistence.Entity;

public class Horario {
    
    private LocalTime horario;

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}
