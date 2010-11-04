package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.IPlanning;
import com.kokotchy.betaSeriesAPI.model.Episode;

/**
 * Planning API
 * 
 * @author kokotchy
 */
public class Planning implements IPlanning {

	/**
	 * Api key
	 */
	private String apiKey;

	/**
	 * Create a new planning with the key
	 * 
	 * @param apiKey
	 *            Api key
	 */
	public Planning(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public List<Episode> getGeneralPlanning() {
		return getPlanning(null, null, null);
	}

	@Override
	public List<Episode> getMemberPlanning(boolean unseen, String token) {
		return getPlanning(unseen, token, true);
	}

	@Override
	public List<Episode> getMemberPlanning(String login, boolean unseen) {
		return getPlanning(unseen, login, false);
	}

	/**
	 * Return the planning. If all parameters are null, return the general planning.
	 * Unseen parameter used to select only unseen episodes.
	 * Token is used for the identified user if identifiedUser, or the login of the user if not identifiedUser
	 * 
	 * @param unseen
	 *            Only uneseen erpisode
	 * @param token
	 *            If identifiedUser => identified user toke, otherwise login of the member
	 * @param identifiedUser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Episode> getPlanning(Boolean unseen, String token,
			Boolean identifiedUser) {
		Document document = null;
		if (unseen == null && token == null && identifiedUser == null) {
			document = UtilsXml.executeQuery("planning/general.xml", apiKey);
		} else {
			Map<String, String> params = new HashMap<String, String>();
			String action;
			if (unseen != null && unseen) {
				params.put("view", "unseen");
			}
			if (identifiedUser) {
				params.put("token", token);
				action = "planning/member.xml";
			} else {
				action = "planning/member/" + token + ".xml";
			}
			document = UtilsXml.executeQuery(action, apiKey, params);
		}

		List<Node> nodes = document.selectNodes("/root/planning/episode");
		List<Episode> episodes = new LinkedList<Episode>();
		for (Node node : nodes) {
			episodes.add(Episode.createEpisode(node));
		}
		return episodes;
	}
}
