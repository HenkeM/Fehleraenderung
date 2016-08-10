package domain;

import javax.persistence.*;

/**
 * Diese Klasse ist die JPA Entity fuer die Datenbank-Tabelle "nutzer"
 */
@Entity
@Table(name="nutzer")
public class NutzerEntity
{
//Attribute der Klasse
	private int nuNr;
	private String vorname;
	private String name;

//Konstruktoren der Klasse
	public NutzerEntity()
	{
		
	}
	
	public NutzerEntity(int nuNr, String vorname, String name)
	{
		this.nuNr = nuNr;
		this.vorname = vorname;
		this.name = name;
	}

//Getter der Klasse
	@Id
	@Column(name="nuNr")
	public int getNuNr()
	{
		return nuNr;
	}
	
	@Column(name="vorname")
	public String getVorname()
	{
		return vorname;
	}
	
	@Column(name="name")
	public String getName()
	{
		return name;
	}
	
//Setter der Klasse
	public void setNuNr(int nuNr)
	{
		this.nuNr = nuNr;
	}
	
	public void setVorname(String vorname)
	{
		this.vorname = vorname;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
}
