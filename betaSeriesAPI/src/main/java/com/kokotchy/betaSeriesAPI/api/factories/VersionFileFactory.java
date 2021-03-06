package com.kokotchy.betaSeriesAPI.api.factories;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.Constants;
import com.kokotchy.betaSeriesAPI.model.VersionFile;

/**
 * VersionFile factory
 * 
 * @author kokotchy
 */
public class VersionFileFactory {
	/**
	 * Create a new VersionFile from the json object
	 * 
	 * @param fileObject
	 *            json object
	 * @return VersionFile
	 */
	public static VersionFile createVersionFile(JSONObject fileObject) {
		VersionFile versionFile = new VersionFile();
		versionFile.setLastChange(UtilsJson.getIntValue(fileObject,
				Constants.LAST_CHANGE));
		versionFile.setName(UtilsJson
				.getStringValue(fileObject, Constants.NAME));
		return versionFile;
	}

	/**
	 * Create a version file from node
	 * 
	 * @param node
	 *            Node
	 * @return Version file
	 */
	public static VersionFile createVersionFile(Node node) {
		VersionFile versionFile = new VersionFile();
		versionFile
				.setLastChange(UtilsXml.getInt(node, Constants.LAST_CHANGE));
		versionFile.setName(UtilsXml.getString(node, Constants.NAME));
		return versionFile;
	}
}
