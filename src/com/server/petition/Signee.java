package com.server.petition;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(detachable = "true")
public class Signee {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String signeeName;

	@Persistent
	private String signeeImportance;

	@Persistent
	private String signeemail;

	@Persistent
	private String signeeContact;

    @Persistent(serialized = "true")
	private ByteArrayWrapperSerializable signeeSignature;

	public Signee(String signeeName, String signeeImportance,
			String signeemail, String signeeContact,
			ByteArrayWrapperSerializable signeeSignature) {
		this.signeeName = signeeName;
		this.signeeImportance = signeeImportance;
		this.signeemail = signeemail;
		this.signeeContact = signeeContact;
		this.signeeSignature = signeeSignature;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Key getKey() {
		return key;
	}

	public void setSigneeName(String signeeName) {
		this.signeeName = signeeName;
	}

	public String getSigneeName() {
		return signeeName;
	}

	public void setSigneeImportance(String signeeImportance) {
		this.signeeImportance = signeeImportance;
	}

	public String getSigneeImportance() {
		return signeeImportance;
	}

	public void setSigneemail(String signeemail) {
		this.signeemail = signeemail;
	}

	public String getSigneemail() {
		return signeemail;
	}

	public void setSigneeContact(String signeeContact) {
		this.signeeContact = signeeContact;
	}

	public String getSigneeContact() {
		return signeeContact;
	}

	public ByteArrayWrapperSerializable getSigneeSignature() {
		return signeeSignature;
	}

}
