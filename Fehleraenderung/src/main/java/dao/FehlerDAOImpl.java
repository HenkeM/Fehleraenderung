package dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import domain.FehlerEntity;
import domain.NutzerEntity;
import domain.PrioritaetEntity;
import domain.ProjektEntity;
import domain.StatusEntity;

public class FehlerDAOImpl implements FehlerDAOI
{
//Attribute der Klasse
	private EntityManager em;
	
//Konstruktor der Klasse
	public FehlerDAOImpl()
	{
		em = EntityManagerSingleton.getInstance().getEM();
	}

//Implementationen der Methoden des Interfaces
	/**
	 * Liefert eine Liste aller Fehler in der Datenbank
	 * @return List<FehlerEntity> 
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public List<FehlerEntity> findAll() throws Exception
	{
		//Lokale Variablen der Methode
		List<FehlerEntity> fehlerliste = null;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum finden aller Fehler
		String queryString = "from FehlerEntity as f";
		TypedQuery<FehlerEntity> query = em.createQuery(queryString,FehlerEntity.class);
		fehlerliste = query.getResultList();
		et.commit();	//Ende der Transaktion
		return fehlerliste;
	}
	
	public List<FehlerEntity> findFehlerForProjekt(int proNr) throws Exception
	{
		//Lokale Variablen der Methode
		List<FehlerEntity> fehlerliste = null;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum finden aller Fehler, die zu einer proNr gehoeren
		String queryString = "from FehlerEntity as f where f.projekt.proNr = :proNr";
		TypedQuery<FehlerEntity> query = em.createQuery(queryString,FehlerEntity.class);
		query.setParameter("proNr", proNr);
		fehlerliste = query.getResultList();
		et.commit();	//Ende der Transaktion
		return fehlerliste;
	}
	
	/**
	 * Bekommt eine Fehler ID und liefert den Fehler zu dieser ID
	 * @param feNr - Fehler ID
	 * @return FehlerEntity
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public FehlerEntity findByNr(int feNr) throws Exception
	{
		//Lokale Variablen der Methode
		List<FehlerEntity> results = null;
		FehlerEntity fehler = null;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum finden eines Fehler zu einer feNr
		String queryString = "from FehlerEntity as f where f.feNr= :feNr";
		TypedQuery<FehlerEntity> query = em.createQuery(queryString, FehlerEntity.class);
		query.setParameter("feNr", feNr);
		results = query.getResultList();
		et.commit();	//Ende der Transaktion
		
		//Ueberpruefen, ob der Fehler gefunden wurde
		if(!results.isEmpty())
		{
			fehler = results.get(0);
		}
		else
		{
			throw new Exception();
		}
		return fehler;
	}
	
	/**
	 * Bekommt die Parameter zum erstellen eines Fehlers und speichert einen neuen Fehler
	 * in der Datenbank
	 * @param bezeichnung - Name fuer den Fehler
	 * @param beschreibung - Text, der den Fehler beschreibt
	 * @param prioritaet - PrioritaetEntity
	 * @param projekt - ProjektEntity
	 * @param status - StatusEntity
	 * @param erstelltVon - NutzerEntity
	 * @param zugewiesenAn - NutzerEntity
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void saveFehler(String bezeichnung, String beschreibung, PrioritaetEntity prioritaet,
			ProjektEntity projekt, StatusEntity status, NutzerEntity erstelltVon,
			NutzerEntity zugewiesenAn) throws Exception
	{
		//Lokale Variablen der Methode
		FehlerEntity fehler = null;
		Integer feNr = 0;
		EntityTransaction et = em.getTransaction();
		
		et.begin();		//Anfang der Transaktion
		//Query zum ermitteln der hoechsten feNr, die aktuell vergeben ist
		String queryString = "select max(f.feNr) from FehlerEntity as f";
		TypedQuery<Integer> query = em.createQuery(queryString,Integer.class);
		feNr = query.getSingleResult();
		et.commit();	//Ende der Transaktion
		
		//Ermitteln der neuen feNr
		if(feNr == null)
		{
			feNr = 1;
		}
		else
		{
			feNr++;
		}
		
		//Erstellen eines neuen Fehlers
		fehler = new FehlerEntity(feNr,bezeichnung, beschreibung, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), prioritaet, projekt, status, erstelltVon, zugewiesenAn);
		et.begin();		//Ende der Transaktion
		//Speichern des neuen Fehlers in der Datenbank
		em.persist(fehler);
		et.commit();
	}
	
	/**
	 * Bekommt eine Fehler ID und die Parameter, die in diesem Fehler geaendert werden sollen.
	 * @param feNr - Fehler ID
	 * @param bezeichnung - Name fuer den Fehler
	 * @param beschreibung - Text, der den Fehler beschreibt
	 * @param prioritaet - PrioritaetEntity
	 * @param projekt - ProjektEntity
	 * @param status - StatusEntity
	 * @param zugewiesenAn - NutzerEntity
	 * @throws Exception - Exception fuer die JPA Methoden
	 */
	public void editFehler(int feNr, String bezeichnung, String beschreibung, 
			PrioritaetEntity prioritaet, ProjektEntity projekt, StatusEntity status, 
			NutzerEntity zugewiesenAn) throws Exception
	{
		//Lokale Variablen der Methode
		EntityTransaction et = em.getTransaction();
		
		//Finden des zu aendernden Fehlers
		FehlerEntity fehler = findByNr(feNr);
		//Setzen der geaenderten Parameter in der FehlerEntity
		fehler.setBezeichnung(bezeichnung);
		fehler.setBeschreibung(beschreibung);
		fehler.setPrioritaet(prioritaet);
		fehler.setProjekt(projekt);
		fehler.setStatus(status);
		
		//Aendern des Zuweisungsdatums, wenn der Fehler neu zugewiesen wurde
		if(!fehler.getZugewiesenAn().equals(zugewiesenAn))
		{
			fehler.setZugewiesenAn(zugewiesenAn);
			fehler.setZugewiesenAm(Calendar.getInstance().getTime());
		}
		
		et.begin();		//Anfang der Transaktion
		//Ueberschreiben des alten Fehlers mit dem neuen
		em.merge(fehler);
		et.commit();	//Ende der Transaktion
	}
	
