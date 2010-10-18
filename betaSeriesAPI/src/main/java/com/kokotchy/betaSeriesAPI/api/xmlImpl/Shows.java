package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;
import com.kokotchy.betaSeriesAPI.api.IShows;
import com.kokotchy.betaSeriesAPI.model.Episode;
import com.kokotchy.betaSeriesAPI.model.Season;
import com.kokotchy.betaSeriesAPI.model.Show;

/**
 * Shows API
 * 
 * @author kokotchy
 */
public class Shows implements IShows {

	/**
	 * API Key
	 */
	private String apiKey;

	/**
	 * Create the shows api with the given key
	 * 
	 * @param apiKey
	 *            API Key
	 */
	public Shows(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public boolean add(String url, String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		Document document = Utils.executeQuery("shows/add/" + url + ".xml",
				apiKey, params);
		return !Utils.hasErrors(document);
	}

	@Override
	public Show display(String url) {
		Document document = Utils.executeQuery("shows/display/" + url + ".xml",
				apiKey);
		if (Utils.hasErrors(document)) {
			return Show.createShow(document.selectSingleNode("/root/show"));
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Show> displayAll() {
		Document document = Utils.executeQuery("shows/display/all.xml", apiKey);
		List<Show> result = new LinkedList<Show>();
		List<Node> nodes = document.selectNodes("/root/shows/show");
		for (Node node : nodes) {
			result.add(Show.createShow(node));
		}
		return result;
	}

	@Override
	public List<Season> getEpisodes(String url) {
		return getEpisodesFromSeason(url, -1);
	}

	@Override
	public Season getEpisodes(String url, int seasonNb) {
		return getEpisodesFromSeason(url, seasonNb).get(0);
	}

	/**
	 * Return the episodes from the given season. If seasonNb is < 0, then
	 * retrieve all seasons
	 * 
	 * @param url
	 *            Url of the show
	 * @param seasonNb
	 *            Number of the season
	 * @return List of seasons with the episodes
	 */
	@SuppressWarnings("unchecked")
	private List<Season> getEpisodesFromSeason(String url, int seasonNb) {
		Document document = null;
		if (seasonNb > 0) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("season", "" + seasonNb);
			document = Utils.executeQuery("shows/episodes/" + url + ".xml",
					apiKey, params);
		} else {
			document = Utils.executeQuery("shows/episodes/" + url + ".xml",
					apiKey);
		}

		List<Node> seasons = document.selectNodes("/root/seasons/season");
		List<Season> result = new LinkedList<Season>();
		for (Node node : seasons) {
			Season season = new Season(Integer.parseInt(node.selectSingleNode(
					"number").getText()));
			List<Node> episodes = node.selectNodes("episodes/episode");
			for (Node episodeNode : episodes) {
				Episode episode = Episode.createEpisode(episodeNode);
				season.addEpisode(episode);
			}
			result.add(season);
		}
		return result;
	}

	@Override
	public boolean remove(String url, String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		Document document = Utils.executeQuery("shows/remove/" + url + ".xml",
				apiKey, params);
		return !Utils.hasErrors(document);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Show> search(String title) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("title", title);
		Document document = Utils.executeQuery("shows/search.xml", apiKey,
				params);
		List<Show> shows = new LinkedList<Show>();
		if (document.selectSingleNode("/root/code").getText().equals("1")) {
			List<Node> nodes = document.selectNodes("/root/shows/show");
			for (Node showNode : nodes) {
				Show show = new Show();
				show.setUrl(showNode.selectSingleNode("url").getText());
				show.setTitle(showNode.selectSingleNode("title").getText());
				shows.add(show);
			}
		}
		return shows;
	}
}