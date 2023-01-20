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
@Table(name = "localidade")
public class Localidade {
    
    @Id
    @GeneratedValue
    @org.hibernate.annotations.Type(type="org.hibernate.type.UUIDCharType")
    // @JsonIgnore
    private UUID id;

    @NotNull
    private String localidade;

    @Lob
    @Column(name = "local_image", length = Integer.MAX_VALUE, nullable = true)
    @JsonIgnore
    private byte[] localImage;

    private String imgName;

    @OneToMany(mappedBy = "local", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnore
    private List<Passeio> passeios = new ArrayList<>();

    public Localidade() {}

    public Localidade(@NotNull String localidade, List<Passeio> passeios) {
        this.localidade = localidade;
        this.passeios = passeios;
    }

    public UUID getId() {
        return id;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public List<Passeio> getPasseios() {
        return passeios;
    }

    public void addPasseio(Passeio passeio) {
        this.passeios.add(passeio);
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public byte[] getLocalImage() {
        return localImage;
    }

    public void setLocalImage(byte[] localImage) {
        this.localImage = localImage;
    }
}