	/**
	 * Bekommt eine Fehler ID und entfernt diesen Fehler aus der Datenbank
	 * @param feNr - Fehler ID
	 * @throws Exception - Exception fuer die JPA Methoden
	 */
	public void deleteFehler(int feNr) throws Exception
	{
		//Lokale Variablen der Methode
		EntityTransaction et = em.getTransaction();
		//Finden des zu loeschenden Fehlers
		FehlerEntity fehler = findByNr(feNr);
		
		et.begin();		//Anfang der Transaktion
		em.remove(fehler);
		et.commit();	//Ende der Transaktion
	}

	/**
	 * Bekommt eine Fehler ID und liefert eine Liste aller Fehler, auf die dieser Fehler verweist
	 * @param feNr Fehler ID
	 * @return List<FehlerEntity>
	 * @throws Exception - Exception fuer die JPA Methoden
	 */
	public List<FehlerEntity> findFehlerVerweistAuf(int feNr) throws Exception
	{
		//Lokale Variablen der Methode
		List<FehlerEntity> verwieseneFehler = null;
		//Finden des aktuellen Fehlers
		FehlerEntity aktuellerFehler = findByNr(feNr);
		//Auslesen der Liste aller verwiesenen Fehler vom aktuellen Fehler
		verwieseneFehler = aktuellerFehler.getVerwiesenVon();
		return verwieseneFehler;
	}
	
	/**
	 * Bekommt eine FehlerEntity und eine Liste aller Fehler, auf die dieser Fehler verweist.
	 * Diese Liste ersetzt dann die alte Verweisliste
	 * @param fehler - FehlerEntity
	 * @param verweisliste - Liste aller verwiesenen Fehler
	 * @throws Exception - Exception fuer die JPA Methoden
	 */
	public void saveVerweise(FehlerEntity aktuellerFehler, List<FehlerEntity> verweisliste) throws Exception
	{		
		//Lokale Variablen der Methode
		EntityTransaction et = em.getTransaction();
		//Ersetzen der Verweisliste im aktuellen Fehler
		aktuellerFehler.setVerwiesenVon(verweisliste);
		
		et.begin();		//Anfang der Transaktion
		//Ueberschreiben des alten Fehlers mit dem neuen Fehler
		em.merge(aktuellerFehler);
		et.commit();	//Ende der Transaktion
	}
}
