package service;

import java.util.ArrayList;
import java.util.List;

import dao.FehlerDAOI;
import dao.FehlerDAOImpl;
import dao.KommentarDAOI;
import dao.KommentarDAOImpl;
import dao.NutzerDAOI;
import dao.NutzerDAOImpl;
import dao.PrioritaetDAOI;
import dao.PrioritaetDAOImpl;
import dao.ProjektDAOI;
import dao.ProjektDAOImpl;
import dao.StatusDAOI;
import dao.StatusDAOImpl;
import domain.FehlerEntity;
import domain.KommentarEntity;
import domain.NutzerEntity;
import domain.PrioritaetEntity;
import domain.ProjektEntity;
import domain.StatusEntity;

public class FehleranwendungServiceImpl implements FehleranwendungServiceI
{
	ProjektDAOI projektDAO;
	FehlerDAOI fehlerDAO;
	KommentarDAOI kommentarDAO;
	NutzerDAOI nutzerDAO;
	PrioritaetDAOI prioritaetDAO;
	StatusDAOI statusDAO;
	
	public FehleranwendungServiceImpl()
	{
		this.projektDAO = new ProjektDAOImpl();
		this.fehlerDAO = new FehlerDAOImpl();
		this.kommentarDAO = new KommentarDAOImpl();
		this.nutzerDAO = new NutzerDAOImpl();
		this.prioritaetDAO = new PrioritaetDAOImpl();
		this.statusDAO = new StatusDAOImpl();
	}

	/**
	 * Liefert eine Liste aller existierenden Projekte in der Datenbank
	 * @return List<ProjektEntity>
	 */
	public List<ProjektEntity> getProjektliste()
	{
		//Lokale Variablen der Methode
		List<ProjektEntity> projektliste = null;
		
		try
		{
			projektliste = projektDAO.findAll();
		} catch (Exception e)
		{
			System.out.println("Fehler: getProjektliste fehlgeschlagen");
			e.printStackTrace();
		}
		return projektliste;
	}

	/**
	 * Liefert eine Liste aller existierenden Fehler in der Datenbank
	 * @return List<FehlerEntity>
	 */
	public List<FehlerEntity> getFehlerliste()
	{
		//Lokale Variablen der Methode
		List<FehlerEntity> fehlerliste = null;
		
		try
		{
			fehlerliste = fehlerDAO.findAll();
		} catch (Exception e)
		{
			System.out.println("Fehler: getFehlerliste fehlgeschlagen");
			e.printStackTrace();
		}
		return fehlerliste;
	}

	/**
	 * Liefert eine Liste aller existierenden Kommentare in der Datenbank
	 * @return List<KommentarEntity>
	 */
	public List<KommentarEntity> getKommentarliste()
	{
		//Lokale Variablen der Methode
		List<KommentarEntity> kommentarliste = null;
		
		try
		{
			kommentarliste = kommentarDAO.findAll();
		} catch (Exception e)
		{
			System.out.println("Fehler: getKommentarliste fehlgeschlagen");
			e.printStackTrace();
		}
		return kommentarliste;
	}

	/**
	 * Liefert eine Liste aller existierenden Nutzer in der Datenbank
	 * @return List<NutzerEntity>
	 */
	public List<NutzerEntity> getNutzerliste()
	{
		//Lokale Variablen der Methode
		List<NutzerEntity> nutzerliste = null;
		
		try
		{
			nutzerliste = nutzerDAO.findAll();
		} catch (Exception e)
		{
			System.out.println("Fehler: getNutzerliste fehlgeschlagen");
			e.printStackTrace();
		}
		return nutzerliste;
	}

	/**
	 * Liefert eine Liste aller existierenden Prioritaeten in der Datenbank
	 * @return List<PrioritaetEntity>
	 */
	public List<PrioritaetEntity> getPrioritaetliste()
	{
		//Lokale Variablen der Methode
		List<PrioritaetEntity> prioritaetliste = null;
		
		try
		{
			prioritaetliste = prioritaetDAO.findAll();
		} catch (Exception e)
		{
			System.out.println("Fehler: getPrioritaetliste fehlgeschlagen");
			e.printStackTrace();
		}
		return prioritaetliste;
	}

	/**
	 * Liefert eine Liste aller existierenden Status in der Datenbank
	 * @return List<StatusEntity>
	 */
	public List<StatusEntity> getStatusliste()
	{
		//Lokale Variablen der Methode
		List<StatusEntity> statusliste = null;
		
		try
		{
			statusliste = statusDAO.findAll();
		} catch (Exception e)
		{
			System.out.println("Fehler: getStatusliste fehlgeschlagen");
			e.printStackTrace();
		}
		return statusliste;
	}
	
