package dao;

import java.util.List;

import domain.FehlerEntity;
import domain.KommentarEntity;
import domain.NutzerEntity;

public interface KommentarDAOI
{
	/**
	 * Liefert eine Liste aller Kommentare in der Datenbank
	 * @return List<KommentarEntity>
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public List<KommentarEntity> findAll() throws Exception;
	
	/**
	 * Bekommt eine Fehler ID und liefert eine Liste aller Kommentare zu diesem Fehler
	 * @param feNr - Fehler ID
	 * @return List<KommentarEntity>
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public List<KommentarEntity> findKommentarForFehler(int feNr) throws Exception;
	
	/**
	 * Bekommt eine Kommentar ID und liefert den dazugehoerigen Kommentar
	 * @param koNr - Kommentar ID
	 * @return KommentarEntity
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public KommentarEntity findByNr(int koNr) throws Exception;
	
	/**
	 * Bekommt die Parameter fuer einen neuen Fehler und speichert diesen in der Datenbank
	 * @param text - Der Text des Kommentars
	 * @param fehler - FehlerEntity
	 * @param nutzer - NutzerEntity
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void saveKommentar(String text, FehlerEntity fehler, NutzerEntity nutzer) throws Exception;
	
	/**
	 * Bekommt eine Kommentar ID und die Parameter, die in diesem Kommentar geaendert werden sollen.
	 * @param koNr - Kommentar ID
	 * @param text - Der Text des Kommentars
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void editKommentar(int koNr, String text) throws Exception;
	
	/**
	 * Bekommt eine Kommentar ID und loescht den dazugehoerigen Kommentar aus der Datenbank
	 * @param koNr - Kommentar ID
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void deleteKommentar(int koNr) throws Exception;
}
