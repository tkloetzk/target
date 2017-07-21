package myretail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import myretail.dao.Product;
import myretail.dao.ProductResourceObject;
import myretail.service.ProductDAO;
import myretail.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductDAO productDAO;

	//Return JSON representation of Product
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProductDetailsById(@PathVariable int id) throws Exception{					
		Product product = productService.getProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	//Update Product's Price 
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateProductPriceById(@RequestBody Product productRequest,                                        
			@PathVariable int id) throws Exception {
		ProductResourceObject product = productDAO.resourceObject(productRequest);
		productService.updateProductPrice(product);	
	}

}
