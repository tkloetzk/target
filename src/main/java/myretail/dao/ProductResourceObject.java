package myretail.dao;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="Product")
public class ProductResourceObject {
	
	@Id
	private String id;
	
	@Indexed
	private int pid;
	
	private String title;

	@Field("price")
	private BigDecimal currentPrice;
	
	@Field("currencyCode")
	private String currency_code;

	public ProductResourceObject(int pid) {
		super();
		this.pid = pid;
	}
	
	public int getProductId() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
}
