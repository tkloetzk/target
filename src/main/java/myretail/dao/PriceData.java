
package myretail.dao;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceData {
	
	@JsonProperty("value")
	protected BigDecimal price;
	
	@JsonProperty("currency_code") 
	protected String currencyCode;
	
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


}
