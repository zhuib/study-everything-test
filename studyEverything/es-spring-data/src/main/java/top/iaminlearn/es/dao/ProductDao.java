package top.iaminlearn.es.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import top.iaminlearn.es.entity.Product;

/**
 * Date: 2021/4/9 22:03
 */
@Repository
public interface ProductDao extends ElasticsearchRepository<Product,Long> {
}
