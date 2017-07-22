package myretail.service;

import org.springframework.stereotype.Component;

import myretail.dao.PriceData;
import myretail.dao.Product;
import myretail.dao.ProductResourceObject;

@Component
public class ProductDAO {

	public Product resourceObject(ProductResourceObject product) {

		Product productsData = null;
		if (product != null) {
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

	public ProductResourceObject resourceObject(Product productData) {
		ProductResourceObject productDao = new ProductResourceObject(productData.getId());
		productDao.setCurrentPrice(productData.getPriceData().getPrice());
		return productDao;
	}

}
