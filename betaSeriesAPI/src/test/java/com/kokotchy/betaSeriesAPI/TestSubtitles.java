package com.kokotchy.betaSeriesAPI;

import java.util.List;

import junit.framework.TestCase;

import com.kokotchy.betaSeriesAPI.model.Subtitle;
import com.kokotchy.betaSeriesAPI.model.SubtitleLanguage;

/**
 * Test subtitles api
 * 
 * @author kokotchy
 * 
 */
public class TestSubtitles extends TestCase {

	/**
	 * Subtitles api for json
	 */
	private com.kokotchy.betaSeriesAPI.api.jsonImpl.Subtitles subtitlesJson;

	/**
	 * Subtitles api for xml
	 */
	private com.kokotchy.betaSeriesAPI.api.xmlImpl.Subtitles subtitlesXml;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		subtitlesJson = com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi
				.getSubtitles();
		subtitlesXml = com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi
				.getSubtitles();
		UtilsJson.setDebug(true);
		UtilsJson.setDebugPath(System.getProperty("user.dir")
				+ "/src/test/resources/betaseriejson/");
		UtilsXml.setDebug(true);
		UtilsXml.setDebugPath(System.getProperty("user.dir")
				+ "/src/test/resources/betaseriexml/");
	}

	/**
	 * Test the last subtitles
	 */
	public void testSubtitlesLast() {
		List<Subtitle> lastSubtitlesJson = subtitlesJson.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		List<Subtitle> lastSubtitlesXml = subtitlesXml.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		assertEquals(lastSubtitlesXml, lastSubtitlesJson);
	}

	/**
	 * Test last subtitles for two json
	 */
	public void testSubtitlesLastJson() {
		List<Subtitle> lastSubtitlesJson = subtitlesJson.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		List<Subtitle> lastSubtitlesJson2 = subtitlesJson.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		assertEquals(lastSubtitlesJson, lastSubtitlesJson2);
	}

	/**
	 * Test last subtitles for two xml
	 */
	public void testSubtitlesLastXml() {
		List<Subtitle> lastSubtitlesXml = subtitlesXml.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		List<Subtitle> lastSubtitlesXml2 = subtitlesXml.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		assertEquals(lastSubtitlesXml, lastSubtitlesXml2);
	}

	/**
	 * TODO Fill it
	 */
	public void testSubtitlesShowDexter() {
		String url = "dexter";
		SubtitleLanguage subtitle = SubtitleLanguage.VF;
		int season = 1;
		int episode = -1;
		List<Subtitle> subtitlesListXml = subtitlesXml.show(url, subtitle,
				season, episode);
		List<Subtitle> subtitlesListJson = subtitlesJson.show(url, subtitle,
				season, episode);
		assertEquals(subtitlesListXml, subtitlesListJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testSubtitlesShowDexterJson() {
		String url = "dexter";
		SubtitleLanguage subtitle = SubtitleLanguage.VF;
		int season = 1;
		int episode = -1;
		List<Subtitle> subtitlesListJson2 = subtitlesJson.show(url, subtitle,
				season, episode);
		List<Subtitle> subtitlesListJson = subtitlesJson.show(url, subtitle,
				season, episode);
		assertEquals(subtitlesListJson, subtitlesListJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testSubtitlesShowDexterXml() {
		String url = "dexter";
		SubtitleLanguage subtitle = SubtitleLanguage.VF;
		int season = 1;
		int episode = -1;
		List<Subtitle> subtitlesListXml = subtitlesXml.show(url, subtitle,
				season, episode);
		List<Subtitle> subtitlesListXml2 = subtitlesXml.show(url, subtitle,
				season, episode);
		assertEquals(subtitlesListXml, subtitlesListXml2);
	}
}