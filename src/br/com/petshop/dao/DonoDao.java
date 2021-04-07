/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.petshop.dao;

import br.com.petshop.modelo.Dono;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author Kamilla Faust
 */
public interface DonoDao extends BaseDao <Dono, Long> {
    
    List<Dono> pesquisarPorNome (String nome, Session sessao) throws HibernateException;
        
    Dono pesquisarDonoTelefone (String telefone, Session sessao) throws HibernateException;
    
}
