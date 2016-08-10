package controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

import service.FehleranwendungServiceImpl;
import domain.FehlerEntity;
import domain.KommentarEntity;
import domain.NutzerEntity;
import domain.PrioritaetEntity;
import domain.ProjektEntity;
import domain.StatusEntity;


@ManagedBean(name="dtFehlerBean")
@SessionScoped
public class FehlerBean implements Serializable {




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int feNr;
	private List<FehlerEntity> fehlerliste;
	private List<FehlerEntity> fehlerlistetemp;
	private FehlerEntity fehler = new FehlerEntity();
	private String bezeichnung;
	private String beschreibung;
	private Date erstelltAm;
	private Date zugewiesenAm;
	private PrioritaetEntity prioritaet = new PrioritaetEntity();
	private StatusEntity status = new StatusEntity();
	private FehleranwendungServiceImpl service = new FehleranwendungServiceImpl();
	private ProjektEntity projekt = new ProjektEntity();
	private int projektnummer;
	private List<KommentarEntity> kommentarliste;
	private KommentarEntity kommentar = new KommentarEntity();
	private int koNr;
	private String text;
	private Date KoErstelltAm;
	private List<NutzerEntity> nutzerliste;
	private NutzerEntity nutzer = new NutzerEntity();
	private NutzerEntity kommentarnutzer = new NutzerEntity();
	private List<NutzerEntity> kommentarnutzerliste;
	private int nuNr;
	private String vorname;
	private String name;
	private String kommentartext;
	private NutzerEntity fehlernutzer = new NutzerEntity();
	private NutzerEntity fehlernutzerzugewiesen = new NutzerEntity();
	private List<PrioritaetEntity> prioliste; 
	private List<StatusEntity> folgestatusliste;
	private List<FehlerEntity> fehlerverweisliste;
	private List<FehlerEntity> nichtfehlerverweisliste;
	private FehlerEntity fehlerverweis;
	private DualListModel<FehlerEntity> fehlerverweispickliste;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String zugewiesenAmString;
	private String erstelltAmString;
	
	public DualListModel<FehlerEntity> getFehlerverweispickliste() {
		return fehlerverweispickliste;
	}


	public void setFehlerverweispickliste(
			DualListModel<FehlerEntity> fehlerverweispickliste) {
		this.fehlerverweispickliste = fehlerverweispickliste;
	}


	public List<KommentarEntity> getKommentarliste() {
		return kommentarliste;
	}


	public void setKommentarliste(List<KommentarEntity> kommentarliste) {

		this.kommentarliste = kommentarliste;
	}


	public KommentarEntity getKommentar() {
		return kommentar;
	}


	public void setKommentar(KommentarEntity kommentar) {
		this.kommentar = kommentar;
	}

	
	/*
	 *fehlerdetail.xhtml nimmt den Kommentartext des Nutzers und dessen Nutzernummer entgegen, generiert das aktuelle
	 *Datum f�r erstelltAm, erzeugt ein tempor舐es Kommentar-Object, speichert dieses �ber eine Methose der Service-Klasse
	 *in der Datenbank ab, erzeugt eine Meldung, die auf der angezeigten Seite eingeblendet wird */

	public void addEmptyKommentar(){//ActionEvent actionEvent
		//Zum Testen 
		KommentarEntity tempkom = new KommentarEntity();
		Date date = new Date();		
		//nutzer.setNuNr(service.getNutzerliste().size());
		//nutzer.setVorname("Test");
		//nutzer.setName("Nutzer");
		tempkom.setText(kommentartext);
		tempkom.setKoNr(0);
		tempkom.setNutzer(kommentarnutzer);	
		tempkom.setErstelltAm(date);
		kommentarliste.add(tempkom);
		service.kommentarSpeichern(tempkom.getText(), feNr, kommentarnutzer.getNuNr());
		kommentarliste = service.getKommentarlisteForFehler(feNr);
		FacesMessage msg = new FacesMessage("Neuer Kommentar hinzugef�gt!","Fehler-Nr. "+feNr+" sind jetzt "+Integer.toString(service.getKommentarlisteForFehler(feNr).size())+" Kommentare zugeordnet" );
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		kommentartext ="";

	}



/*
 * Konstruktor
 * initialisiert auch eine Liste mit Nutzer-Objekten*/
	public FehlerBean() {
		super();
		
		this.fehlerliste =service.getFehlerliste();
		fehler = fehlerliste.get(0);
		this.kommentarnutzerliste =service.getNutzerliste();
		this.prioliste = service.getPrioritaetliste();
	}


