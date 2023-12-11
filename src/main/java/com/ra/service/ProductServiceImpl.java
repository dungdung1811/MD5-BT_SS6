package com.ra.service;

import com.ra.entity.Category;
import com.ra.entity.Product;
import com.ra.repository.CategoryRepository;
import com.ra.repository.ProductReponsetory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductReponsetory productReponsetory;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Product> findAll() {
        return (List<Product>)productReponsetory.findAll();
    }
    @Override
    public Product saveOrUpdate(Product product) {
      return productReponsetory.save(product);

    }
    @Override
    public Product findById(Integer id) {
        Optional<Product> optionalProduct = productReponsetory.findById(id);
        return  optionalProduct.orElse(null);
    }
    @Override
    public void delete(Integer id) {
            productReponsetory.deleteById(id);
    }
}