	/**
	 * Bekommt eine Projekt ID und liefert eine Liste aller Fehler, die zu dieser
	 * Projekt ID gehoeren
	 * @param proNr - Projekt ID
	 * @return List<FehlerEntity>
	 */
	public List<FehlerEntity> getFehlerlisteForProjekt(int proNr)
	{
		//Lokale Variablen der Methode
		List<FehlerEntity> fehlerliste = null;
		try
		{
			fehlerliste = fehlerDAO.findFehlerForProjekt(proNr);
		} catch (Exception e)
		{
			System.out.println("Fehler: getFehlerlisteForProjekt fehlgeschlagen");
			e.printStackTrace();
		}
		return fehlerliste;
	}

	/**
	 * Bekommt eine Fehler ID und liefert eine Liste aller Kommentare, die zu dieser
	 * Fehler ID gehoeren
	 * @param feNr - Fehler ID
	 * @return List<KommentarEntity>
	 */
	public List<KommentarEntity> getKommentarlisteForFehler(int feNr)
	{
		//Lokale Variablen der Methode
		List<KommentarEntity> kommentarliste = null;
		
		try
		{
			kommentarliste = kommentarDAO.findKommentarForFehler(feNr);
		} catch (Exception e)
		{
			System.out.println("Fehler: getKommentarlisteForFehler fehlgeschlagen");
			e.printStackTrace();
		}
		return kommentarliste;
	}

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
	public void fehlerSpeichern(String bezeichnung, String beschreibung, int prioID, int proNr, int staNr,
			int nuNrErstelltVon, int nuNrZugewiesenAn)
	{
		//Lokale Variablen der Methode
		PrioritaetEntity prioritaet = null;
		ProjektEntity projekt = null;
		StatusEntity status = null;
		NutzerEntity erstelltVon = null;
		NutzerEntity zugewiesenAn = null;
		
		//Ermitteln aller Entitaeten zum Fehler speichern
		try
		{
			prioritaet = prioritaetDAO.findByNr(prioID);
		} catch (Exception e)
		{
			System.out.println("Fehler speichern fehlgeschlagen: Prioritaet konnte nicht gefunden werden");
			e.printStackTrace();
		}
		try
		{
			projekt = projektDAO.findByNr(proNr);
		} catch (Exception e)
		{
			System.out.println("Fehler speichern fehlgeschlagen: Projekt konnte nicht gefunden werden");
			e.printStackTrace();
		}
		try
		{
			status = statusDAO.findByNr(staNr);
		} catch (Exception e)
		{
			System.out.println("Fehler speichern fehlgeschlagen: Status konnte nicht gefunden werden");
			e.printStackTrace();
		}
		try
		{
			erstelltVon = nutzerDAO.findByNr(nuNrErstelltVon);
			zugewiesenAn = nutzerDAO.findByNr(nuNrZugewiesenAn);
		} catch (Exception e)
		{
			System.out.println("Fehler speichern fehlgeschlagen: Nutzer konnte nicht gefunden werden");
			e.printStackTrace();
		}
		//Speichern des Fehlers
		try
		{
			fehlerDAO.saveFehler(bezeichnung, beschreibung, prioritaet, projekt, status, erstelltVon, zugewiesenAn);
		} catch (Exception e)
		{
			System.out.println("Fehler speichern fehlgeschlagen: Fehler konnte nicht gespeichert werden");
			e.printStackTrace();
		}
	}

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
	public void fehlerAendern(int feNr, String bezeichnung, String beschreibung, int prioID, int proNr, int staNr,
			int nuNrZugewiesenAn)
	{
		//Lokale Variablen der Methode
		PrioritaetEntity prioritaet = null;
		ProjektEntity projekt = null;
		StatusEntity status = null;
		NutzerEntity zugewiesenAn = null;

		//Ermitteln aller Entitaeten zum aendern des Fehlers
		try
		{
			prioritaet = prioritaetDAO.findByNr(prioID);
		} catch (Exception e)
		{
			System.out.println("Fehler aendern fehlgeschlagen: Prioritaet konnte nicht gefunden werden");
			e.printStackTrace();
		}
		try
		{
			projekt = projektDAO.findByNr(proNr);
		} catch (Exception e)
		{
			System.out.println("Fehler aendern fehlgeschlagen: Projekt konnte nicht gefunden werden");
			e.printStackTrace();
		}
		try
		{
			status = statusDAO.findByNr(staNr);
		} catch (Exception e)
		{
			System.out.println("Fehler aendern fehlgeschlagen: Status konnte nicht gefunden werden");
			e.printStackTrace();
		}
		try
		{
			zugewiesenAn = nutzerDAO.findByNr(nuNrZugewiesenAn);
		} catch (Exception e)
		{
			System.out.println("Fehler aendern fehlgeschlagen: Nutzer konnte nicht gefunden werden");
			e.printStackTrace();
		}
		//Editieren des Fehlers
		try
		{
			fehlerDAO.editFehler(feNr, bezeichnung, beschreibung, prioritaet, projekt, status, zugewiesenAn);
		} catch (Exception e)
		{
			System.out.println("Fehler aendern fehlgeschlagen: Fehler konnte nicht geaendert werden");
			e.printStackTrace();
		}
	}

