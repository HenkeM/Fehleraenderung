package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import domain.ProjektEntity;

public class ProjektDAOImpl implements ProjektDAOI
{
//Attribute der Klasse
	private EntityManager em;
	
//Konstruktor der Klasse
	public ProjektDAOImpl()
	{
		em = EntityManagerSingleton.getInstance().getEM();
	}
	
//Implementationen der Methoden des Interfaces
	/**
	 * Liefert eine Liste aller Projekte in der Datenbank
	 * @return List<ProjektEntity>
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public List<ProjektEntity> findAll() throws Exception
	{
		//Lokale Variablen der Methode
		List<ProjektEntity> projekte= null;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum finden aller Projekte
		String queryString = "from ProjektEntity as p";
		TypedQuery<ProjektEntity> query = em.createQuery(queryString,ProjektEntity.class);
		projekte = query.getResultList();
		et.commit();	//Ende der Transaktion
		return projekte;
	}
	
	/**
	 * Bekommt eine Projekt ID und liefert das Projekt dazu
	 * @param proNr - Projekt ID
	 * @return ProjektEntity
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public ProjektEntity findByNr(int proNr) throws Exception
	{
		//Lokale Variablen der Methoden
		List<ProjektEntity> results = null;
		ProjektEntity projekt = null;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum finden eines Projektes zu einer proNr
		String queryString = "from ProjektEntity as p where p.proNr= :proNr";
		TypedQuery<ProjektEntity> query = em.createQuery(queryString, ProjektEntity.class);
		query.setParameter("proNr", proNr);
		results = query.getResultList();
		et.commit();	//Ende der Transaktion
		//Ueberpruefen, ob das Projekt gefunden wurde
		if(!results.isEmpty())
		{
			projekt = results.get(0);
		}
		else
		{
			throw new Exception();
		}
		return projekt;
	}
	
	/**
	 * Bekommt die Parameter zum erstellen eines neuen Projektes und speichert diesen in der Datenbank
	 * @param bezeichnung - Name des Projektes
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void saveProjekt(String bezeichnung) throws Exception
	{
		//Lokale Variablen der Methoden
		ProjektEntity projekt = null;
		Integer proNr = 0;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum ermitteln der hoechsten proNr in der Datenbank
		String queryString = "select max(p.proNr) from ProjektEntity as p";
		TypedQuery<Integer> query = em.createQuery(queryString,Integer.class);
		proNr = query.getSingleResult();
		et.commit();	//Ende der Transaktion
		
		//Ermitteln der neuen proNr
		if(proNr == null)
		{
			proNr = 1;
		}
		else
		{
			proNr++;
		}
		
		//Erstellen eines neuen Projektes
		projekt = new ProjektEntity(proNr,bezeichnung);
		
		et.begin();		//Anfang der Transaktion
		//Speichern des Projektes in der Datenbank
		em.persist(projekt);
		et.commit();	//Ende der Transaktion
	}
	
	/**
	 * Bekommt eine Projekt ID und die Parameter zum aendern des Projektes und aendert dieses
	 * Projekt in der Datenbank
	 * @param proNr - Projekt ID
	 * @param bezeichnung - Name des Projektes
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void editProjekt(int proNr, String bezeichnung) throws Exception
	{
		//Lokale Variablen der Methoden
		EntityTransaction et = em.getTransaction();
		//Finden des zu aendernden Projektes
		ProjektEntity projekt = findByNr(proNr);
		//Setzen der Parameter
		projekt.setBezeichnung(bezeichnung);

		et.begin();		//Anfang der Transaktion
		//Ueberschreiben des Projektes
		em.merge(projekt);
		et.commit();	//Ende der Transaktion
	}
	
	/**
	 * Bekommt eine Projekt ID und loescht das dazugehoerige Projekt in der Datenbank
	 * @param proNr - Projekt ID
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void deleteProjekt(int proNr) throws Exception
	{
		//Lokale Variablen der Methode
		EntityTransaction et = em.getTransaction();
		//Finden des zu loeschenden Projektes
		ProjektEntity projekt = findByNr(proNr);
	
		et.begin();		//Anfang der Transaktion
		//Loeschen des Projektes
		em.remove(projekt);
		et.commit();	//Ende der Transaktion
	}
}
