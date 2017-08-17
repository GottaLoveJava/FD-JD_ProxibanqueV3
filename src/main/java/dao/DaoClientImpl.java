package dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Client;

@Named
@ApplicationScoped
public class DaoClientImpl implements IDaoClient {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");


	@Override
	public List<Client> listeClients() throws Exception {
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		List<Client> retList = new ArrayList<>();
		try {
			txn.begin();

			TypedQuery<Client> query = em.createQuery("from Client", Client.class);
			retList = query.getResultList();
			
			
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
		return retList;
	}


	@Override
	public boolean ajouterClient(Client client) throws Exception {
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		boolean result = false;
		try {
			txn.begin();
			em.persist(client);
			txn.commit();
			result = true;
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
		return result;
	}


	@Override
	public Client afficherClient(long clientId) throws Exception {
		Client client = null;
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		try {
			txn.begin();
			client = em.find(Client.class, clientId);
			
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
		return client;
	}


	@Override
	public boolean modifierClient(Client client) throws Exception {
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		boolean result = false;
		try {
			txn.begin();
			em.merge(client);
			txn.commit();
			result = true;
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
		return result;
		

	}


	@Override
	public boolean supprimerClient(long clientId) throws Exception {
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		boolean result = false;
		try {
			txn.begin();
			Client client = em.find(Client.class, clientId);
			em.remove(client);
			txn.commit();
			result = true;
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
		return result;

	}

}
