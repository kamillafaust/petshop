/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.petshop.dao;

import br.com.petshop.modelo.*;
import java.util.*;
import org.hibernate.*;
import org.junit.Test;
import static org.junit.Assert.*;
import util.UtilGerador;

/**
 *
 * @author Kamilla Faust
 */
public class CachorroDaoImplTest {
    
    private Session sessao;
    private Cachorro cachorro;
    private CachorroDao cachorroDao;
    
    public CachorroDaoImplTest() {
        cachorroDao = new CachorroDaoImpl();
    }

    //@Test
    public void testSalvar() {
        System.out.println("Salvar");
        cachorro = new Cachorro(null,
                UtilGerador.gerarNumero(5),
                UtilGerador.gerarNomeAnimal(),
                UtilGerador.gerarEspecieCachorro(),
                new Date(),
                1.5, 
                "M",
                UtilGerador.gerarCaracter(8), true);
        
        DonoDaoImplTest umDono = new DonoDaoImplTest();
        Dono dono = umDono.buscarDonoBd();
        cachorro.setDono(dono);
        
        sessao = HibernateUtil.abrirConexao();
        cachorroDao.salvarOuAlterar(cachorro, sessao);
        sessao.close();
        
        assertNotNull(cachorro.getId());
    }
    
    //@Test
    public void testAlterar() {
        System.out.println("Alterar");
        buscarCachorroBd();
        cachorro.setNome(UtilGerador.gerarNomeAnimal());
        cachorro.setRaca(UtilGerador.gerarEspecieCachorro());
        sessao = HibernateUtil.abrirConexao();
        cachorroDao.salvarOuAlterar(cachorro, sessao);
        sessao.close();
        
        sessao = HibernateUtil.abrirConexao();
        Cachorro cachorroAlterado = cachorroDao.pesquisarPorId(cachorro.getId(), sessao);
        sessao.close();
        
        assertEquals(cachorro.getNome(), cachorroAlterado.getNome());
        assertEquals(cachorro.getRaca(), cachorroAlterado.getRaca()); 
    }
    
    //@Test
    public void testPesquisarPorNome() {
        System.out.println("Pesquisar Por Nome");
        buscarCachorroBd();
        String nome = cachorro.getNome().substring(0, 3);
        
        sessao = HibernateUtil.abrirConexao();
        List<Cachorro> cachorros = cachorroDao.pesquisarPorNome(nome, sessao);
        sessao.close();
        
        assertNotNull(!cachorros.isEmpty());
    }
    
    private Cachorro buscarCachorroBd(){
        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Cachorro");
        List<Cachorro> cachorros = consulta.list();
        sessao.close();
        if(cachorros.isEmpty()){
            testSalvar();            
        } else {
            cachorro = cachorros.get(0);
        }
        return cachorro;
    }
}
