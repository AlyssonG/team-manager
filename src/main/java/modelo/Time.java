package modelo;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Time.recuperaUmTime",
                query = "select t from Time t where t.id = ?1"
        ),
        @NamedQuery(
                name = "Time.recuperaUmTimeEMembros",
                query = "select t from Time t left outer join fetch t.membros where t.id = ?1"
        ),
        @NamedQuery(
                name = "Time.recuperaTimesEMembros",
                query = "select t from Time t left outer join fetch t.membros"
        )
})

@Entity
@Table(name = "TIME")
@SequenceGenerator(name = "SEQUENCIA_TIME",
        sequenceName = "SEQ_TIME",
        allocationSize = 1)
public class Time {
    private Long id;
    private String nome;
    private String liga;

    private List<Membro> membros = new ArrayList<Membro>();

    public Time() {
    }

    public Time(String nome, String liga) {
        this.nome = nome;
        this.liga = liga;
    }

    // ********* M�todos do Tipo Get *********

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCIA_TIME")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Column(nullable = false)
    public String getNome() {
        return nome;
    }

    @Column(nullable = false)
    public String getLiga() {
        return liga;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    @OneToMany(mappedBy = "time")
    @OrderBy
    public List<Membro> getMembros() {
        return membros;
    }

    public void setMembros(List<Membro> membros) {
        this.membros = membros;
    }
}

