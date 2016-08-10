package controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import service.FehleranwendungServiceImpl;
import domain.FehlerEntity;
import domain.KommentarEntity;
import domain.NutzerEntity;



public class KommentarBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<KommentarEntity> kommentarliste;
	private int koNr;
	private int feNr;
	private String text;
	private Date erstelltAm;
	private FehlerEntity fehler;
	private NutzerEntity nutzer;
	private FehleranwendungServiceImpl service = new FehleranwendungServiceImpl() ;
	
	
	
	
	public KommentarBean() {
		super();
	//	this.kommentarliste=service.getKommentarlisteForFehler(feNr);
		// TODO Auto-generated constructor stub
	}
	public List<KommentarEntity> getKommentarliste() {
		this.kommentarliste=service.getKommentarlisteForFehler(feNr);
		
		return kommentarliste;
	}
	public void setKommentarliste(List<KommentarEntity> kommentarliste) {
		this.kommentarliste = kommentarliste;
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
	public Date getErstelltAm() {
		return erstelltAm;
	}
	public void setErstelltAm(Date erstelltAm) {
		this.erstelltAm = erstelltAm;
	}
	public FehlerEntity getFehler() {
		return fehler;
	}
	public void setFehler(FehlerEntity fehler) {
		this.fehler = fehler;
	}
	public NutzerEntity getNutzer() {
		return nutzer;
	}
	public void setNutzer(NutzerEntity nutzer) {
		this.nutzer = nutzer;
	}
	public FehleranwendungServiceImpl getService() {
		return service;
	}
	public void setService(FehleranwendungServiceImpl service) {
		this.service = service;
	}
	public int getFeNr() {
		return feNr;
	}
	public void setFeNr(int feNr) {
		this.feNr = feNr;
	}
	
	
}
