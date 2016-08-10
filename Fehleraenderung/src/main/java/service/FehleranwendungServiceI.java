package service;

import java.util.List;

import domain.FehlerEntity;
import domain.KommentarEntity;
import domain.NutzerEntity;
import domain.PrioritaetEntity;
import domain.ProjektEntity;
import domain.StatusEntity;

public interface FehleranwendungServiceI
{
	
//Methoden fuer FehlerEntity
	/**
	 * Liefert eine Liste aller existierenden Fehler in der Datenbank
	 * @return List<FehlerEntity>
	 */
	public List<FehlerEntity> getFehlerliste();

	/**
	 * Bekommt eine Projekt ID und liefert eine Liste aller Fehler, die zu dieser
	 * Projekt ID gehoeren
	 * @param proNr - Projekt ID
	 * @return List<FehlerEntity>
	 */
	public List<FehlerEntity> getFehlerlisteForProjekt(int proNr);
	
	/**
	 * Bekommt eine Fehler ID und liefert eine Liste aller Fehler, auf die dieser Fehler
	 * verweist
	 * @param proNr - Projekt ID
	 * @return List<FehlerEntity>
	 */
	public List<FehlerEntity> getFehlerlisteVerwieseneFehlerByFehler(int feNr);
	
	/**
	 * Bekommt eine Fehler ID und liefert eine Liste aller Fehler, auf die dieser Fehler
	 * NICHT verweist
	 * @param proNr - Projekt ID
	 * @return List<FehlerEntity>
	 */
	public List<FehlerEntity> getFehlerlisteNichtVerwieseneFehlerByFehler(int feNr);
	
	/**
	 * Bekommt eine Fehler ID und liefert die dazugehoerige FehlerEntity aus der Datenbank zurueck
	 * @param feNr - Fehler ID
	 * @return FehlerEntity
	 */
	public FehlerEntity getFehlerByNr(int feNr);
	
	/**
	 * Speichert einen neuen Fehler in der Datenbank mit den Werten, die als Parameter
	 * uebergeben werden.
	 * @param bezeichnung - Name des Fehlers
	 * @param beschreibung - Text, der den Fehler beschreibt
	 * @param prioID - Prioritaet ID
	 * @param proNr - Projekt ID
	 * @param staNr - Status ID
	 * @param nuNrErstelltVon - Nutzer ID
	 * @param nuNrZugewiesenAn - Nutzer ID
	 */
	public void fehlerSpeichern(String bezeichnung, String beschreibung, int prioID,
			int proNr, int staNr, int nuNrErstelltVon, int nuNrZugewiesenAn);
	
	/**
	 * Bekommt eine Fehler ID und aendert den Fehler in der Datenbank mit den
	 * Werten, die als Parameter uebergeben werden.
	 * @param feNr - Fehler ID
	 * @param bezeichnung - Name des Fehlers
	 * @param beschreibung - Text, der den Fehler beschreibt
	 * @param prioID - Prioritaet ID
	 * @param proNr - Projekt ID
	 * @param staNr - Status ID
	 * @param NuNrZugewiesenAn - Nutzer ID
	 */
	public void fehlerAendern(int feNr, String bezeichnung, String beschreibung, 
			int prioID, int proNr, int staNr, int NuNrZugewiesenAn);
	
	/**
	 * Bekommt eine Fehler ID und loescht den dazugehoerigen Fehler aus der Datenbank
	 * @param feNr - Fehler ID
	 */
	public void deleteFehler(int feNr);
	
	/**
	 * Bekommt eine Fehler ID und eine Liste aller Fehler, auf die dieser Fehler verweist
	 * @param feNr - Fehler ID
	 * @param verweisliste - Liste aller verwiesenen FehlerEntities
	 */
	public void saveFehlerVerweise(int aktuelleFeNr, List<FehlerEntity> verweisliste);
	
	
	
//Methode fuer KommentarEntity
	/**
	 * Liefert eine Liste aller existierenden Kommentare in der Datenbank
	 * @return List<KommentarEntity>
	 */
	public List<KommentarEntity> getKommentarliste();
	
	/**
	 * Bekommt eine Fehler ID und liefert eine Liste aller Kommentare, die zu dieser
	 * Fehler ID gehoeren
	 * @param feNr - Fehler ID
	 * @return List<KommentarEntity>
	 */
	public List<KommentarEntity> getKommentarlisteForFehler(int feNr);
	
	/**
	 * Speichert einen neuen Kommentar in der Datenbank mit den Werten, die als Parameter
	 * uebergeben werden.
	 * @param text - Text des Kommentars
	 * @param feNr - Fehler ID
	 * @param nuNr - Nutzer ID
	 */
	public void kommentarSpeichern(String text, int feNr, int nuNr);
	
	/**
	 * Bekommt eine Kommentar ID und aendert den Kommentar in der Datenbank mit den
	 * Werten, die als Parameter uebergeben werden.
	 * @param koNr - Kommentar ID
	 * @param text - Text des Kommentars
	 */
	public void kommentarAendern(int koNr, String text);
	
	/**
	 * Bekommt eine Kommentar ID und loescht den dazugehoerigen Kommentar aus der Datenbank
	 * @param koNr - Kommentar ID
	 */
	public void deleteKommentar(int koNr);
	
	
	
//Methoden fuer StatusEntity
	/**
	 * Liefert eine Liste aller existierenden Status in der Datenbank
	 * @return List<StatusEntity>
	 */
	public List<StatusEntity> getStatusliste();
	
	/**
	 * Bekommt eine Status ID und lieft eine Liste mit dem aktuellen Status als erstes, gefolgt von allen 
	 * Folgestati, die von diesem Status aus erreichbar sind
	 * @param staNr
	 * @return List<StatusEntity>
	 */
	public List<StatusEntity> getFolgestatusliste(int staNr);
		
	
	
//Methoden fuer ProjekEntity
	/**
	 * Liefert eine Liste aller existierenden Projekte in der Datenbank
	 * @return List<ProjektEntity>
	 */
	public List<ProjektEntity> getProjektliste();
	
		
	
//Methoden fuer NutzerEntity
	/**
	 * Liefert eine Liste aller existierenden Nutzer in der Datenbank
	 * @return List<NutzerEntity>
	 */
	public List<NutzerEntity> getNutzerliste();

	
	
//Methoden fuer PrioritaetEntity
	/**
	 * Liefert eine Liste aller existierenden Prioritaeten in der Datenbank
	 * @return List<PrioritaetEntity>
	 */
	public List<PrioritaetEntity> getPrioritaetliste();
}
