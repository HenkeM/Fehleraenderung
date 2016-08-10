package dao;

import java.util.List;

import domain.NutzerEntity;

public interface NutzerDAOI
{
	/**
	 * Liefert eine Liste aller Nutzer
	 * @return List<NutzerEntity>
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public List<NutzerEntity> findAll() throws Exception;
	
	/**
	 * Bekommt eine Nutzer ID und liefert den Nutzer dazu
	 * @param nuNr - Nutzer ID
	 * @return NutzerEntity
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public NutzerEntity findByNr(int nuNr) throws Exception;
	
	/**
	 * Bekommt die Parameter zum erstellen eines neuen Nutzers und speichert ihn in der Datenbank
	 * @param vorname - Vorname des Nutzers
	 * @param name - Nachname des Nutzers
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void saveNutzer(String vorname, String name) throws Exception;
	
	/**
	 * Bekommt eine Nutzer ID und die Parameter zum aendern eines Nutzers und aendert den 
	 * dazugehoerigen Nutzer in der Datenbank
	 * @param nuNr - Nutzer ID
	 * @param vorname - Vorname des Nutzers
	 * @param name - Nachname des Nutzers
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void editNutzer(int nuNr, String vorname, String name) throws Exception;
	
	/**
	 * Bekommt eine Nutzer ID und loescht den dazugehoerigen Nutzer aus der Datenbank
	 * @param nuNr - Nutzer ID
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void deleteNutzer(int nuNr) throws Exception;
}
