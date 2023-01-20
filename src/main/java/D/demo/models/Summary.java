package D.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@Entity
@Immutable
@Subselect(
    value = "SELECT p.id as id, p.nome as nome, p.valor as valor, p.duracao as duracao, l.localidade as local, f.id as foto_id FROM passeios p INNER JOIN foto f ON p.id = f.passeio_id INNER JOIN localidade l ON p.localidade_id = l.id AND f.id = (SELECT MIN(z.id) FROM foto z WHERE z.passeio_id = p.id)"
)
@Synchronize({"passeios", "foto"})
public class Summary {
    
    
    private String id;
    
    private String nome;
    
    private Double valor;
    
    private int duracao;
    
    private String local;
    
    @Id    
    private String fotoId;

    public Summary() {
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getFotoId() {
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
