/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.petshop.modelo;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Kamilla Faust
 */

@Entity
@Table (name = "gato")
@PrimaryKeyJoinColumn (name = "id_animal")
public class Gato extends Animal {
    
    private String comportamento;

    public Gato() {
    }

    public Gato(Long id, String codigo, String nome, String raca, Date nascimento, double peso, String sexo, String observacao, String comportamento) {
        super(id, codigo, nome, raca, nascimento, peso, sexo, observacao);
        this.comportamento = comportamento;
    }

    public String getComportamento() {
        return comportamento;
    }

    public void setComportamento(String comportamento) {
        this.comportamento = comportamento;
    }
    
}
