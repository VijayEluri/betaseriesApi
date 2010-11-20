package com.kokotchy.betaSeriesAPI.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;
import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;

/**
 * Model of a Show
 * 
 * @author kokotchy
 */
public class Show {

	/**
	 * Create a show from json object
	 * 
	 * @param jsonObject
	 *            jsonObject
	 * @return Show
	 */
	public static Show createShow(JSONObject jsonObject) {
		Show show = new Show();
		show.setTitle(UtilsJson.getStringValue(jsonObject, "title"));
		show.setUrl(UtilsJson.getStringValue(jsonObject, "url"));
		show
				.setDescription(UtilsJson.getStringValue(jsonObject,
						"description"));
		show.setStatus(UtilsJson.getStringValue(jsonObject, "status"));
		show.setBanner(UtilsJson.getStringValue(jsonObject, "banner"));
		show.setIdTvdb(UtilsJson.getIntValue(jsonObject, "id_thetvdb"));
		JSONObject genres = UtilsJson.getJSONObject(jsonObject, "genres");
		if (genres != null) {
			String[] names = JSONObject.getNames(genres);
			try {
				for (String name : names) {
					show.addGenre(genres.getString(name));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		show.setArchived(UtilsJson.getBooleanValue(jsonObject, "archive"));
		return show;
	}

	/**
	 * Create a show from a node
	 * 
	 * @param node
	 *            Node
	 * @return Show
	 */
	@SuppressWarnings("unchecked")
	public static Show createShow(Node node) {
		Show show = new Show();
		show.setTitle(UtilsXml.readString(node, "title"));
		show.setUrl(UtilsXml.readString(node, "url"));
		show.setDescription(UtilsXml.readString(node, "description"));
		show.setStatus(UtilsXml.readString(node, "status"));
		show.setBanner(UtilsXml.readString(node, "banner"));
		show.setIdTvdb(UtilsXml.readInt(node, "id_thetvdb"));
		List<Node> genres = node.selectNodes("genres/genre");
		for (Node nodeGenre : genres) {
			show.addGenre(nodeGenre.getStringValue());
		}
		show.setArchived(UtilsXml.readBoolean(node, "archive"));
		return show;
	}

	/**
	 * Id of the show on tvdb
	 */
	private int idTvdb;

	/**
	 * Url of the show
	 */
	private String url;

	/**
	 * Title of the show
	 */
	private String title;

	/**
	 * Description of the show
	 */
	private String description;

	/**
	 * Status of the show
	 */
	private ShowStatus status;

	/**
	 * Banner of the show
	 */
	private String banner;

	/**
	 * List of genres of the show
	 */
	private Set<String> genres;

	/**
	 * Archived state of the show
	 */
	private boolean archived;

	/**
	 * Create a new show
	 */
	public Show() {
		this(null, null);
	}

	/**
	 * Create a new show
	 * 
	 * @param url
	 *            Url of the show
	 * @param title
	 *            Title of the show
	 */
	public Show(String url, String title) {
		this.url = url;
		this.title = title;
		genres = new HashSet<String>();
	}

	/**
	 * Add the genre
	 * 
	 * @param genre
	 *            Genre of the show
	 */
	public void addGenre(String genre) {
		genres.add(genre);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Show)) {
			return false;
		}
		return hashCode() == obj.hashCode();
	}

	/**
	 * Return the banner of the show
	 * 
	 * @return the banner
	 */
	public String getBanner() {
		return banner;
	}

	/**
	 * Return the description
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Return the genres of the show
	 * 
	 * @return the genres
	 */
	public Set<String> getGenres() {
		return genres;
	}

	/**
	 * Return the id of the show on tv db
	 * 
	 * @return the idTvdb
	 */
	public int getIdTvdb() {
		return idTvdb;
	}

	/**
	 * Return the status of the show
	 * 
	 * @return the status
	 */
	public ShowStatus getStatus() {
		return status;
	}

	/**
	 * Set the title of the show
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Return the url of the show
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, idTvdb);
		result = HashCodeUtil.hash(result, url);
		result = HashCodeUtil.hash(result, title);
		result = HashCodeUtil.hash(result, description);
		result = HashCodeUtil.hash(result, status);
		result = HashCodeUtil.hash(result, banner);
		result = HashCodeUtil.hash(result, genres);
		result = HashCodeUtil.hash(result, archived);
		return result;
	}

	/**
	 * If the show is archived
	 * 
	 * @return archived state
	 */
	public boolean isArchived() {
		return archived;
	}

	/**
	 * Set archived state
	 * 
	 * @param archived
	 *            the archived to set
	 */
	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	/**
	 * Set the banner of the show
	 * 
	 * @param banner
	 *            the banner to set
	 */
	public void setBanner(String banner) {
		this.banner = banner;
	}

	/**
	 * Set the description of the show
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Set the id of the show on tv db
	 * 
	 * @param idTvdb
	 *            the idTvdb to set
	 */
	public void setIdTvdb(int idTvdb) {
		this.idTvdb = idTvdb;
	}

	/**
	 * Set the status of the show
	 * 
	 * @param status
	 *            the status to set
	 */
	public void setStatus(ShowStatus status) {
		this.status = status;
	}

	/**
	 * Set the status of the show
	 * 
	 * @param status
	 *            Name of the status
	 */
	public void setStatus(String status) {
		if (status != null) {
			if (status.equals("Continuing")) {
				setStatus(ShowStatus.CONTINUING);
			} else if (status.equals("Ended")) {
				setStatus(ShowStatus.ENDED);
			} else if (status.equals("On Hiatus")) {
				setStatus(ShowStatus.ON_HIATUS);
			} else if (status.equals("Other")) {
				setStatus(ShowStatus.OTHER);
			}
		}
	}

	/**
	 * Set the title of the show
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Set the url of the show
	 * 
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		String format = "[%s] %s";
		return String.format(format, url, title);
	}

}
