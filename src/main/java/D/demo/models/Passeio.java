package D.demo.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "passeios")
public class Passeio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @org.hibernate.annotations.Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @NotNull
    private String nome;

    @NotNull
    private Double valor;
    
    @NotNull
    private int duracao;
    
    @NotNull
    private String descricao;

    // @NotNull
    // private int rating;

    // @NotNull
    // private int totalReview;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "localidade_id", nullable = false)
    private Localidade local;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "passeio", cascade = CascadeType.PERSIST, orphanRemoval = true)
    // @JsonIgnore
    private List<Foto> fotos = new ArrayList<>();

    // @Lob
    // @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    // private byte[] image;

    // private List<byte[]> images;

    public Passeio() {}

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Localidade getLocal() {
        return local;
    }

    public void setLocal(Localidade local) {
        this.local = local;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void addFoto(Foto foto) {
        this.fotos.add(foto);
    }
}
