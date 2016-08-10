package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Diese Klasse ist die JPA Entity fuer die Datenbank-Tabelle "fehler"
 */
@Entity
@Table(name="fehler")
public class FehlerEntity
{
//Attribute der Klasse
	private int feNr;
	private String bezeichnung;
	private String beschreibung;
	private Date erstelltAm;
	private Date zugewiesenAm;
	private PrioritaetEntity prioritaet;
	private ProjektEntity projekt;
	private StatusEntity status;
	private NutzerEntity erstelltVon;
	private NutzerEntity zugewiesenAn;
	private List<FehlerEntity> verwiesenVon;
	private List<FehlerEntity> verweistAuf;

//Konstruktoren der Klasse
	public FehlerEntity()
	{
		
	}

	public FehlerEntity(int feNr, String bezeichnung, String beschreibung, Date erstelltAm, Date zugewiesenAm,
			PrioritaetEntity prioritaet, ProjektEntity projekt, StatusEntity status, NutzerEntity erstelltVon,
			NutzerEntity zugewiesenAn)
	{
		this.feNr = feNr;
		this.bezeichnung = bezeichnung;
		this.beschreibung = beschreibung;
		this.erstelltAm = erstelltAm;
		this.zugewiesenAm = zugewiesenAm;
		this.prioritaet = prioritaet;
		this.projekt = projekt;
		this.status = status;
		this.erstelltVon = erstelltVon;
		this.zugewiesenAn = zugewiesenAn;
	}

//Getter der Klasse
	@Id
	@Column(name="feNr")
	public int getFeNr()
	{
		return feNr;
	}

	@Column(name="bezeichnung")
	public String getBezeichnung()
	{
		return bezeichnung;
	}

	@Column(name="beschreibung")
	public String getBeschreibung()
	{
		return beschreibung;
	}

	@Column(name="erstelltAm")
	public Date getErstelltAm()
	{
		return erstelltAm;
	}

	@Column(name="zugewiesenAm")
	public Date getZugewiesenAm()
	{
		return zugewiesenAm;
	}

	@ManyToOne()
    @JoinColumn(name = "prioID")
	public PrioritaetEntity getPrioritaet()
	{
		return prioritaet;
	}

	@ManyToOne()
    @JoinColumn(name = "proNr")
	public ProjektEntity getProjekt()
	{
		return projekt;
	}

	@ManyToOne()
    @JoinColumn(name = "staNr")
	public StatusEntity getStatus()
	{
		return status;
	}

	@ManyToOne()
    @JoinColumn(name = "erstelltVon")
	public NutzerEntity getErstelltVon()
	{
		return erstelltVon;
	}

	@ManyToOne()
    @JoinColumn(name = "zugewiesenAn")
	public NutzerEntity getZugewiesenAn()
	{
		return zugewiesenAn;
	}
	
	@JoinTable(name = "fehlerVerweise",
			joinColumns = {
			@JoinColumn(name = "vorgaenger", referencedColumnName = "feNr", nullable = false)},
			inverseJoinColumns = {
			@JoinColumn(name = "verweisAuf", referencedColumnName = "feNr", nullable = false)})
	@ManyToMany
	@OrderBy("feNr ASC")
	public List<FehlerEntity> getVerwiesenVon()
	{
		return verwiesenVon;
	}

	@ManyToMany(mappedBy = "verwiesenVon")
	@OrderBy("feNr ASC")
	public List<FehlerEntity> getVerweistAuf()
	{
		return verweistAuf;
	}

//Setter der Klasse
	public void setFeNr(int feNr)
	{
		this.feNr = feNr;
	}

	public void setBezeichnung(String bezeichnung)
	{
		this.bezeichnung = bezeichnung;
	}

	public void setBeschreibung(String beschreibung)
	{
		this.beschreibung = beschreibung;
	}

	public void setErstelltAm(Date erstelltAm)
	{
		this.erstelltAm = erstelltAm;
	}

	public void setZugewiesenAm(Date zugewiesenAm)
	{
		this.zugewiesenAm = zugewiesenAm;
	}

	public void setPrioritaet(PrioritaetEntity prioritaet)
	{
		this.prioritaet = prioritaet;
	}

	public void setProjekt(ProjektEntity projekt)
	{
		this.projekt = projekt;
	}

	public void setStatus(StatusEntity status)
	{
		this.status = status;
	}

	public void setErstelltVon(NutzerEntity erstelltVon)
	{
		this.erstelltVon = erstelltVon;
	}

	public void setZugewiesenAn(NutzerEntity zugewiesenAn)
	{
		this.zugewiesenAn = zugewiesenAn;
	}

	public void setVerwiesenVon(List<FehlerEntity> verwiesenVon)
	{
		this.verwiesenVon = verwiesenVon;
	}

	public void setVerweistAuf(List<FehlerEntity> verweistAuf)
	{
		this.verweistAuf = verweistAuf;
	}
}
