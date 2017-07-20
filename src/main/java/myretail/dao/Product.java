/**
 * 
 */
package myretail.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sdevana
 *
 */
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

	@Override
	public String toString() {
		return "ProductData [id=" + id + ", title=" + title + ", priceData=" + priceData + ", getPid()=" + getId()
				+ ", getTitle()=" + getTitle() + ", getPriceData()=" + getPriceData() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((priceData == null) ? 0 : priceData.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		if (priceData == null) {
			if (other.priceData != null)
				return false;
		} else if (!priceData.equals(other.priceData))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
	
	
	

}
