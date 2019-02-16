package com.jt.search.pojo;

import org.apache.solr.client.solrj.beans.Field;

import com.jt.common.po.BasePojo;

public class Item extends BasePojo {
	@Field("id")
	private long id;

	@Field("title")
	private String title;

	@Field("sellPoint")
	private String sellPoint;

	@Field("price")
	private long price;
	// jsp foreach item item.image/item.images
	// solr中filed的name是image
	@Field("image")
	private String images;

	// jsp item.images
	public String[] getImages() {
		// 1.png,2.png img src=1.png,2.png
		return images.split(",");
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
}
