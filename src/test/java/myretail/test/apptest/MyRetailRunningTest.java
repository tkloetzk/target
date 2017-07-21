package myretail.test.apptest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import myretail.dao.PriceData;
import myretail.dao.Product;

@RunWith(SpringRunner.class)
public class MyRetailRunningTest {

	private int port = 8080;

	private TestRestTemplate template = new TestRestTemplate();

	@Test
	public void testGetProductById() {
		ResponseEntity<Product> productEntity = this.template.getForEntity("http://localhost:"
				+ port +"/products/{productId}", Product.class, 13860428);
		assertEquals("The Big Lebowski (Blu-ray)", productEntity.getBody().getTitle());
		assertEquals(HttpStatus.OK, productEntity.getStatusCode());
	}

	@Test
	public void testGetProductByIdNotFound() {
		ResponseEntity<Product> productEntity = this.template.getForEntity("http://localhost:"
				+ port +"/products/{productId}", Product.class, 831608124);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, productEntity.getStatusCode()); //Should be 404
	}

	@Test
	public void testUpdateProductPriceById_ProductNotFound() {
		Product product = new Product();
		PriceData priceData = new PriceData();
		priceData.setPrice(new BigDecimal(99.99));
		product.setPriceData(priceData);
		product.setId(9876);

		HttpEntity<Product> requestEntity = new HttpEntity<Product>(product);
		ResponseEntity<String> responseEntity = 
				this.template.exchange("http://localhost:"
						+ port +"/products/{productId}", HttpMethod.PUT, requestEntity, String.class, 9876);
		assertTrue(responseEntity.getBody().contains("404") && responseEntity.getStatusCodeValue() == 500); //Should be 404
	}

	@Test
	public void testUpdateProductPrice() {
		Product product = new Product();
		PriceData priceData = new PriceData();
		priceData.setPrice(new BigDecimal(14.95));
		product.setId(13860428);
		product.setPriceData(priceData);

		HttpEntity<Product> requestEntity = new HttpEntity<Product>(product);
		ResponseEntity<String> responseEntity = 
				this.template.exchange("http://localhost:"
						+ port +"/products/{productId}", HttpMethod.PUT, requestEntity, String.class, 13860428);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

}
