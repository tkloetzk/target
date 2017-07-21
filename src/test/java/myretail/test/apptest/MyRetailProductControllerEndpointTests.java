package myretail.test.apptest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import myretail.controller.ProductController;
import myretail.dao.Product;
import myretail.dao.ProductResourceObject;
import myretail.service.ProductDAO;
import myretail.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class MyRetailProductControllerEndpointTests {

	@InjectMocks
	private ProductController productController = new ProductController();

	@Mock
	private ProductService mockProductService;

	@Mock
	private ProductDAO mockProductDAO; 

	@Test
	public void getProduct_Success() throws Exception {		
		when(mockProductService.getProductById(anyInt())).thenReturn(any(Product.class));
		ResponseEntity<Product> productEntity = productController.getProductDetailsById(123);
		assertEquals(HttpStatus.OK, productEntity.getStatusCode());
	}

	@Test
	public void testUpdateProductPrice() throws Exception  {	
		when(mockProductService.updateProductPrice(any(ProductResourceObject.class))).thenReturn(any(Product.class));
		productController.updateProductPriceById(new Product(), 15117729);
		verify(mockProductDAO).resourceObject(any(Product.class));
	}

}