package com.kokotchy.betaSeriesAPI.api;

import java.util.List;

import com.kokotchy.betaSeriesAPI.model.Episode;
import com.kokotchy.betaSeriesAPI.model.Member;
import com.kokotchy.betaSeriesAPI.model.Notification;
import com.kokotchy.betaSeriesAPI.model.SubtitleLanguage;

/**
 * Interface for members to authenticate, retrieve informations, ...
 * 
 * @author kokotchy
 */
public interface IMembers {
	/**
	 * Auth the user with his login and password
	 * 
	 * @param login
	 *            Login of the user
	 * @param password
	 *            Password of the user
	 * @return Token of the user. If null => not logged
	 */
	public String auth(String login, String password);

	/**
	 * Destroy the token of the user
	 * 
	 * @param token
	 *            Toekn
	 */
	public void destroy(String token);

	/**
	 * TODO Fill it
	 * 
	 * @param token
	 * @param identifieduser
	 * @return
	 */
	public int getDateCache(String token, boolean identifieduser);

	/**
	 * Return remaining episodes to see according to the subtitle option
	 * 
	 * @param token
	 *            Token of the user
	 * @param subtitleLanguage
	 *            Subtitle language configuration
	 * @param List
	 *            of episode to watch
	 * @return List of remaining episodes to watch
	 */
	public List<Episode> getEpisodes(String token,
			SubtitleLanguage subtitleLanguage);

	/**
	 * Return the notification received by the member.
	 * 
	 * @param token
	 *            Token of the logged user
	 * @param seen
	 *            Flag to return already seen notification or not
	 * @return List of notifications
	 */
	public List<Notification> getNotifications(String token, boolean seen);

	/**
	 * Return the notification received by the member.
	 * 
	 * @param token
	 *            Token of the logged user
	 * @param seen
	 *            Flag to return already seen notification or not
	 * @param nb
	 *            Number of notification
	 * @return List of notifications
	 */
	public List<Notification> getNotifications(String token, boolean seen, int nb);

	/**
	 * Return the notification received by the member.
	 * 
	 * @param token
	 *            Token of the logged user
	 * @param seen
	 *            Flag to return already seen notification or not
	 * @param nb
	 *            Number of notification
	 * @param lastId
	 *            Start of the last notification
	 * @return List of notifications
	 */
	public List<Notification> getNotifications(String token, boolean seen, int nb, int lastId);

	/**
	 * Return the notification received by the member.
	 * 
	 * @param token
	 *            Token of the logged user
	 * @param nb
	 *            Number of notification
	 * @return List of notifications
	 */
	public List<Notification> getNotifications(String token, int nb);

	/**
	 * Return information about the logged user
	 * 
	 * @param token
	 *            Token
	 * @return Informations about the logged user
	 */
	public Member infos(String token);

	/**
	 * Return information about the logged user
	 * 
	 * @param token
	 *            Token
	 * @param lastCache
	 * @return Informations about the logged user
	 */
	public Member infos(String token, int lastCache);

	/**
	 * Return information about the given user
	 * 
	 * @param user
	 *            User to retrieve information
	 * @return Informations about the user
	 */
	public Member infosOfUser(String user);

	/**
	 * Return information about the given user
	 * 
	 * @param user
	 *            User to retrieve information
	 * @param lastCache
	 * @return Informations about the user
	 */
	public Member infosOfUser(String user, int lastCache);

	/**
	 * Return true if the user is still active, false otherwise
	 * 
	 * @param token
	 *            Token of the user
	 * @return True if active, false otherwise
	 */
	public boolean isActive(String token);

	/**
	 * Reset the serie
	 * 
	 * @param token
	 *            Token
	 * @param url
	 *            Url of the show
	 */
	public void resetViewedShow(String token, String url);

	/**
	 * Set the episode for the given season and url as watch
	 * If the watched episode didn't follow the last seen episode, all episodes
	 * between them are marked as seen.
	 * 
	 * @param token
	 *            Token of the user
	 * @param url
	 *            Url of the show
	 * @param season
	 *            Season
	 * @param episode
	 *            Episode
	 */
	public void setWatched(String token, String url, int season, int episode);
}
