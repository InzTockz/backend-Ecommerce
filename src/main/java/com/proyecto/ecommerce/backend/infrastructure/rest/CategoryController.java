package com.proyecto.ecommerce.backend.infrastructure.rest;

import com.proyecto.ecommerce.backend.application.CategoryService;
import com.proyecto.ecommerce.backend.domain.model.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/categories")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Iterable<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("{id}")
    public Category findById(@PathVariable Integer id){
        return categoryService.findById(id);
    }

    @PostMapping
    public Category save(@RequestBody Category category){
        return categoryService.save(category);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        categoryService.deleteById(id);
    }
}
