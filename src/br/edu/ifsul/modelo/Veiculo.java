package br.edu.ifsul.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ubiratan
 */
@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_veiculo", sequenceName = "seq_veiculo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_veiculo", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "O modelo não pode ser nulo")
    @Length(max = 20, message = "O modelo não pode ter mais que {max} caracteres")
    @NotBlank(message = "O modelo não pode ser em branco")
    @Column(name = "modelo", length = 20, nullable = false)
    private String modelo;
    @NotNull(message = "A cor não pode ser nulo")
    @Length(max = 20, message = "A cor não pode ter mais que {max} caracteres")
    @NotBlank(message = "A cor não pode ser em branco")
    @Column(name = "cor", length = 20, nullable = false)
    private String cor;
    @NotNull(message = "A placa não pode ser nulo")
    @Length(max = 20, message = "A placa não pode ter mais que {max} caracteres")
    @NotBlank(message = "A placa não pode ser em branco")
    @Column(name = "placa", length = 20, nullable = false, unique = true)
    private String placa;
    @ManyToOne
    @JoinColumn(name = "montadora", referencedColumnName = "id", nullable = false, 
            foreignKey = @ForeignKey(name = "fk_montadora"))
    private Montadora montadora;
    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL, 
            orphanRemoval = true, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<Instrutor> instrutor = new ArrayList<>();
        
    public Veiculo(){
        
    }
    
    public void adicionarInstrutor(Instrutor obj){
        obj.setVeiculo(this);
        this.instrutor.add(obj);
    }
    
    public void removerInstrutor(int idx){
        this.instrutor.remove(idx);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Montadora getMontadora() {
        return montadora;
    }

    public void setMontadora(Montadora montadora) {
        this.montadora = montadora;
    }
        
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Veiculo other = (Veiculo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<Instrutor> getInstrutores() {
        return instrutor;
    }

    public void setInstrutores(List<Instrutor> instrutores) {
        this.instrutor = instrutores;
    }
    
}
