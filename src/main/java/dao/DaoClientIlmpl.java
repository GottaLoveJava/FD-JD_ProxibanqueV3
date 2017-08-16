package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Client;

public class DaoClientIlmpl {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
	
	public boolean ajouterClient(Client client) throws Exception{
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		boolean result = false;
		try {
			txn.begin();
			em.persist(client);
			txn.commit();
		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		result = true;
		return result;
	}

}
