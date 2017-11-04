package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import util.Util;

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

    // ********* Métodos do Tipo Get *********

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

    // ********* Métodos para Associações *********

    /*
     * Com o atributo mappedBy. Sem ele a  JPA irá procurar  pela
     * tabela PRODUTO_LANCE. Com ele, ao se  tentar recuperar  um
     * produto  e  todos  os  seus  membros, o  join de PRODUTO  e
     * LANCE irá acontecer através da chave estrangeira existente
     * em  LANCE.  Sem  ele  a  JPA  irá  procurar  pela   tabela
     * PRODUTO_LANCE.
     */
    @OneToMany(mappedBy = "time")
    @OrderBy
    public List<Membro> getMembros() {
        return membros;
    }

    public void setMembros(List<Membro> membros) {
        this.membros = membros;
    }
}

