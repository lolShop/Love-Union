package edu.nf.lol.repository;


import edu.nf.lol.product.entity.ProductDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


public interface ProductRepository extends ElasticsearchRepository<ProductDto,Integer> {

}
