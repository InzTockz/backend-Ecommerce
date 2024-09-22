package com.proyecto.ecommerce.backend.infrastructure.adapter;

import com.proyecto.ecommerce.backend.domain.model.Product;
import com.proyecto.ecommerce.backend.domain.port.IProductRepository;
import com.proyecto.ecommerce.backend.infrastructure.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductCrudRepositoryImpl implements IProductRepository {

    private final IProductCrudRepository productCrudRepository;
    private final ProductMapper productMapper;

    public ProductCrudRepositoryImpl(IProductCrudRepository productCrudRepository, ProductMapper productMapper) {
        this.productCrudRepository = productCrudRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product save(Product product) {
        return this.productMapper.toProduct(this.productCrudRepository.save(this.productMapper.toProductEntity(product)));
    }

    @Override
    public Iterable<Product> findAll() {
        return this.productMapper.listProducts(this.productCrudRepository.findAll());
    }

    @Override
    public Product findById(Integer id) {
        return this.productMapper.toProduct(this.productCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("El producto con: " + id + " no existe.")
        ));
    }

    @Override
    public void deleteById(Integer id) {
        this.productCrudRepository.deleteById(id);
    }
}
