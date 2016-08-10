package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import domain.NutzerEntity;

public class NutzerDAOImpl implements NutzerDAOI
{
//Attribute der Klasse
	private EntityManager em;
	
//Konstruktor der Klasse
	public NutzerDAOImpl()
	{
		em = EntityManagerSingleton.getInstance().getEM();
	}

//Implementationen der Methoden des Interfaces
	/**
	 * Liefert eine Liste aller Nutzer
	 * @return List<NutzerEntity>
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public List<NutzerEntity> findAll() throws Exception
	{
		//Lokale Variablen der Methoden
		List<NutzerEntity> nutzer= null;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum finden aller Nutzer
		String queryString = "from NutzerEntity as n";
		TypedQuery<NutzerEntity> query = em.createQuery(queryString,NutzerEntity.class);
		nutzer = query.getResultList();
		et.commit();	//Ende der Transaktion
		return nutzer;
	}
	
	/**
	 * Bekommt eine Nutzer ID und liefert den Nutzer dazu
	 * @param nuNr - Nutzer ID
	 * @return NutzerEntity
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public NutzerEntity findByNr(int nuNr) throws Exception
	{
		//Lokale Variablen der Methoden
		List<NutzerEntity> results = null;
		NutzerEntity nutzer = null;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum finden eines Nutzer zu einer nuNr
		String queryString = "from NutzerEntity as n where n.nuNr= :nuNr";
		TypedQuery<NutzerEntity> query = em.createQuery(queryString, NutzerEntity.class);
		query.setParameter("nuNr", nuNr);
		results = query.getResultList();
		et.commit();	//Ende der Transaktion
		//Ueberpruefen, ob der Nutzer gefunden wurde
		if(!results.isEmpty())
		{
			nutzer = results.get(0);
		}
		else
		{
			throw new Exception();
		}
		return nutzer;
	}
	
	/**
	 * Bekommt die Parameter zum erstellen eines neuen Nutzers und speichert ihn in der Datenbank
	 * @param vorname - Vorname des Nutzers
	 * @param name - Nachname des Nutzers
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void saveNutzer(String vorname, String name) throws Exception
	{
		//Lokale Variablen der Methode
		NutzerEntity nutzer = null;
		Integer nuNr = 0;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum ermitteln der hoechsten nuNr in der Datenbank
		String queryString = "select max(n.nuNr) from NutzerEntity as n";
		TypedQuery<Integer> query = em.createQuery(queryString,Integer.class);
		nuNr = query.getSingleResult();
		et.commit();	//Ende der Transaktion
		
		//Ermitteln einer neuen nuNr
		if(nuNr == null)
		{
			nuNr = 1;
		}
		else
		{
			nuNr++;
		}
		
		//Erstellen eines neuen Nutzers
		nutzer = new NutzerEntity(nuNr,vorname,name);
		
		et.begin();		//Anfang der Transaktion
		//Speichern des neuen Nutzers
		em.persist(nutzer);
		et.commit();	//Ende der Transaktion
	}
	
	/**
	 * Bekommt eine Nutzer ID und die Parameter zum aendern eines Nutzers und aendert den 
	 * dazugehoerigen Nutzer in der Datenbank
	 * @param nuNr - Nutzer ID
	 * @param vorname - Vorname des Nutzers
	 * @param name - Nachname des Nutzers
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void editNutzer(int nuNr, String vorname, String name) throws Exception
	{
		//Lokale Variablen der Methoden
		EntityTransaction et = em.getTransaction();
		//Finden des zu aendernden Nutzers
		NutzerEntity nutzer = findByNr(nuNr);
		//Setzen der geaenderten Parameter
		nutzer.setVorname(vorname);
		nutzer.setName(name);
		
		et.begin();		//Anfang der Transaktion
		//Ueberschreiben des alten Nutzers mit dem neuen Nutzers
		em.merge(nutzer);
		et.commit();	//Ende der Transaktion
	}
	
	/**
	 * Bekommt eine Nutzer ID und loescht den dazugehoerigen Nutzer aus der Datenbank
	 * @param nuNr - Nutzer ID
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void deleteNutzer(int nuNr) throws Exception
	{
		//Lokale Variablen der Methode
		EntityTransaction et = em.getTransaction();
		//Finden des zu loeschenden Nutzers
		NutzerEntity nutzer = findByNr(nuNr);
		
		et.begin();		//Anfang der Transaktion
		//Loeschen des Nutzers aus der Datenbank
		em.remove(nutzer);
		et.commit();	//Ende der Transaktion
	}
}