	public int getKoNr() {
		return koNr;
	}



	public void setKoNr(int koNr) {
		this.koNr = koNr;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}



	public Date getKoErstelltAm() {
		return KoErstelltAm;
	}



	public void setKoErstelltAm(Date koErstelltAm) {
		KoErstelltAm = koErstelltAm;
	}







	public int getProjektnummer() {

		return projektnummer;
	}



	public void setProjektnummer(int projektnummer) {
		this.projektnummer = projektnummer;
	}



	public FehlerEntity getFehler() {
		return fehler;
	}

/*
 * Wird ausgef�hrt wenn auf der Seite fehleransicht.xhtml in der Tabelle ein Fehler angeklickt wird
 * setzt entsprechend Attribute des Fehler-Objekts sowie die Liste mit verwiesenen und zu verweisenden Fehlern und
 * Stati*/
	public void setFehler(FehlerEntity fehler) {
		this.fehler = fehler;

		setProjekt(fehler.getProjekt());
		setProjektnummer(projekt.getProNr());
		feNr = fehler.getFeNr();
		kommentarliste = service.getKommentarlisteForFehler(feNr);
		bezeichnung = fehler.getBezeichnung();
		beschreibung = fehler.getBeschreibung();
		erstelltAm = fehler.getErstelltAm();
		zugewiesenAm = fehler.getZugewiesenAm();
		zugewiesenAmString = dateFormat.format(zugewiesenAm);
		prioritaet = fehler.getPrioritaet();
		status=fehler.getStatus();
		prioritaet = fehler.getPrioritaet();
		fehlernutzer = fehler.getErstelltVon();
		fehlernutzerzugewiesen = fehler.getZugewiesenAn();
		folgestatusliste = 	service.getFolgestatusliste(status.getStaNr());
		fehlerverweisliste = service.getFehlerlisteVerwieseneFehlerByFehler(feNr);
		nichtfehlerverweisliste = service.getFehlerlisteNichtVerwieseneFehlerByFehler(feNr);
		//TODO
		fehlerverweispickliste =  new DualListModel<FehlerEntity>();
		fehlerverweispickliste.setSource(nichtfehlerverweisliste); 
		fehlerverweispickliste.setTarget(fehlerverweisliste);


		//	fehlerlistetemp.clear();
		//	fehlerlistetemp.add(fehler);

	}



	public List<FehlerEntity> getFehlerlistetemp() {
		return fehlerlistetemp;
	}

	public void setFehlerlistetemp(List<FehlerEntity> fehlerlistetemp) {
		this.fehlerlistetemp = fehlerlistetemp;
	}

	public int getFeNr() {
		return feNr;
	}
	public void setFeNr(int feNr) {
		this.feNr = feNr;
	}

