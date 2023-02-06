package com.entertours.passeio.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categoria")
public class Categoria {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uui",
        strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotNull
    private String catName;

    @Lob
    @Column(name = "icon", length = Integer.MAX_VALUE, nullable = true)
    @JsonIgnore
    private byte[] icon;

    private String iconName;

    @NotNull
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnore
    private List<Passeio> passeios = new ArrayList<>();

    public Categoria() {}

    public Categoria(@NotNull String catName, @NotNull List<Passeio> passeios) {
        this.catName = catName;
        this.passeios = passeios;
    }

    public String getId() {
        return id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public List<Passeio> getPasseios() {
        return passeios;
    }

    public void addPasseio(Passeio passeio) {
        this.passeios.add(passeio);
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icone) {
        this.icon = icone;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String name) {
        this.iconName = name;
    }
}
