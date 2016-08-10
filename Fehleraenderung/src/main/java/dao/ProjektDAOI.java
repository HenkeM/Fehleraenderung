package dao;

import java.util.List;

import domain.ProjektEntity;

public interface ProjektDAOI
{
	/**
	 * Liefert eine Liste aller Projekte in der Datenbank
	 * @return List<ProjektEntity>
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public List<ProjektEntity> findAll() throws Exception;
	
	/**
	 * Bekommt eine Projekt ID und liefert das Projekt dazu
	 * @param proNr - Projekt ID
	 * @return ProjektEntity
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public ProjektEntity findByNr(int proNr) throws Exception;
	
	/**
	 * Bekommt die Parameter zum erstellen eines neuen Projektes und speichert diesen in der Datenbank
	 * @param bezeichnung - Name des Projektes
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void saveProjekt(String bezeichnung) throws Exception;
	
	/**
	 * Bekommt eine Projekt ID und die Parameter zum aendern des Projektes und aendert dieses
	 * Projekt in der Datenbank
	 * @param proNr - Projekt ID
	 * @param bezeichnung - Name des Projektes
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void editProjekt(int proNr, String bezeichnung) throws Exception;
	
	/**
	 * Bekommt eine Projekt ID und loescht das dazugehoerige Projekt in der Datenbank
	 * @param proNr - Projekt ID
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void deleteProjekt(int proNr) throws Exception;
}
