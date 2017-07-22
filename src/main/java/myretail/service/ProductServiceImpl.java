package myretail.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import myretail.dao.Product;
import myretail.dao.ProductRepository;
import myretail.dao.ProductResourceObject;

@Service
public class ProductServiceImpl implements ProductService {
	private static final String HOST = "http://redsky.target.com/v1/pdp/tcin/";
	private static final String EXCLUDES_PATH = "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

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

	public String getURIPath(int productId) {
		return HOST + productId + EXCLUDES_PATH;
	}

	public void setProductMapper(ProductDAO prdDaoMapper) {
		this.productDAO = prdDaoMapper;
	}

	@Override
	public Product getProductById(int id) throws JsonProcessingException, IOException {

		ProductResourceObject product = productRepository.findByProductId(id);

		String productTitle = getProductTitle(id);

		if (product == null) {
			product = new ProductResourceObject(id);
		}

		product.setTitle(productTitle);

		return productDAO.resourceObject(product);
	}

	private String getProductTitle(int productId) throws JsonProcessingException, IOException {
		String responseBody = null;
		ResponseEntity<String> response = null;
		String productTitle = null;

		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(getURIPath(productId)).build();

		// try {
		response = restfulTemplate.getForEntity(uriComponents.encode().toUri(), String.class);
		responseBody = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		JsonNode productRootNode = mapper.readTree(responseBody);

		if (productRootNode != null)
			productTitle = productRootNode.get("product").get("item").get("product_description").get("title").asText();

		return productTitle;
	}

	@Override
	public Product updateProductPrice(ProductResourceObject product) {

		int productId = product.getProductId();

		// get product from database
		ProductResourceObject existingProduct = productRepository.findByProductId(productId);

		if (existingProduct == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "404");
		}

		existingProduct.setCurrentPrice(product.getCurrentPrice());

		// updating the price in database
		ProductResourceObject updatedProduct = productRepository.save(existingProduct);

		return productDAO.resourceObject(updatedProduct);
	}
}
