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
@Table (name = "cachorro")
@PrimaryKeyJoinColumn (name = "id_animal")
public class Cachorro extends Animal {
    
    private boolean treinado;

    public Cachorro() {
    }

    public Cachorro(Long id, String codigo, String nome, String raca, Date nascimento, double peso, String sexo, String observacao, boolean treinado) {
        super(id, codigo, nome, raca, nascimento, peso, sexo, observacao);
        this.treinado = treinado;
    }

    public boolean isTreinado() {
        return treinado;
    }

    public void setTreinado(boolean treinado) {
        this.treinado = treinado;
    }
    
}
