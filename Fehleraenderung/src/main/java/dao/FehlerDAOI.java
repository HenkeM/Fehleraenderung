package dao;

import java.util.List;

import domain.FehlerEntity;
import domain.NutzerEntity;
import domain.PrioritaetEntity;
import domain.ProjektEntity;
import domain.StatusEntity;

public interface FehlerDAOI
{
	/**
	 * Liefert eine Liste aller Fehler in der Datenbank
	 * @return List<FehlerEntity> 
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public List<FehlerEntity> findAll() throws Exception;
	
	/**
	 * Bekommt eine Projekt ID und liefert eine Liste aller Fehler, die zu diesem Projekt gehoeren
	 * @param proNr - Projekt ID
	 * @return List<FehlerEntity>
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public List<FehlerEntity> findFehlerForProjekt(int proNr) throws Exception;
	
	/**
	 * Bekommt eine Fehler ID und liefert den Fehler zu dieser ID
	 * @param feNr - Fehler ID
	 * @return FehlerEntity
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public FehlerEntity findByNr(int feNr) throws Exception;
	
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
			NutzerEntity zugewiesenAn) throws Exception;
	
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
			NutzerEntity zugewiesenAn) throws Exception;
	
	/**
	 * Bekommt eine Fehler ID und entfernt diesen Fehler aus der Datenbank
	 * @param feNr - Fehler ID
	 * @throws Exception - Exception fuer die JPA Methoden
	 */
	public void deleteFehler(int feNr) throws Exception;
	
	/**
	 * Bekommt eine Fehler ID und liefert eine Liste aller Fehler, auf die dieser Fehler verweist
	 * @param feNr Fehler ID
	 * @return List<FehlerEntity>
	 * @throws Exception - Exception fuer die JPA Methoden
	 */
	public List<FehlerEntity> findFehlerVerweistAuf(int feNr) throws Exception;
	
	/**
	 * Bekommt eine FehlerEntity und eine Liste aller Fehler, auf die dieser Fehler verweist.
	 * Diese Liste ersetzt dann die alte Verweisliste 
	 * @param fehler - FehlerEntity
	 * @param verweisliste - Liste aller verwiesenen Fehler
	 * @throws Exception - Exception fuer die JPA Methoden
	 */
	public void saveVerweise(FehlerEntity fehler, List<FehlerEntity> verweisliste) throws Exception;
}
