package D.demo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categoria")
public class Categoria {
    
    @Id
    @GeneratedValue()
    @org.hibernate.annotations.Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @NotNull
    private String categoria;

    @NotNull
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnore
    private List<Passeio> passeios = new ArrayList<>();

    public Categoria() {}

    public Categoria(@NotNull String categoria, @NotNull List<Passeio> passeios) {
        this.categoria = categoria;
        this.passeios = passeios;
    }

    public UUID getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Passeio> getPasseios() {
        return passeios;
    }

    public void addPasseio(Passeio passeio) {
        this.passeios.add(passeio);
    }
}
