package myretail.service;

import myretail.dao.Product;
import myretail.dao.ProductResourceObject;

public interface ProductService {
	
	Product getProductById(int id) throws Exception;

	Product updateProductPrice(ProductResourceObject product);


}
