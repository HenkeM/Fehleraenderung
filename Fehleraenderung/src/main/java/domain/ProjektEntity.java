package domain;

import javax.persistence.*;

/**
 * Diese Klasse ist die JPA Entity fuer die Datenbank-Tabelle "projekt"
 */
@Entity
@Table(name="projekt")
public class ProjektEntity
{
//Attribute der Klasse
	private int proNr;
	private String bezeichnung;
	
//Konstruktoren der Klasse	
	public ProjektEntity()
	{
		
	}
	
	public ProjektEntity(int proNr, String bezeichnung)
	{
		this.proNr = proNr;
		this.bezeichnung = bezeichnung;
	}

//Getter der Klasse
	@Id
	@Column(name="proNr")
	public int getProNr()
	{
		return proNr;
	}
	
	@Column(name="bezeichnung")
	public String getBezeichnung()
	{
		return bezeichnung;
	}

//Setter der Klasse
	public void setProNr(int proNr)
	{
		this.proNr = proNr;
	}

	public void setBezeichnung(String bezeichnung)
	{
		this.bezeichnung = bezeichnung;
	}
	
	
}
