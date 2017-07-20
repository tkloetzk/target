package myretail.service;

import myretail.dao.ProductDao;
import myretail.dao.Product;

public interface ProductService {

	
	Product getProductById(int id) throws Exception ;

	Product updateProductPrice(ProductDao product) ;


}
