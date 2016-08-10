package dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import domain.FehlerEntity;
import domain.KommentarEntity;
import domain.NutzerEntity;

public class KommentarDAOImpl implements KommentarDAOI
{
//Attribute der Klasse
	private EntityManager em;
	
//Konstruktor der Klasse
	public KommentarDAOImpl()
	{
		em = EntityManagerSingleton.getInstance().getEM();
	}
	
//Implementationen der Methoden des Interfaces
	/**
	 * Liefert eine Liste aller Kommentare in der Datenbank
	 * @return List<KommentarEntity>
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public List<KommentarEntity> findAll() throws Exception
	{
		//Lokale Variablen der Methode
		List<KommentarEntity> kommentare= null;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum finden aller Kommentare
		String queryString = "from KommentarEntity as k";
		TypedQuery<KommentarEntity> query = em.createQuery(queryString,KommentarEntity.class);
		kommentare = query.getResultList();
		et.commit();	//Ende der Transaktion
		return kommentare;
	}
	
	/**
	 * Bekommt eine Fehler ID und liefert eine Liste aller Kommentare zu diesem Fehler
	 * @param feNr - Fehler ID
	 * @return List<KommentarEntity>
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public List<KommentarEntity> findKommentarForFehler(int feNr) throws Exception
	{
		//Lokale Variablen der Methode
		List<KommentarEntity> kommentarliste = null;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum finden aller Kommentare zu einer feNr
		String queryString = "from KommentarEntity as k where k.fehler.feNr = :feNr";
		TypedQuery<KommentarEntity> query = em.createQuery(queryString,KommentarEntity.class);
		query.setParameter("feNr", feNr);
		kommentarliste = query.getResultList();
		et.commit();	//Ende der Transaktion
		return kommentarliste;
	}
	
	/**
	 * Bekommt eine Kommentar ID und liefert den dazugehoerigen Kommentar
	 * @param koNr - Kommentar ID
	 * @return KommentarEntity
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public KommentarEntity findByNr(int koNr) throws Exception
	{
		//Lokale Variablen der Methoden
		List<KommentarEntity> results = null;
		KommentarEntity kommentar = null;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum finden eines Kommentars zu einer koNr
		String queryString = "from KommentarEntity as k where k.koNr= :koNr";
		TypedQuery<KommentarEntity> query = em.createQuery(queryString, KommentarEntity.class);
		query.setParameter("koNr", koNr);
		results = query.getResultList();
		et.commit();	//Ende der Transaktion
		//Ueberpruefen, ob der Kommentar gefunden wurde
		if(!results.isEmpty())
		{
			kommentar = results.get(0);
		}
		else
		{
			throw new Exception();
		}
		return kommentar;
	}
	
	/**
	 * Bekommt die Parameter fuer einen neuen Fehler und speichert diesen in der Datenbank
	 * @param text - Der Text des Kommentars
	 * @param fehler - FehlerEntity
	 * @param nutzer - NutzerEntity
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void saveKommentar(String text, FehlerEntity fehler, NutzerEntity nutzer) throws Exception
	{
		//Lokale Variablen der Methode
		KommentarEntity kommentar = null;
		Integer koNr = 0;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum ermitteln der hoechsten koNr in der Datenbank
		String queryString = "select max(k.koNr) from KommentarEntity as k";
		TypedQuery<Integer> query = em.createQuery(queryString,Integer.class);
		koNr = query.getSingleResult();
		et.commit();	//Ende der Transaktion
		//Ermitteln einer neuen koNr
		if(koNr == null)
		{
			koNr = 1;
		}
		else
		{
			koNr++;
		}
		
		//Erstellen eines neuen Kommentares
		kommentar = new KommentarEntity(koNr, text, Calendar.getInstance().getTime(), fehler, nutzer);
	
		et.begin();		//Anfang der Transaktion
		//Speichern des neuen Kommentares
		em.persist(kommentar);
		et.commit();	//Ende der Transaktion
	}
	
	/**
	 * Bekommt eine Kommentar ID und die Parameter, die in diesem Kommentar geaendert werden sollen.
	 * @param koNr - Kommentar ID
	 * @param text - Der Text des Kommentars
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void editKommentar(int koNr, String text) throws Exception
	{
		//Lokale Variablen der Methode
		EntityTransaction et = em.getTransaction();
		//Finden des zu aendernden Kommentars
		KommentarEntity kommentar = findByNr(koNr);
		//Setzen der geaenderten Parameter
		kommentar.setText(text);
		
		et.begin();		//Anfang der Transaktion
		//Ueberschreiben des alten Kommentars
		em.merge(kommentar);
		et.commit();	//Ende der Transaktion
	}
	
	/**
	 * Bekommt eine Kommentar ID und loescht den dazugehoerigen Kommentar aus der Datenbank
	 * @param koNr - Kommentar ID
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void deleteKommentar(int koNr) throws Exception
	{
		//Lokale Variablen der Methode
		EntityTransaction et = em.getTransaction();
		//Finden des zu loeschenden Kommntars
		KommentarEntity kommentar = findByNr(koNr);

		et.begin();		//Anfang der Transaktion
		//Loeschen des Kommentars
		em.remove(kommentar);
		et.commit();	//Ende der Transaktion
	}
}
