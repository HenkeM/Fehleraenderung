package dao;

import java.util.List;

import domain.StatusEntity;

public interface StatusDAOI
{
	/**
	 * Liefert eine Liste aller Stati in der Datenbank
	 * @return List<StatusEntity>
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public List<StatusEntity> findAll() throws Exception;
	
	/**
	 * Bekommt eine Status ID und liefert den dazugehoerigen Status
	 * @param staNr - Status ID
	 * @return StatusEntity
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public StatusEntity findByNr(int staNr) throws Exception;
	
	/**
	 * Bekommt die Parameter zum erstellen eines neuen Status und speichert diesen in der Datenbank
	 * @param bezeichnung - Bezeichnung des Status
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void saveStatus(String bezeichnung) throws Exception;
	
	/**
	 * Bekommt eine Status ID und die Parameter zum aendern des Status und aendert diesen in 
	 * der Datenbank
	 * @param staNr - Status ID
	 * @param bezeichnung - Bezeichnung des Status
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void editStatus(int staNr, String bezeichnung) throws Exception;
	
	/**
	 * Bekommt eine Status ID und loescht den dazugehoerigen Status in der Datenbank
	 * @param staNr - Status ID
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public void deleteStatus(int staNr) throws Exception;
	
	/**
	 * Bekommt eine Status ID und liefert eine Liste aller Stati, die auf diesen Status folgen koennen
	 * @param staNr - Status ID
	 * @return List<StatusEntity>
	 * @throws Exception - Exceptions fuer die JPA Methoden
	 */
	public List<StatusEntity> findStatusNachfolger(int staNr) throws Exception;
}
