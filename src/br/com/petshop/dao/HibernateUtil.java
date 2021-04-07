/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.petshop.dao;

import br.com.petshop.modelo.Animal;
import br.com.petshop.modelo.Cachorro;
import br.com.petshop.modelo.Dono;
import br.com.petshop.modelo.Gato;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Kamilla Faust
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration cfg = new Configuration();
            cfg.addAnnotatedClass(Dono.class);
            cfg.addAnnotatedClass(Animal.class);
            cfg.addAnnotatedClass(Cachorro.class);
            cfg.addAnnotatedClass(Gato.class);
            cfg.configure("/br/com/petshop/dao/hibernate.cfg.xml");

            StandardServiceRegistryBuilder build = new StandardServiceRegistryBuilder().
                    applySettings(cfg.getProperties());
            sessionFactory = cfg.buildSessionFactory(build.build());

        } catch (Throwable ex) {
            
            System.err.println("Erro ao criar HibernateUtil" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session abrirConexao() {
        return sessionFactory.openSession();
    }
}
