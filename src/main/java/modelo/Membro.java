package modelo;

import java.util.Calendar;

import javax.persistence.*;



@Entity
@Table(name = "MEMBRO")
@SequenceGenerator(name = "SEQUENCIA_MEMBRO",
        sequenceName = "SEQ_MEMBRO",
        allocationSize = 1)

public class Membro {
    private Long id;
    private String nome;
    private String posicao;
    private Calendar dataAdimissao;

    private Time time;

    public Membro() {
    }

    public Membro(String nome, String posicao, Calendar dataAdimissao, Time time) {
        this.nome = nome;
        this.posicao = posicao;
        this.dataAdimissao = dataAdimissao;
        this.time = time;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCIA_MEMBRO")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Column(nullable = false)
    public String getNome() {
        return nome;
    }

    @Column(name = "DATA_ADIMISSAO")
    public Calendar getDataAdimissao() {
        return dataAdimissao;
    }

    @Column(nullable = false)
    public String getPosicao() {
        return posicao;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataAdimissao(Calendar dataAdimissao) {
        this.dataAdimissao = dataAdimissao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TIME_ID")
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}	