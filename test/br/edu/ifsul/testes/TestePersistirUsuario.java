/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Permissao;
import br.edu.ifsul.modelo.Setor;
import br.edu.ifsul.modelo.Usuario;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ricardo
 */
public class TestePersistirUsuario {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirUsuario() {
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("TA-2018-1-6N1-ModelPU");
        em = emf.createEntityManager();
    }
    
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    
    @Test
    public void teste(){
        boolean exception = false;
        try {
           em.getTransaction().begin();
            Usuario obj = new Usuario();
            obj.setEmail("jorge.bavaresco@passofundo.ifsul.edu.br");
            obj.setNascimento(Calendar.getInstance());
            obj.setNome("Jorge");
            obj.setSenha("123456");
            obj.setSetor(em.find(Setor.class, 1));
            obj.setUsuario("jorgebb");
            Permissao p = em.find(Permissao.class, "ADMINISTRADOR");
            Permissao p1 = em.find(Permissao.class, "USUARIO");
            obj.getPermissoes().add(p);
            obj.getPermissoes().add(p1);
            em.persist(obj);
            em.getTransaction().commit();
        } catch(Exception e){
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);
    }
    
}
