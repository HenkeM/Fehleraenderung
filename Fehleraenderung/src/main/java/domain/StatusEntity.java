package domain;

import java.util.List;

import javax.persistence.*;

/**
 * Diese Klasse ist die JPA Entity fuer die Datenbank-Tabelle "status"
 */
@Entity
@Table(name="status")
public class StatusEntity
{
//Attribute der Klasse
	private int staNr;
	private String bezeichnung;
	private List<StatusEntity> aktuellerStatus;
	private List<StatusEntity> folgeStatus;

//Konstruktoren der Klasse
	public StatusEntity()
	{
		
	}

	public StatusEntity(int staNr, String bezeichnung)
	{
		this.staNr = staNr;
		this.bezeichnung = bezeichnung;
	}

//Getter der Klasse
	@Id
	@Column(name="staNr")
	public int getStaNr()
	{
		return staNr;
	}

	@Column(name="bezeichnung")
	public String getBezeichnung()
	{
		return bezeichnung;
	}
	
	@JoinTable(name = "statusAenderungen",
			joinColumns = {
			@JoinColumn(name = "vorgaenger", referencedColumnName = "staNr", nullable = false)},
			inverseJoinColumns = {
			@JoinColumn(name = "nachfolger", referencedColumnName = "staNr", nullable = false)})
	@ManyToMany
	@OrderBy("staNr ASC")
	public List<StatusEntity> getAktuellerStatus()
	{
		return aktuellerStatus;
	}

	@ManyToMany(mappedBy = "aktuellerStatus")
	@OrderBy("staNr ASC")
	public List<StatusEntity> getFolgeStatus()
	{
		return folgeStatus;
	}

//Setter der Klasse
	public void setStaNr(int staNr)
	{
		this.staNr = staNr;
	}

	public void setBezeichnung(String bezeichnung)
	{
		this.bezeichnung = bezeichnung;
	}

	public void setAktuellerStatus(List<StatusEntity> aktuellerStatus)
	{
		this.aktuellerStatus = aktuellerStatus;
	}

	public void setFolgeStatus(List<StatusEntity> folgeStatus)
	{
		this.folgeStatus = folgeStatus;
	}
}
