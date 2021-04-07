/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.petshop.dao;

import br.com.petshop.modelo.*;
import java.util.Date;
import java.util.List;
import org.hibernate.*;
import org.junit.Test;
import static org.junit.Assert.*;
import util.UtilGerador;

/**
 *
 * @author Kamilla Faust
 */
public class GatoDaoImplTest {

    private Session sessao;
    private Gato gato;
    private GatoDao gatoDao;

    public GatoDaoImplTest() {
        gatoDao = new GatoDaoImpl();
    }

    //@Test
    public void testSalvar() {
        System.out.println("Salvar");
        gato = new Gato(null,
                UtilGerador.gerarNumero(4),
                UtilGerador.gerarNomeAnimal(),
                UtilGerador.gerarEspecieGato(),
                new Date(),
                1.0,
                "F",
                UtilGerador.gerarCaracter(8),
                UtilGerador.gerarComportamento());
        
        DonoDaoImplTest umDono = new DonoDaoImplTest();
        Dono dono = umDono.buscarDonoBd();
        gato.setDono(dono);
        
        sessao = HibernateUtil.abrirConexao();
        gatoDao.salvarOuAlterar(gato, sessao);
        sessao.close();
        
        assertNotNull(gato.getId());   
    }

    //@Test
    public void testAlterar() {
        System.out.println("Alterar");
        buscarGatoBd();
        gato.setNome(UtilGerador.gerarNomeAnimal());
        gato.setComportamento(UtilGerador.gerarComportamento());
        sessao = HibernateUtil.abrirConexao();
        gatoDao.salvarOuAlterar(gato, sessao);
        sessao.close();
        
        sessao = HibernateUtil.abrirConexao();
        Gato gatoAlterado = gatoDao.pesquisarPorId(gato.getId(), sessao);
        sessao.close();
        
        assertEquals(gato.getNome(), gatoAlterado.getNome());
        assertEquals(gato.getComportamento(), gatoAlterado.getComportamento());
    }

    //@Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisar Por Nome");
        buscarGatoBd();
        String nome = gato.getNome().substring(0, 4);
        
        sessao = HibernateUtil.abrirConexao();
        List<Gato> gatos = gatoDao.pesquisarPorNome(nome, sessao);
        sessao.close();
        
        assertNotNull(!gatos.isEmpty());
    }
    
    private Gato buscarGatoBd(){
        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Gato");
        List<Gato> gatos = consulta.list();
        sessao.close();
        if(gatos.isEmpty()){
            testSalvar();
        } else {
            gato = gatos.get(0);
        }
        return gato;
    }
}
