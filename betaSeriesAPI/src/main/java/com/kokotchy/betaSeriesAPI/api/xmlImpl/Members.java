package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;
import com.kokotchy.betaSeriesAPI.api.IMembers;
import com.kokotchy.betaSeriesAPI.model.Episode;
import com.kokotchy.betaSeriesAPI.model.Member;
import com.kokotchy.betaSeriesAPI.model.Notification;
import com.kokotchy.betaSeriesAPI.model.SubtitleLanguage;

/**
 * Members API
 * 
 * @author kokotchy
 * 
 */
public class Members implements IMembers {

	/**
	 * API Key
	 */
	private String apiKey;

	/**
	 * Token of logged user
	 */
	private String token;

	/**
	 * Create new members api with the given key
	 * 
	 * @param apiKey
	 *            API Key
	 */
	public Members(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public boolean auth(String login, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("login", login);
		params.put("password", Utils.getMD5(password));
		Document document = Utils.executeQuery("members/auth.xml", apiKey,
				params);
		if (document.selectSingleNode("/root/code").getText().equals("1")) {
			Node tokenNode = document.selectSingleNode("/root/member/token");
			token = tokenNode.getText();
			return true;
		}
		return false;
	}

	@Override
	public void destroy() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		Utils.executeQuery("members/destroy.xml", apiKey, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Episode> getEpisodes(String token,
			SubtitleLanguage subtitleLanguage) {
		String lang = null;
		switch (subtitleLanguage) {
		case VF:
			lang = "vf";
			break;
		case VOVF:
			lang = "vovf";
			break;
		case ALL:
			lang = "all";
			break;
		}
		List<Episode> result = new LinkedList<Episode>();
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		Document document = Utils.executeQuery("members/episodes/" + lang
				+ ".xml", apiKey, params);
		List<Node> nodes = document.selectNodes("/root/episodes/episode");
		for (Node node : nodes) {
			result.add(Episode.createEpisode(node));
		}
		return result;
	}

	/**
	 * Return the information about the user. If it is the identified user,
	 * identifiedUser has to be true and user has to be the token. If
	 * identifiedUser is false, then the user is the login of the user to
	 * retrieve.
	 * 
	 * @param user
	 *            User or token to retrieve
	 * @param identifiedUser
	 *            If user if the user or the token
	 * @return Member informations
	 */
	private Member getInfosForUser(String user, boolean identifiedUser) {
		Document document;
		if (!identifiedUser) {
			document = Utils.executeQuery("members/infos/" + user + ".xml",
					apiKey);
		} else {
			Map<String, String> params = new HashMap<String, String>();
			params.put("token", user);
			document = Utils.executeQuery("members/infos.xml", apiKey, params);
		}
		return Member.createMember(document.selectSingleNode("/root/member"));
	}

	@Override
	public List<Notification> getNotifications(boolean seen) {
		return getNotificationsWithParameters(seen, -1, -1);
	}

	@Override
	public List<Notification> getNotifications(boolean seen, int nb) {
		return getNotificationsWithParameters(seen, nb, -1);
	}

	@Override
	public List<Notification> getNotifications(boolean seen, int nb, int lastId) {
		return getNotificationsWithParameters(seen, nb, lastId);
	}

	@Override
	public List<Notification> getNotifications(int nb) {
		return getNotificationsWithParameters(null, nb, -1);
	}

	/**
	 * Return the notifications with the given parameter.
	 * 
	 * Conditions for the parameters to be used:
	 * <ul>
	 * <li>seen has not to be null</li>
	 * <li>nb greater than 0</li>
	 * <li>lastId greater than 0</li>
	 * </ul>
	 * 
	 * @param seen
	 *            If the notification has to be already seen or not
	 * @param nb
	 *            Number of notification
	 * @param lastId
	 *            Start of notification
	 * @return List of notification
	 */
	@SuppressWarnings("unchecked")
	private List<Notification> getNotificationsWithParameters(Boolean seen,
			int nb, int lastId) {
		Map<String, String> params = new HashMap<String, String>();
		if (seen != null) {
			params.put("seen", seen ? "yes" : "no");
		}
		if (nb > 0) {
			params.put("number", "" + nb);
		}
		if (lastId > 0) {
			params.put("last_id", "" + lastId);
		}
		params.put("token", token);
		Document document = Utils.executeQuery("members/notifications.xml",
				apiKey, params);
		List<Node> nodes = document
				.selectNodes("/root/notifications/notification");
		List<Notification> notifications = new LinkedList<Notification>();
		for (Node node : nodes) {
			notifications.add(Notification.createNotification(node));
		}
		return notifications;
	}

	/**
	 * Return the token of the user
	 * 
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	@Override
	public Member infos(String token) {
		return getInfosForUser(token, true);
	}

	@Override
	public Member infosOfUser(String user) {
		return getInfosForUser(user, false);
	}

	@Override
	public void resetViewedShow(String token, String url) {
		setWatched(token, url, 0, 0);
	}

	@Override
	public void setWatched(String token, String url, int season, int episode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("season", "" + season);
		params.put("episode", "" + episode);
		params.put("token", token);
		Utils.executeQuery("members/watched/" + url + ".xml", apiKey, params);
	}
}
