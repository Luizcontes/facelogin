package D.demo.modelsDTO;

import java.io.Serializable;
import java.util.UUID;

import D.demo.models.Localidade;

public class PasseioDTO implements Serializable {

    private UUID id;

    private String nome;

    private Localidade local;

    private Double valor;

    public PasseioDTO() {
    }

    public PasseioDTO(UUID id, String nome, Localidade local, Double valor) {
        this.id = id;
        this.nome = nome;
        this.local = local;
        this.valor = valor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Localidade getLocal() {
        return local;
    }

    public void setLocal(Localidade local) {
        this.local = local;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
