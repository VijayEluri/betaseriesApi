package com.kokotchy.betaSeriesAPI.model;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;

/**
 * Model of a comment
 * 
 * TODO Fill it
 * 
 * @author kokotchy
 */
public class Comment {

	/**
	 * TODO Fill it
	 */
	private String login;

	/**
	 * TODO Fill it
	 */
	private int date;

	/**
	 * TODO Fill it
	 */
	private String content;

	/**
	 * TODO Fill it
	 */
	private int innerId;

	/**
	 * TODO Fill it
	 */
	private int replyToId;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Comment)) {
			return false;
		}
		return hashCode() == obj.hashCode();
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the date
	 */
	public int getDate() {
		return date;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the innerId
	 */
	public int getInnerId() {
		return innerId;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the replyToId
	 */
	public int getReplyToId() {
		return replyToId;
	}

	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		return result;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(int date) {
		this.date = date;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param innerId
	 *            the innerId to set
	 */
	public void setInnerId(int innerId) {
		this.innerId = innerId;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param replyToId
	 *            the replyToId to set
	 */
	public void setReplyToId(int replyToId) {
		this.replyToId = replyToId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
