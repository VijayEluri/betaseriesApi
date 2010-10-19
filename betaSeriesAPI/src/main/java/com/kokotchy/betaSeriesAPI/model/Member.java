package com.kokotchy.betaSeriesAPI.model;

import java.util.LinkedList;
import java.util.List;

import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;

/**
 * Model of a member
 * 
 * @author kokotchy
 */
public class Member {

	/**
	 * Create the member from the node
	 * 
	 * @param node
	 *            Node
	 * @return Member
	 */
	@SuppressWarnings("unchecked")
	public static Member createMember(Node node) {
		Member member = new Member();
		member.setLogin(Utils.readString(node, "login"));
		member.setAvatar(Utils.readString(node, "avatar"));
		member.setStats(Stats.createStats(node.selectSingleNode("stats")));
		List<Node> shows = node.selectNodes("shows/show");
		for (Node show : shows) {
			member.addShow(Show.createShow(show));
		}
		return member;
	}

	/**
	 * Login of the member
	 */
	private String login;

	/**
	 * Avatar of the user
	 */
	private String avatar;

	/**
	 * Statistics about the user
	 * 
	 * TODO Remove stats object an include directly in Member model
	 */
	private Stats stats;

	/**
	 * List of shows followed by the member
	 */
	private List<Show> shows = new LinkedList<Show>();

	/**
	 * Add the given show
	 * 
	 * @param show
	 *            Show
	 */
	public void addShow(Show show) {
		shows.add(show);
	}

	/**
	 * Return the avatar
	 * 
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * Return the login
	 * 
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Return the shows
	 * 
	 * @return the shows
	 */
	public List<Show> getShows() {
		return shows;
	}

	/**
	 * Return statistics
	 * 
	 * @return the stats
	 */
	public Stats getStats() {
		return stats;
	}

	/**
	 * Return avatar
	 * 
	 * @param avatar
	 *            the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * Set login
	 * 
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Set stats
	 * 
	 * @param stats
	 *            the stats to set
	 */
	public void setStats(Stats stats) {
		this.stats = stats;
	}

	@Override
	public String toString() {
		return login + " with " + shows.size() + " shows";
	}
}
