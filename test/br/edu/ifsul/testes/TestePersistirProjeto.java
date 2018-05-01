/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Colaborador;
import br.edu.ifsul.modelo.Projeto;
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
public class TestePersistirProjeto {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirProjeto() {
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
            Projeto p = new Projeto();
            /*
            Colaborador c = em.find(Colaborador.class, 1);
            Colaborador c1 = em.find(Colaborador.class, 1);
            Colaborador c2 = em.find(Colaborador.class, 1);
            p.getListaColaboradores().add(c);
            p.getListaColaboradores().add(c1);
            p.getListaColaboradores().add(c2);*/
            p.setAtivo(true);
            p.setDescricao("Projeto lixo do futuro - Recilagem");
            p.setFim(Calendar.getInstance());
            p.setInicio(Calendar.getInstance());
            p.setNome("Projeto lixo do futuro - Recilagem");
            p.setSetor(em.find(Setor.class, 1));
            
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch(Exception e){
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);
    }
    
}
