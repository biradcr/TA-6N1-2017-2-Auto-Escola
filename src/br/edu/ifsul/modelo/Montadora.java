package br.edu.ifsul.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ubiratan
 */
@Entity
@Table(name = "montadora")
public class Montadora implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_montadora", sequenceName = "seq_montadora_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_montadora", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "O nome não pode ser nulo")
    @Length(max = 50, message = "O nome não pode ter mais que {max} caracteres")
    @NotBlank(message = "O nome não pode ser em branco")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    @NotNull(message = "O pais de origem não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "pais_origem", referencedColumnName = "id", nullable = false, 
            foreignKey = @ForeignKey(name = "fk_pais_origem"))
    private Pais_Origem pais_origem;
    
        
    public Montadora(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        final Montadora other = (Montadora) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Pais_Origem getPais_origem() {
        return pais_origem;
    }

    public void setPais_origem(Pais_Origem pais_origem) {
        this.pais_origem = pais_origem;
    }

   
    
}
