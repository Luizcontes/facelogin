package com.entertours.passeio.models;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Day {

    private UUID id;

    private LocalDate dia;

    private String passeioId;

    private List<Horario> horario;

    public UUID getId() {
        return id;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public List<Horario> getHorario() {
        return horario;
    }

    public void setHorario(List<Horario> horario) {
        this.horario = horario;
    }

    public String getPasseioId() {
        return passeioId;
    }

    public void setPasseioId(String passeioId) {
        this.passeioId = passeioId;
    }
}
