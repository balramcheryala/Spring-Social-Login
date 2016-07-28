package com.bridgelabz.ipl.database.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name ="PlayerDB")
public class PlayerModel {

	@Id
	@GeneratedValue
	@Column(name = "IpLID" )
	private Long id;
	
	@Column(name = "Name" ,unique=true, nullable=false  )
	private String name;
	@Column(name = "Role" )
	private String role;
	@Column(name = "Batting" )
	private String batting;
	@Column(name = "Bowler" )
	private String bowler;
	@Column(name = "Nation")
	private String nation;
	@Column(name = "DOB" )
	private Date dob;
	@Column(name = "Team" )
	private String team;
	

	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getBatting() {
		return batting;
	}
	public void setBatting(String batting) {
		this.batting = batting;
	}
	public String getBowler() {
		return bowler;
	}
	public void setBowler(String bowler) {
		this.bowler = bowler;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getDob() {
		DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
		return df.format(dob);
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
}
