

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class JpaBaseTestCase {
    protected static EntityManagerFactory emf;

    protected EntityManager em;
    
    protected boolean isRollback;

    @BeforeClass
    public static void createEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("myPu");
    }

    @AfterClass
    public static void closeEntityManagerFactory() {
        emf.close();
    }

    @Before
    public void beginTransaction() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        isRollback = false;
    }

    @After
    public void rollbackTransaction() {   
        if (em.getTransaction().isActive()) {
        	if(isRollback)
                em.getTransaction().rollback();// For rollback test
        	else
        		em.getTransaction().commit();

        }

        if (em.isOpen()) {
            em.close();
        }
    }
}
