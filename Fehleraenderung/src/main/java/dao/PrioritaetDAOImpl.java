package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import domain.PrioritaetEntity;

public class PrioritaetDAOImpl implements PrioritaetDAOI
{
//Attribute der Klasse
	private EntityManager em;
	
//Konstruktor der Klasse
	public PrioritaetDAOImpl()
	{
		em = EntityManagerSingleton.getInstance().getEM();
	}
	
//Implementationen der Methoden des Interfaces
	/**
	 * Liefert eine Liste aller Prioritaeten in der Datenbank
	 * @return List<PrioritaetEntity>
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public List<PrioritaetEntity> findAll() throws Exception
	{
		//Lokale Variablen der Methoden
		List<PrioritaetEntity> prioritaet = null;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum finden aller Prioritaeten
		String queryString = "from PrioritaetEntity as pr";
		TypedQuery<PrioritaetEntity> query = em.createQuery(queryString,PrioritaetEntity.class);
		prioritaet = query.getResultList();
		et.commit();	//Ende der Transaktion
		return prioritaet;
	}
	
	/**
	 * Bekommt eine Prioritaet ID und liefert die dazugehoerige Prioritaet
	 * @param prioID - Prioritaet ID
	 * @return PrioritaetEntity
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public PrioritaetEntity findByNr(int prioID) throws Exception
	{
		//Lokale Variablen der Methoden
		List<PrioritaetEntity> results = null;
		PrioritaetEntity prioritaet = null;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum finden eine Prioritaet zu einer prioID
		String queryString = "from PrioritaetEntity as pr where pr.prioID= :prioID";
		TypedQuery<PrioritaetEntity> query = em.createQuery(queryString, PrioritaetEntity.class);
		query.setParameter("prioID", prioID);
		results = query.getResultList();
		et.commit();	//Ende der Transaktion
		//Ueberpruefen, ob die Prioritaet gefunden wurde
		if(!results.isEmpty())
		{
			prioritaet = results.get(0);
		}
		else
		{
			throw new Exception();
		}
		return prioritaet;
	}
	
	/**
	 * Bekommt die Parameter zum erstellen einer neue Prioritaet und speichert sie in der Datenbank
	 * @param bezeichnung - Bezeichnung der Prioritaet
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void savePrioritaet(String bezeichnung) throws Exception
	{
		//Lokale Variablen der Methode
		PrioritaetEntity prioritaet = null;
		Integer prioID = 0;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum ermitteln der hoechsten prioID in der Datenbank
		String queryString = "select max(pr.prioID) from PrioritaetEntity as pr";
		TypedQuery<Integer> query = em.createQuery(queryString,Integer.class);
		prioID = query.getSingleResult();
		et.commit();	//Ende der Transaktion
		
		//Ermitteln einer neuen prioID
		if(prioID == null)
		{
			prioID = 1;
		}
		else
		{
			prioID++;
		}
		
		//Erstellen einer neuen Prioritaet
		prioritaet = new PrioritaetEntity(prioID,bezeichnung);
		
		et.begin();		//Anfang der Transaktion
		//Speichern der neuen Prioritaet
		em.persist(prioritaet);
		et.commit();	//Ende der Transaktion
	}
	
	/**
	 * Bekommt eine Prioritaet ID und Parameter zum aendern der Prioritaet und aendert die dazugehoerige
	 * Prioritaet in der Datenbank
	 * @param prioID - Prioritaet ID
	 * @param bezeichnung - Bezeichnung der Prioritaet
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void editPrioritaet(int prioID, String bezeichnung) throws Exception
	{
		//Lokale Variablen
		EntityTransaction et = em.getTransaction();
		//Finden der zu aendernden Prioritaet
		PrioritaetEntity prioritaet = findByNr(prioID);
		//Setzen der Parameter
		prioritaet.setBezeichnung(bezeichnung);
		
		et.begin();		//Anfang der Transaktion
		//Ueberschreiben der alten Prioritaet
		em.merge(prioritaet);
		et.commit();	//Ende der Transaktion
	}
	
	/**
	 * Bekommt eine Prioritaet ID und loescht die dazugehoerige Prioritaet in der Datenbank
	 * @param prioID - Prioritaet ID
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void deletePrioritaet(int prioID) throws Exception
	{
		//Lokale Variablen der Methode
		EntityTransaction et = em.getTransaction();
		//Finden der zu loeschenden Prioritaet
		PrioritaetEntity prioritaet = findByNr(prioID);

		et.begin();		//Anfang der Transaktion
		//Loeschen der Prioritaet in der Datenbank
		em.remove(prioritaet);
		et.commit();	//Ende der Transaktion
	}
}
