package domain;

import java.util.Date;

import javax.persistence.*;

/**
 * Diese Klasse ist die JPA Entity fuer die Datenbank-Tabelle "kommentar"
 */
@Entity
@Table(name="kommentar")
public class KommentarEntity
{
//Attribute der Klasse
	private int koNr;
	private String text;
	private Date erstelltAm;
	private FehlerEntity fehler;
	private NutzerEntity nutzer;

//Konstruktoren der Klasse
	public KommentarEntity()
	{
		
	}

	public KommentarEntity(int koNr, String text, Date erstelltAm, FehlerEntity fehler, NutzerEntity nutzer)
	{
		this.koNr = koNr;
		this.text = text;
		this.erstelltAm = erstelltAm;
		this.fehler = fehler;
		this.nutzer = nutzer;
	}

//Getter der Klasse
	@Id
	@Column(name="koNr")
	public int getKoNr()
	{
		return koNr;
	}

	@Column(name="text")
	public String getText()
	{
		return text;
	}

	@Column(name="erstelltAm")
	public Date getErstelltAm()
	{
		return erstelltAm;
	}
	
	@ManyToOne()
    @JoinColumn(name = "feNr")
	public FehlerEntity getFehler()
	{
		return fehler;
	}

	@ManyToOne()
    @JoinColumn(name = "nuNr")
	public NutzerEntity getNutzer()
	{
		return nutzer;
	}
	
//Setter der Klasse
	public void setKoNr(int koNr)
	{
		this.koNr = koNr;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public void setErstelltAm(Date erstelltAm)
	{
		this.erstelltAm = erstelltAm;
	}

	public void setFehler(FehlerEntity fehler)
	{
		this.fehler = fehler;
	}

	public void setNutzer(NutzerEntity nutzer)
	{
		this.nutzer = nutzer;
	}
}
