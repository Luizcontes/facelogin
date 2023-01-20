package D.demo.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "foto")
public class Foto {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Lob
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    @JsonIgnore
    private byte[] image;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "passeio_id", nullable = false)
    @JsonIgnore
    private Passeio passeio;

    public Foto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Passeio getPasseio() {
        return passeio;
    }

    public void setPasseio(Passeio passeio) {
        this.passeio = passeio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
