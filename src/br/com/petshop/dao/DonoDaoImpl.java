/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.petshop.dao;

import br.com.petshop.modelo.Dono;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Kamilla Faust
 */
public class DonoDaoImpl extends BaseDaoImpl <Dono, Long> implements DonoDao, Serializable{

    @Override
    public Dono pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return (Dono) sessao.get(Dono.class, id);
    }

    @Override
    public List<Dono> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Dono where nome like :nomeHQL");
        consulta.setParameter("nomeHQL", "%" + nome + "%");
        return consulta.list();
    }

    @Override
    public Dono pesquisarDonoTelefone(String telefone, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Dono where telefone like :telefoneHQL");
        consulta.setParameter("telefoneHQL", "%" + telefone + "%");
        return (Dono) consulta.uniqueResult();
    }
    
}
