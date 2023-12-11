package com.ra.contronller;

import com.ra.entity.Category;
import com.ra.entity.Product;
import com.ra.service.CategoryService;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductContronller {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @GetMapping("product")
    private String allProduct(Model model){
        List<Product> list = productService.findAll();
        model.addAttribute("list", list);
        return "product";
    }
    @GetMapping("add-product")
    private String addProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        List<Category> listCategory = categoryService.findAll();
        model.addAttribute("listCategory",listCategory);
        return "add-product";
    }
    @PostMapping("create-product")
    public  String createProduct (@ModelAttribute("product") Product product){
        productService.saveOrUpdate(product);
        return "redirect:/product";
    }
    @GetMapping("edit-product/{id}")
    private String editProduct(@PathVariable("id")Integer id,Model model){
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        List<Category> listCategory = categoryService.findAll();
        model.addAttribute("listCategory",listCategory);
        return "edit-product";
    }
    @GetMapping("delete-product/{id}")
    private String deleteProduct (@PathVariable("id") Integer id ){
        productService.delete(id);
        return "redirect:/product";
    }
}
