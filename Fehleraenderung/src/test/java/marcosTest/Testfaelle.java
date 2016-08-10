package marcosTest;

import java.util.ArrayList;
import java.util.List;

import dao.StatusDAOImpl;
import domain.FehlerEntity;
import domain.StatusEntity;
import service.FehleranwendungServiceI;
import service.FehleranwendungServiceImpl;

public class Testfaelle
{
	FehleranwendungServiceI service;
	StatusDAOImpl statusDAO;
	
	public Testfaelle()
	{
		this.service = new FehleranwendungServiceImpl();
		this.statusDAO = new StatusDAOImpl();
	}
	
	public void manyToManyTest()
	{
		statusManyToMany();
	}
	
	public void projektTest()
	{
		
	}
	
	public void fehlerDatetest()
	{
		List<FehlerEntity> fehlerliste = service.getFehlerliste();
		for(int i=0;i<fehlerliste.size();i++)
		{
			System.out.println("Date "+i+": "+fehlerliste.get(i).getErstelltAm());
		}
	}
	
	public void fehlerSaveVerweis()
	{
		List<FehlerEntity> fehlerliste = service.getFehlerliste();
		List<FehlerEntity> verweisliste = new ArrayList<FehlerEntity>();
		verweisliste.add(fehlerliste.get(3));
		verweisliste.add(fehlerliste.get(5));
		verweisliste.add(fehlerliste.get(7));
		verweisliste.add(fehlerliste.get(9));
		verweisliste.add(fehlerliste.get(14));
		service.saveFehlerVerweise(1, verweisliste);
		System.out.println("save verweis done");
	}
	
	public void fehlerNeuerFehlerTest()
	{
		service.fehlerSpeichern("testfehler", "guck dir das datum an", 2, 1, 1, 1, 1);
		System.out.println("save done");
	}
	
	public void fehlerVerwieseneTest(int feNr)
	{
		List<FehlerEntity> trefferliste = service.getFehlerlisteVerwieseneFehlerByFehler(feNr);
		System.out.println("Anzahl Treffer: "+trefferliste.size());
		for(FehlerEntity test : trefferliste)
		{
				System.out.println("FeNr: "+test.getFeNr());
		}
	}
	
	public void fehlerNichtVerwieseneTest(int feNr)
	{
		List<FehlerEntity> trefferliste = service.getFehlerlisteNichtVerwieseneFehlerByFehler(feNr);
		System.out.println("Anzahl Treffer: "+trefferliste.size());
		for(FehlerEntity test : trefferliste)
		{
			if(trefferliste.contains(test))
			{
				System.out.println("FeNr: "+test.getFeNr());
			}
		}
	}
	
	public void statusFolgestatusTest(int staNr)
	{
		List<StatusEntity> testliste = service.getFolgestatusliste(staNr);
		for(int i=0;i<testliste.size();i++)
		{
			System.out.println("StaNr:       "+testliste.get(i).getStaNr());
			System.out.println("Bezeichnung: "+testliste.get(i).getBezeichnung());
		}
	}
	
	private void statusManyToMany()
	{
		List<StatusEntity> nachfolger = null;
		try
		{
			nachfolger = statusDAO.findStatusNachfolger(5);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Anzahl der Nachfolger :"+nachfolger.size());
		for(int i=0;i<nachfolger.size();i++)
		{
			System.out.println("Nr "+i+1);
			System.out.println("ID: "+nachfolger.get(i).getStaNr());
			System.out.println("Bezeichnung: "+nachfolger.get(i).getBezeichnung());
		}
	}
}
