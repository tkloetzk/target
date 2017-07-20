/**
 * 
 */
package myretail.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author sdevana
 *
 */
@Repository("ProductRepository")
public interface ProductRepository extends MongoRepository<ProductDao, String> {
	
	@Query("{ 'pid' : ?0 }")
	public ProductDao findByProductId(int pid);
	
	@SuppressWarnings("unchecked")
	public ProductDao save(ProductDao productDao);

}