	public List<FehlerEntity> getFehlerliste() {

		return fehlerliste;
	}
	public void setFehlerliste(List<FehlerEntity> fehlerliste) {
		this.fehlerliste = fehlerliste;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public Date getErstelltAm() {
		return erstelltAm;
	}
	public void setErstelltAm(Date erstelltAm) {
		this.erstelltAm = erstelltAm;
	}
	public Date getZugewiesenAm() {
		return zugewiesenAm;
	}
	public void setZugewiesenAm(Date zugewiesenAm) {
		this.zugewiesenAm = zugewiesenAm;
	}
	public PrioritaetEntity getPrioritaet() {
		return prioritaet;
	}
	public void setPrioritaet(PrioritaetEntity prioritaet) {
		this.prioritaet = prioritaet;
	}

	/*
	 * Listener f�r den ﾄnderungen best舩igen-Button in der Kommentartabelle auf fehlerdetail.xhtml*/
	public void onRowEdit(RowEditEvent event) {
		try {
			saveKommentar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesMessage msg = new FacesMessage("Kommentar ge舅dert:", Integer.toString(((KommentarEntity) event.getObject()).getKoNr()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/*
	 * Listener f�r den ﾄnderungen abbrechen-Button in der Kommentartabelle auf fehlerdetail.xhtml*/
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("ﾄnderung abgebrochen",Integer.toString(((KommentarEntity) event.getObject()).getKoNr()));
		FacesContext.getCurrentInstance().addMessage(null, msg );
	}
	
	/*
	 * Listener f�r den (Fehler-)ﾄnderungen Speichern-Button auf fehlerdetail.xhtml*/
	public void buttonAction(ActionEvent actionEvent) throws Exception {
		saveFehler();
		FacesMessage msg = new FacesMessage("Fehler舅derung(en) erfolgreich gesichert!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowSelect(SelectEvent event) {       

	}
	/*
	public String signOff(ActionEvent actionEvent)  {
		
		kommentarnutzer.setName("");
		kommentarnutzer.setVorname("");
		kommentarnutzer.setNuNr(0);
		FacesMessage msg = new FacesMessage("Sie wurden abgemeldet!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "index.xhtml";
	}*/

	/*
	 * Listener f�r die Dropdownliste zur Auswahl des Nutzers an den der Fehler zugewiesen werden soll auf fehlerdetail.xhtml*/
	public void assignChange(AjaxBehaviorEvent AjaxBehaviorEvent){

		Date date = new Date();
		zugewiesenAm = date;
		zugewiesenAmString= dateFormat.format(zugewiesenAm);
	}


	public ProjektEntity getProjekt() {
		return projekt;
	}


	public void setProjekt(ProjektEntity projekt) {
		this.projekt = projekt;
	}


	public NutzerEntity getNutzer() {
		return nutzer;
	}


	public void setNutzer(NutzerEntity nutzer) {
		this.nutzer = nutzer;
	}


	public int getNuNr() {
		return nuNr;
	}


	public void setNuNr(int nuNr) {
		this.nuNr = nuNr;
	}


	public String getVorname() {
		return vorname;
	}


	public void setVorname(String vorname) {
		this.vorname = vorname;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<NutzerEntity> getNutzerliste() {
		return nutzerliste;
	}


	public void setNutzerliste(List<NutzerEntity> nutzerliste) {
		this.nutzerliste = nutzerliste;
	}

	/*
	 *Wird ein Kommentar ge舅dert, wird die Arrayliste der Kommentare durchlaufen und jedes Kommentar-Objekt in der Datenbank gespeichert
	 *f�r die Aktualisierung der Ansicht wird die Liste danach neu geladen  */
	public void saveKommentar() throws Exception{
		//KommentarDAOImpl saveKom = new KommentarDAOImpl();
		int n;
		KommentarEntity ko = new KommentarEntity();
		String s ="";



		for(int i=0;i<kommentarliste.size();i++){

			ko = kommentarliste.get(i);
			s = ko.getText();
			n =ko.getNutzer().getNuNr();
			service.kommentarAendern(n, s);
			// saveKom.editKommentar(n, s);

		}
		kommentarliste = service.getKommentarlisteForFehler(feNr);
		//bei full request anstatt ajax werden so messages f�r events gemacht 
		// FacesContext context = FacesContext.getCurrentInstance();
		// context.addMessage("Information:", new FacesMessage("Kommentar(e) erfolgreich gesichert!"));

	}
	
	/*
	 *Speichert die ﾄnderungen des Fehlers in der Datenbank */
	public String saveFehler() throws Exception{


		service.fehlerAendern(feNr, bezeichnung, beschreibung, prioritaet.getPrioID(), projekt.getProNr(), status.getStaNr(), fehlernutzerzugewiesen.getNuNr());
		service.saveFehlerVerweise(feNr, fehlerverweispickliste.getTarget());

		//FehlerDAOImpl fdao = new FehlerDAOImpl();

		//zugewiesenAm = fdao.findByNr(feNr).getZugewiesenAm();	


		// FacesContext context = FacesContext.getCurrentInstance(); Non Ajax
		//   context.addMessage("Information:", new FacesMessage("Fehler舅derung(en) erfolgreich gesichert!"));
		return null;
	}




	public List<NutzerEntity> getKommentarnutzerliste() {
		return kommentarnutzerliste;
	}


	public void setKommentarnutzerliste(List<NutzerEntity> kommentarnutzerliste) {
		this.kommentarnutzerliste = kommentarnutzerliste;
	}


	public NutzerEntity getKommentarnutzer() {
		return kommentarnutzer;
	}


	public void setKommentarnutzer(NutzerEntity kommentarnutzer) {
		this.kommentarnutzer = kommentarnutzer;
	}


	public String getKommentartext() {
		return kommentartext;
	}


	public void setKommentartext(String kommentartext) {
		this.kommentartext = kommentartext;
	}


	public StatusEntity getStatus() {
		return status;
	}


	public void setStatus(StatusEntity status) {
		this.status = status;
	}


	public NutzerEntity getFehlernutzer() {
		return fehlernutzer;
	}


	public void setFehlernutzer(NutzerEntity fehlernutzer) {
		this.fehlernutzer = fehlernutzer;
	}


	public NutzerEntity getFehlernutzerzugewiesen() {
		return fehlernutzerzugewiesen;
	}


	public void setFehlernutzerzugewiesen(NutzerEntity fehlernutzerzugewiesen) {
		this.fehlernutzerzugewiesen = fehlernutzerzugewiesen;
	}


	public List<PrioritaetEntity> getPrioliste() {
		return prioliste;
	}


	public void setPrioliste(List<PrioritaetEntity> prioliste) {
		this.prioliste = prioliste;
	}


	public List<StatusEntity> getFolgestatusliste() {
		return folgestatusliste;
	}


	public void setFolgestatusliste(List<StatusEntity> folgestatusliste) {
		this.folgestatusliste = folgestatusliste;
	}


	public List<FehlerEntity> getFehlerverweisliste() {
		return fehlerverweisliste;
	}


	public void setFehlerverweisliste(List<FehlerEntity> fehlerverweisliste) {
		this.fehlerverweisliste = fehlerverweisliste;
	}


	public FehlerEntity getFehlerverweis() {
		return fehlerverweis;
	}


	public void setFehlerverweis(FehlerEntity fehlerverweis) {
		this.fehlerverweis = fehlerverweis;
	}


	public List<FehlerEntity> getNichtfehlerverweisliste() {
		return nichtfehlerverweisliste;
	}


	public void setNichtfehlerverweisliste(List<FehlerEntity> nichtfehlerverweisliste) {
		this.nichtfehlerverweisliste = nichtfehlerverweisliste;
	}


	public String getZugewiesenAmString() {
		return zugewiesenAmString;
	}


	public void setZugewiesenAmString(String zugewiesenAmString) {
		this.zugewiesenAmString = zugewiesenAmString;
	}


	public String getErstelltAmString() {
		return erstelltAmString;
	}


	public void setErstelltAmString(String erstelltAmString) {
		this.erstelltAmString = erstelltAmString;
	}



}
