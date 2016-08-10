package dao;

import javax.persistence.*;

/**
 *	Diese Klasse ist ein Singleton fuer den EntityManager um sicherzustellen, dass zur Laufzeit
 *	nur eine Instanz des EntityManagers existiert
 */
public class EntityManagerSingleton
{
//Attribute fue die Klasse
	private static EntityManagerSingleton instance;
	private static EntityManager em;

//Private Konstruktor fuer die Klasse
	private EntityManagerSingleton()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Fehleraenderung");
		em = factory.createEntityManager();
	}

//Statische Funktion, um die Instanz des Singletons zu bekommen
	public static EntityManagerSingleton getInstance()
	{
		if (EntityManagerSingleton.instance == null)
		{
			EntityManagerSingleton.instance = new EntityManagerSingleton();
		}
	    return EntityManagerSingleton.instance;
	}
	
//Getter fuer den EntityManager
	public EntityManager getEM()
	{
		return EntityManagerSingleton.em;
	}
}
