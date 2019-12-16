package com.example.ktxexample.model.response.feed_model.RSS;

import com.example.ktxexample.model.response.feed_model.BaseNewsFeed;

import org.simpleframework.xml.Root;

/**
 * @author Anirudh Sharma
 * <p>
 * The 'Article' class represents one single article and only stores the title, link and description of it.
 */
@Root(name = "item", strict = false)
public class Article extends BaseNewsFeed {

   /* @Element(name = "description")
    private String description;

    @Element(name = "source")
    private String source;*/

	/*@Element(name = "title")
	private String title;

	@Element(name = "link")
	private String link;

	@Element(name = "guid")
	private String guid;

	@Element(name = "pubDate")
	private String pubDate;*/


    /**
     * @return the title
     *//*
	public String getTitle() {
		return title;
	}

	*//**
     * @param title the title to set
     *//*
	public void setTitle(String title) {
		this.title = title;
	}

	*//**
     * @return the link
     *//*
	public String getLink() {
		return link;
	}

	*//**
     * @param link the link to set
     *//*
	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}*/
}
