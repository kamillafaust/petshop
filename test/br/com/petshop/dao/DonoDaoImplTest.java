/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.petshop.dao;

import br.com.petshop.modelo.Dono;
import java.util.List;
import org.hibernate.*;
import static org.junit.Assert.*;
import org.junit.Test;
import util.UtilGerador;

/**
 *
 * @author Kamilla Faust
 */
public class DonoDaoImplTest {

    private Session sessao;
    private Dono dono;
    private DonoDao donoDao;

    public DonoDaoImplTest() {
        donoDao = new DonoDaoImpl();
    }

    //@Test
    public void testSalvar() {
        System.out.println("Salvar");
        dono = new Dono(null,
                UtilGerador.gerarNome(),
                UtilGerador.gerarNumero(8) + "-" + UtilGerador.gerarNumero(2),
                UtilGerador.gerarTelefoneFixo(),
                UtilGerador.gerarEmail());
   
        sessao = HibernateUtil.abrirConexao();
        donoDao.salvarOuAlterar(dono, sessao);
        sessao.close();

        assertNotNull(dono.getId());
    }
    
    //@Test
    public void testAlterar() {
        System.out.println("Alterar");
        buscarDonoBd();
        dono.setNome(UtilGerador.gerarNome());
        sessao = HibernateUtil.abrirConexao();
        donoDao.salvarOuAlterar(dono, sessao);
        sessao.close();
        
        sessao = HibernateUtil.abrirConexao();
        Dono alterado = donoDao.pesquisarPorId(dono.getId(), sessao);
        sessao.close();
        
        assertEquals(dono.getNome(), alterado.getNome());
    }

    //@Test
    public void testPesquisarPorNome() {
        System.out.println("Pesquisar Por Nome");
        buscarDonoBd();
        String nome = dono.getNome();
        int letra = nome.indexOf(" ");
        nome = nome.substring(0, letra);
        
        sessao = HibernateUtil.abrirConexao();
        List<Dono> donos = donoDao.pesquisarPorNome(nome, sessao);
        sessao.close();
        
        assertNotNull(!donos.isEmpty());
    }

    //@Test
    public void testPesquisarDonoTelefone() {
        System.out.println("Pesquisar Por Telefone");
        buscarDonoBd();
        sessao = HibernateUtil.abrirConexao();
        Dono donoTelefone = donoDao.pesquisarDonoTelefone(dono.getTelefone(), sessao);
        sessao.close();
        
        assertNotNull(donoTelefone); 
    }
    
    public Dono buscarDonoBd() {
        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Dono");
        List<Dono> donos = consulta.list();
        sessao.close();

        if (donos.isEmpty()) {
            testSalvar();
        } else {
            dono = donos.get(0);
        }
        return dono;
    }
}