	/**
	 * Bekommt eine Fehler ID und loescht den dazugehoerigen Fehler aus der Datenbank
	 * @param feNr - Fehler ID
	 */
	public void deleteFehler(int feNr)
	{
		try
		{
			fehlerDAO.deleteFehler(feNr);
		} catch (Exception e)
		{
			System.out.println("Fehler konnte nicht geloescht werden");
			e.printStackTrace();
		}		
	}

	/**
	 * Speichert einen neuen Kommentar in der Datenbank mit den Werten, die als Parameter
	 * uebergeben werden.
	 * @param text - Text des Kommentars
	 * @param feNr - Fehler ID
	 * @param nuNr - Nutzer ID
	 */
	public void kommentarSpeichern(String text, int feNr, int nuNr)
	{
		//Lokale Variablen der Methode
		FehlerEntity fehler = null;
		NutzerEntity nutzer = null;
		
		//Ermitteln aller Entitaeten zum speichern des Kommentars
		try
		{
			fehler = fehlerDAO.findByNr(feNr);
		} catch (Exception e)
		{
			System.out.println("Kommentar speichern fehlgeschlagen: Fehler konnte nicht gefunden werden");
			e.printStackTrace();
		}
		try
		{
			nutzer = nutzerDAO.findByNr(nuNr);
		} catch (Exception e)
		{
			System.out.println("Kommentar speichern fehlgeschlagen: Nutzer konnte nicht gefunden werden");
			e.printStackTrace();
		}
		//Speichern des Kommentars
		try
		{
			kommentarDAO.saveKommentar(text, fehler, nutzer);
		} catch (Exception e)
		{
			System.out.println("Kommentar speichern fehlgeschlagen: Kommentar konnte nicht gespeichert werden");
			e.printStackTrace();
		}
	}

	/**
	 * Bekommt eine Kommentar ID und aendert den Kommentar in der Datenbank mit den
	 * Werten, die als Parameter uebergeben werden.
	 * @param koNr - Kommentar ID
	 * @param text - Text des Kommentars
	 */
	public void kommentarAendern(int koNr, String text)
	{
		//Editieren des Kommentars
		try
		{
			kommentarDAO.editKommentar(koNr, text);
		} catch (Exception e)
		{
			System.out.println("Kommentar aendern fehlgeschlagen: Kommentar konnte nicht geaendert werden");
			e.printStackTrace();
		}
		
	}

	/**
	 * Bekommt eine Kommentar ID und loescht den dazugehoerigen Kommentar aus der Datenbank
	 * @param koNr - Kommentar ID
	 */
	public void deleteKommentar(int koNr)
	{
		//Loeschen des Kommentars
		try
		{
			kommentarDAO.deleteKommentar(koNr);
		} catch (Exception e)
		{
			System.out.println("Kommentar konnte nicht geloescht werden");
			e.printStackTrace();
		}		
	}

	/**
	 * Bekommt eine Status ID und lieft eine Liste mit dem aktuellen Status als erstes, gefolgt von allen 
	 * Folgestati, die von diesem Status aus erreichbar sind
	 * @param staNr
	 * @return List<StatusEntity>
	 */
	public List<StatusEntity> getFolgestatusliste(int staNr)
	{
		//Lokale Variablen der Methode
		StatusEntity aktuellerStatus = null;
		List<StatusEntity> folgestatusliste = new ArrayList<StatusEntity>();
		List<StatusEntity> bufferliste = null;
		//Besorgen aller noetigen Entitaeten fuer die Methode
		try
		{
			aktuellerStatus = statusDAO.findByNr(staNr);
			bufferliste = statusDAO.findStatusNachfolger(staNr);
		} catch (Exception e)
		{
			System.out.println("Folgestatus konnte nicht abgerufen werden");
			e.printStackTrace();
		}
		//Zusammenfuegen der Ausgabeliste
		folgestatusliste.add(aktuellerStatus);
		for(int i=0;i<bufferliste.size();i++)
		{
			folgestatusliste.add(bufferliste.get(i));
		}
		return folgestatusliste;
	}

