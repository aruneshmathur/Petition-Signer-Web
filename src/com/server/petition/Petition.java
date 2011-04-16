package com.server.petition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(detachable = "true")
public class Petition {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String petitionTitle;

	@Persistent
	private String petitionSummary;

	@Persistent
	private String petitionWeb;

	@Persistent
	private String petitionSigned;

	@Persistent
	private String petitionCountry;


	public Petition(String petitionTitle, String petitionSummary,
			String petitionWeb, String petitionCountry, String petitionSigned) {

		this.petitionTitle = petitionTitle;
		this.petitionSummary = petitionSummary;
		this.petitionWeb = petitionWeb;
		this.petitionCountry = petitionCountry;
		this.petitionSigned = petitionSigned;

	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getPetitionTitle() {
		return petitionTitle;
	}

	public void setPetitionSummary(String petitionSummary) {
		this.petitionSummary = petitionSummary;
	}

	public String getPetitionSummary() {
		return petitionSummary;
	}

	public void setPetitionWeb(String petitionWeb) {
		this.petitionWeb = petitionWeb;
	}

	public String getPetitionWeb() {
		return petitionWeb;
	}

	public void setPetitionSigned(String petitionSigned) {
		this.petitionSigned = petitionSigned;
	}

	public String getPetitionSigned() {
		return petitionSigned;
	}

	public void setPetitionCountry(String petitionCountry) {
		this.petitionCountry = petitionCountry;
	}

	public String getPetitionCountry() {
		return petitionCountry;
	}

	/*public void setSigneeList(List<Signee> list) {
		this.signeeList.clear();
		this.signeeList.addAll(list);

	}

	public java.util.ArrayList<Signee> getSignees() {
		ArrayList<Signee> temp = new ArrayList<Signee>();
		for (Signee signee : signeeList) {
			temp.add(signee);
		}
		return temp;

	}*/

}
