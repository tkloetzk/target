package myretail.service;

import org.springframework.stereotype.Component;

import myretail.dao.PriceData;
import myretail.dao.ProductDao;
import myretail.dao.Product;

@Component
public class ProductDAO {
	
	public Product resourceObject(ProductDao product){
		
		Product productsData = null;
		if(product!=null){
			productsData = new Product();
			
			productsData.setId(product.getProductId());
			productsData.setTitle(product.getTitle());
			
			PriceData priceData = new PriceData();
			priceData.setCurrencyCode(product.getCurrency_code());
			priceData.setPrice(product.getCurrentPrice());
			productsData.setPriceData(priceData);
		}
		return productsData;
	}
	
	public ProductDao resourceObject(Product productData){
		
		ProductDao productDao = new ProductDao(productData.getId());
		productDao.setCurrentPrice(productData.getPriceData().getPrice());
		return productDao;
	}

}
