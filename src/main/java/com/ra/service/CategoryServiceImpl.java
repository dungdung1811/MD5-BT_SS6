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
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    ProductReponsetory productReponsetory;
    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }
    @Override
    public Category saveOrUpdate(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    public Category findById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }
    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
    @Override
    public Boolean exitstedProduct(Integer categoryId) {
        for (Product p : productReponsetory.findAll()) {
            if (p.getCategory().getId().equals(categoryId)) {
                return true;
            }
        }
        return false;
    }
}
