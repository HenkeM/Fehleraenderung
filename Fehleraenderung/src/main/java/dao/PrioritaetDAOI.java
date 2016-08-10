package dao;

import java.util.List;

import domain.PrioritaetEntity;

public interface PrioritaetDAOI
{
	/**
	 * Liefert eine Liste aller Prioritaeten in der Datenbank
	 * @return List<PrioritaetEntity>
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public List<PrioritaetEntity> findAll() throws Exception;
	
	/**
	 * Bekommt eine Prioritaet ID und liefert die dazugehoerige Prioritaet
	 * @param prioID - Prioritaet ID
	 * @return PrioritaetEntity
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public PrioritaetEntity findByNr(int prioID) throws Exception;
	
	/**
	 * Bekommt die Parameter zum erstellen einer neue Prioritaet und speichert sie in der Datenbank
	 * @param bezeichnung - Bezeichnung der Prioritaet
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void savePrioritaet(String bezeichnung) throws Exception;
	
	/**
	 * Bekommt eine Prioritaet ID und Parameter zum aendern der Prioritaet und aendert die dazugehoerige
	 * Prioritaet in der Datenbank
	 * @param prioID - Prioritaet ID
	 * @param bezeichnung - Bezeichnung der Prioritaet
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void editPrioritaet(int prioID, String bezeichnung) throws Exception;
	
	/**
	 * Bekommt eine Prioritaet ID und loescht die dazugehoerige Prioritaet in der Datenbank
	 * @param prioID - Prioritaet ID
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void deletePrioritaet(int prioID) throws Exception;
}
