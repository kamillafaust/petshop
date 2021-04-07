/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.petshop.dao;

import br.com.petshop.modelo.Gato;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Kamilla Faust
 */
public interface GatoDao extends BaseDao<Gato, Long>{
    
    List<Gato> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
    
}