	/**
	 * Bekommt eine Fehler ID und liefert eine Liste aller Fehler, auf die dieser Fehler
	 * verweist
	 * @param proNr - Projekt ID
	 * @return List<FehlerEntity>
	 */
	public List<FehlerEntity> getFehlerlisteVerwieseneFehlerByFehler(int feNr)
	{
		//Lokale Variablen der Methode
		List<FehlerEntity> fehlerliste = null;
		//Besorgen der Verweisliste
		try
		{
			fehlerliste = fehlerDAO.findFehlerVerweistAuf(feNr);
		} catch (Exception e)
		{
			System.out.println("Verwiesene Fehler konnte nicht abgerufen werden");
			e.printStackTrace();
		}
		return fehlerliste;
	}

	/**
	 * Bekommt eine Fehler ID und liefert eine Liste aller Fehler, auf die dieser Fehler
	 * NICHT verweist
	 * @param proNr - Projekt ID
	 * @return List<FehlerEntity>
	 */
	public List<FehlerEntity> getFehlerlisteNichtVerwieseneFehlerByFehler(int feNr)
	{
		//Lokale Variablen der Methode
		FehlerEntity aktuellerFehler = null;
		List<FehlerEntity> verweisliste = null;
		List<FehlerEntity> fehlerliste = null;
		List<FehlerEntity> ergebnisliste = new ArrayList<FehlerEntity>();
		//Besorgen aller fuer die Methode noetigen Entitaeten
		try
		{
			aktuellerFehler = fehlerDAO.findByNr(feNr);
			verweisliste = fehlerDAO.findFehlerVerweistAuf(feNr);
			fehlerliste = fehlerDAO.findAll();
		} catch (Exception e)
		{
			System.out.println("Nicht verwiesene Fehler konnten nicht abgerufen werden");
			e.printStackTrace();
		}
		
		//Fuegt alle Fehler der Ergebnisliste hinzu, die nicht in der Verweisliste stehen
		for(FehlerEntity fehler: fehlerliste)
		{
			if(!(verweisliste.contains(fehler)))
			{
				ergebnisliste.add(fehler);
			}
		}
		//Entfernt den aktuellen Fehler aus der Ergebnisliste
		ergebnisliste.remove(aktuellerFehler);
		return ergebnisliste;
	}

	/**
	 * Bekommt eine Fehler ID und eine Liste aller Fehler, auf die dieser Fehler verweist
	 * @param feNr - Fehler ID
	 * @param verweisliste - Liste aller verwiesenen FehlerEntities
	 */
	public void saveFehlerVerweise(int aktuelleFeNr, List<FehlerEntity> verweisliste)
	{
		//Lokale Variablen der Methode
		FehlerEntity aktuellerFehler = null;
		//Besorgt die Entitaet des aktuellen Fehlers
		try
		{
			aktuellerFehler = fehlerDAO.findByNr(aktuelleFeNr);
		} catch (Exception e)
		{
			System.out.println("save Verweis fehlgeschlagen: aktueller Fehler konnte nicht gefunden werden");
			e.printStackTrace();
		}
		//Aendert die Verweisliste
		try
		{
			fehlerDAO.saveVerweise(aktuellerFehler, verweisliste);
		} catch (Exception e)
		{
			System.out.println("save Verweis fehlgeschlagen: Eintrag konnte nicht gespeichert werden");
			e.printStackTrace();
		}
	}

	/**
	 * Bekommt eine Fehler ID und liefert die dazugehoerige FehlerEntity aus der Datenbank zurueck
	 * @param feNr - Fehler ID
	 * @return FehlerEntity
	 */
	public FehlerEntity getFehlerByNr(int feNr)
	{
		//Lokale Variablen der Methode
		FehlerEntity fehler = null;
		//Besorgen des Fehlers aus der Datenbank
		try
		{
			fehler = fehlerDAO.findByNr(feNr);
		} catch (Exception e)
		{
			System.out.println("get Fehler fehlgeschlagen: Fehler konnte nicht gefunden werden");
			e.printStackTrace();
		}
		return fehler;
	}
}
