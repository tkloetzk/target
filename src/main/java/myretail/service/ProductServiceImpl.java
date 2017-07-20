package myretail.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;
import org.springframework.web.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import myretail.dao.ProductDao;
import myretail.dao.Product;
import myretail.dao.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	public static final String PRODUCT_PATH = "v1/pdp/tcin/";
	public static final String EXCLUDES_PATH = "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

	@Value("${myretail.product.hostname}")
	private String hostName;

	@Autowired
	ProductDAO productDAO;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	RestTemplate restfulTemplate;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getURIPath(int productId) {
		return hostName + PRODUCT_PATH + productId + EXCLUDES_PATH;
	}

	public void setProductMapper(ProductDAO prdDaoMapper) {
		this.productDAO = prdDaoMapper;
	}

	@Override
	public Product getProductById(int id) throws Exception {

		ProductDao product = productRepository.findByProductId(id);

		String productTitle = getProductTitle(id);

		if (product == null) {
			product = new ProductDao(id);
		}

		product.setTitle(productTitle);

		return productDAO.resourceObject(product);
	}

	private String getProductTitle(int productId) throws Exception {
		String responseBody = null;
		ResponseEntity<String> response = null;
		String productTitle = null;

		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(getURIPath(productId)).build();

		try {
			response = restfulTemplate.getForEntity(uriComponents.encode().toUri(), String.class);
			responseBody = response.getBody();

			ObjectMapper mapper = new ObjectMapper();

			JsonNode productRootNode = mapper.readTree(responseBody);

			if (productRootNode != null)
				productTitle = productRootNode.get("product").get("item").get("product_description").get("title")
				.asText();

		} catch (HttpClientErrorException ex) {
			throw new HttpClientErrorException(ex.getStatusCode(), ex.getLocalizedMessage());
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Problem parsing JSON String" + responseBody, e);
		} catch (IOException e) {
			throw new RuntimeException("Unknown exception while trying to fetch from Database for " + productId, e);
		}

		if (!response.getStatusCode().equals(HttpStatus.OK))
			throw new Exception(
					"Error ocurred while retrieving product title, status code: " + response.getStatusCode().value());

		return productTitle;
	}

	@Override
	public Product updateProductPrice(ProductDao product) {

		int productId = product.getProductId();

		// get product from database
		ProductDao existingProduct = productRepository.findByProductId(productId);

		if (existingProduct == null) {
			throw new NullPointerException("Product Not Found In Database");
		}

		existingProduct.setCurrentPrice(product.getCurrentPrice());

		// updating the price in database
		ProductDao updatedProduct = productRepository.save(existingProduct);

		return productDAO.resourceObject(updatedProduct);
	}
}
