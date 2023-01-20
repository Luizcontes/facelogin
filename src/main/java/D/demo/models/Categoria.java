package D.demo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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

    public UUID getId() {
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
