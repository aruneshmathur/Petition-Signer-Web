package com.server.petition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/*
 * A handler for the requests that come in.
 * Create a new Petition when a new petition request comes in, requestCreatePetition() will handle it, it takes a HashMap<String,String> along with it.
 * When a petition sync's with the server, syncPetition() is called, which takes an ArrayList<HashMaps<String,String>>
 */

public class RequestsHandler {

	/*
	 * Called when a user requests to create a petition
	 * 
	 * @params: HashMap containing details regarding the petition
	 * 
	 * @return: Returns the petition-id of the petition that was requested.
	 */
	public Boolean requestCreatePetition(HashMap<String, String> petitionInfo) {

		/*
		 * TO-DO: Check so that these values are NOT NULL. This maybe done of
		 * the client.
		 */

		PersistenceManager mPm = PMF.get().getPersistenceManager();

		Petition petition = new Petition(
				petitionInfo.get(Constants.KEY_PETITION_TITLE),
				petitionInfo.get(Constants.KEY_PETITION_SUMMARY),
				petitionInfo.get(Constants.KEY_PETITION_WEB),
				petitionInfo.get(Constants.KEY_PETITION_COUNTRY),
				petitionInfo.get(Constants.KEY_PETITION_SIGNED));

		try {
			Key key = KeyFactory.createKey(Petition.class.getSimpleName(),
					petitionInfo.get(Constants.KEY_PETITION_ID));
			petition.setKey(key);
			mPm.makePersistent(petition);
		} catch (Exception e) {
			return new Boolean(false);
		} finally {
			mPm.close();
		}

		return new Boolean(true);
	}

	public HashMap<String, Boolean> syncPetition(
			ArrayList<HashMap<String, Object>> list) {
		Signee mSignee = null;
		String mPid = null;
		Petition mPetition = null;
		HashMap<String, Boolean> result_list = new HashMap<String, Boolean>();

		for (HashMap<String, Object> map : list) {
			PersistenceManager mPm = PMF.get().getPersistenceManager();

			try {
				mPid = (String) map.get(Constants.KEY_PETITION_ID);

				mSignee = new Signee(
						(String) map.get(Constants.KEY_PETITION_SIGNEE_NAME),
						(String) map
								.get(Constants.KEY_PETITION_SIGNEE_IMPORTANCE),
						(String) map.get(Constants.KEY_PETITION_SIGNEE_EMAIL),
						(String) map.get(Constants.KEY_PETITION_SIGNEE_CONTACT),
						new ByteArrayWrapperSerializable(
								((ArrayList<byte[]>) map
										.get(Constants.KEY_PETITION_SIGNEE_SIGNATURE))
										.get(0)));

				Key k1 = KeyFactory.createKey(
						Signee.class.getSimpleName(),
						mPid
								+ "-"
								+ (String) map
										.get(Constants.KEY_PETITION_SIGNEE_ID));

				mSignee.setKey(k1);

				Key k2 = KeyFactory.createKey(Petition.class.getSimpleName(),
						mPid);

				mPetition = mPm.getObjectById(Petition.class, k2);
				mPetition.setPetitionSigned((Integer.parseInt(mPetition
						.getPetitionSigned()) + 1) + "");

				mPm.makePersistent(mSignee);

			} finally {
				mPm.close();
			}
			result_list.put((String) map.get(Constants.KEY_PETITION_SIGNEE_ID),
					true);
		}
		return result_list;
	}
}
