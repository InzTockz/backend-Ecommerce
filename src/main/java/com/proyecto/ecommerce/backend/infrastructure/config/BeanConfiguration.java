package com.proyecto.ecommerce.backend.infrastructure.config;

import com.proyecto.ecommerce.backend.application.*;
import com.proyecto.ecommerce.backend.domain.port.ICategoryRepository;
import com.proyecto.ecommerce.backend.domain.port.IOrderRepository;
import com.proyecto.ecommerce.backend.domain.port.IProductRepository;
import com.proyecto.ecommerce.backend.domain.port.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public UserService userService(IUserRepository iUserRepository){
        return new UserService(iUserRepository);
    }

    @Bean
    public CategoryService categoryService(ICategoryRepository iCategoryRepository){
        return new CategoryService(iCategoryRepository);
    }

    @Bean
    public ProductService productService(IProductRepository iProductRepository, UploadFile uploadFile){
        return new ProductService(iProductRepository, uploadFile);
    }

    @Bean
    public OrderService orderService(IOrderRepository iOrderRepository){
        return new OrderService(iOrderRepository);
    }

    @Bean
    public UploadFile uploadFile(){
        return new UploadFile();
    }
}
