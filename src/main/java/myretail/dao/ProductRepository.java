package myretail.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("ProductRepository")
public interface ProductRepository extends MongoRepository<ProductResourceObject, String> {

	@Query("{ 'pid' : ?0 }")
	public ProductResourceObject findByProductId(int pid);

	@SuppressWarnings("unchecked")
	public ProductResourceObject save(ProductResourceObject productDao);

}