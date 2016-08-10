package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import domain.StatusEntity;

public class StatusDAOImpl implements StatusDAOI
{
//Attribute der Klasse
	private EntityManager em;
	
//Konstruktor der Klasse
	public StatusDAOImpl()
	{
		em = EntityManagerSingleton.getInstance().getEM();
	}
	
//Implementationen der Methoden des Interfaces
	/**
	 * Liefert eine Liste aller Stati in der Datenbank
	 * @return List<StatusEntity>
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public List<StatusEntity> findAll() throws Exception
	{
		//Lokale Variablen der Methode
		List<StatusEntity> status = null;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum finde aller Stati in der Datenbank
		String queryString = "from StatusEntity as s";
		TypedQuery<StatusEntity> query = em.createQuery(queryString,StatusEntity.class);
		status = query.getResultList();
		et.commit();	//Ende der Transaktion
		return status;
	}
	
	/**
	 * Bekommt eine Status ID und liefert den dazugehoerigen Status
	 * @param staNr - Status ID
	 * @return StatusEntity
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public StatusEntity findByNr(int staNr) throws Exception
	{
		//Lokale Veriablen der Methode
		List<StatusEntity> results = null;
		StatusEntity status = null;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum finden eines Status zu einer staNr
		String queryString = "from StatusEntity as s where s.staNr= :staNr";
		TypedQuery<StatusEntity> query = em.createQuery(queryString, StatusEntity.class);
		query.setParameter("staNr", staNr);
		results = query.getResultList();
		et.commit();	//Ende der Transaktion
		//Ueberpruefen, ob der Status gefunden wurde
		if(!results.isEmpty())
		{
			status = results.get(0);
		}
		else
		{
			throw new Exception();
		}
		return status;
	}
	
	/**
	 * Bekommt die Parameter zum erstellen eines neuen Projektes und speichert diesen in der Datenbank
	 * @param bezeichnung - Name des Projektes
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void saveStatus(String bezeichnung) throws Exception
	{
		//Lokale Variablen der Methode
		StatusEntity status = null;
		Integer staNr = 0;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum ermitteln der hoechsten staNr in der Datenbank
		String queryString = "select max(s.staNr) from StatusEntity as s";
		TypedQuery<Integer> query = em.createQuery(queryString,Integer.class);
		staNr = query.getSingleResult();
		et.commit();	//Ende der Transaktion
		
		//Ermitteln einer neuen staNr
		if(staNr == null)
		{
			staNr = 1;
		}
		else
		{
			staNr++;
		}
		
		//Erstellen eines neuen Status
		status = new StatusEntity(staNr,bezeichnung);
		
		et.begin();		//Anfang der Transaktion
		//Speichern des Status
		em.persist(status);
		et.commit();	//Ende der Transaktion
	}
	
	/**
	 * Bekommt eine Status ID und die Parameter zum aendern des Status und aendert diesen in 
	 * der Datenbank
	 * @param staNr - Status ID
	 * @param bezeichnung - Bezeichnung des Status
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void editStatus(int staNr, String bezeichnung) throws Exception
	{
		//Lokale Variablen der Methode
		EntityTransaction et = em.getTransaction();
		//Finden des zu aendernden Status
		StatusEntity status = findByNr(staNr);
		//Setzen der Parameter
		status.setBezeichnung(bezeichnung);
		
		et.begin();		//Anfang der Transaktion
		//Ueberschreiben des alten Status
		em.merge(status);
		et.commit();	//Ende der Transaktion
	}
	
	/**
	 * Bekommt eine Status ID und loescht den dazugehoerigen Status in der Datenbank
	 * @param staNr - Status ID
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void deleteStatus(int staNr) throws Exception
	{
		//Lokale Variablen der Methode
		EntityTransaction et = em.getTransaction();
		//Finden des zu loeschenden Status
		StatusEntity status = findByNr(staNr);
		
		et.begin();		//Anfang der Transaktion
		//Loeschen des Status
		em.remove(status);
		et.commit();	//Ende der Transaktion
	}

	/**
	 * Bekommt eine Status ID und liefert eine Liste aller Stati, die auf diesen Status folgen koennen
	 * @param staNr - Status ID
	 * @return List<StatusEntity>
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public List<StatusEntity> findStatusNachfolger(int staNr) throws Exception
	{
		//Lokale Variablen der Methode
		List<StatusEntity> nachfolger = null;
		//Finden des aktuellen Status
		StatusEntity aktuellerStatus = findByNr(staNr);
		//Auslesen der Liste aller Stati, die auf den aktuellen Fehler folgen koennen
		nachfolger = aktuellerStatus.getAktuellerStatus();
		return nachfolger;
	}
}
