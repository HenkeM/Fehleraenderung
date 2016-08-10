package domain;

import javax.persistence.*;

/**
 * Diese Klasse ist die JPA Entity fuer die Datenbank-Tabelle "prioritaet"
 */
@Entity
@Table(name="prioritaet")
public class PrioritaetEntity
{
//Attribute der Klasse
	private int prioID;
	private String bezeichnung;
	
//Konstruktoren der Klasse
	public PrioritaetEntity()
	{
		
	}

	public PrioritaetEntity(int prioID, String bezeichnung)
	{
		this.prioID = prioID;
		this.bezeichnung = bezeichnung;
	}

//Getter der Klasse
	@Id
	@Column(name="prioID")
	public int getPrioID()
	{
		return prioID;
	}

	@Column(name="bezeichnung")
	public String getBezeichnung()
	{
		return bezeichnung;
	}

//Setter der Klasse
	public void setPrioID(int prioID)
	{
		this.prioID = prioID;
	}

	public void setBezeichnung(String bezeichnung)
	{
		this.bezeichnung = bezeichnung;
	}	
}
