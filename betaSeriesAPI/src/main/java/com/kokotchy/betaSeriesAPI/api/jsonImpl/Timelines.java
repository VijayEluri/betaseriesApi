package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.List;

import com.kokotchy.betaSeriesAPI.api.ITimelines;
import com.kokotchy.betaSeriesAPI.model.Event;

/**
 * Timeline API
 * 
 * @author kokotchy
 * 
 */
public class Timelines implements ITimelines {

	/**
	 * API Key
	 */
	private String apiKey;

	/**
	 * Timelines
	 * 
	 * @param apiKey
	 *            API key
	 */
	public Timelines(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public List<Event> getFriendsTimeline(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> getFriendsTimeline(String token, int nb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> getHomeTimeline() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> getHomeTimeline(int nb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> getTimelineOfUser(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> getTimelineOfUser(String user, int nb) {
		// TODO Auto-generated method stub
		return null;
	}

}
