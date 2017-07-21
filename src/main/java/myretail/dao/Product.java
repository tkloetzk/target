package myretail.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
	
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("name")
	private String title;
	
	@JsonProperty("current_price")
	private PriceData priceData;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the pid to set
	 */
	public void setId(int pid) {
		this.id = pid;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public PriceData getPriceData() {
		return priceData;
	}

	public void setPriceData(PriceData priceData) {
		this.priceData = priceData;
	}

}
