package com.ra.contronller;

import com.ra.entity.Category;
import com.ra.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeContronller {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("home")
    public String home(Model m) {
        List<Category> list = categoryService.findAll();
        m.addAttribute("list", list);
        return "home";
    }

    @RequestMapping("add-category")
    public String addCategory(Model m) {
        Category category = new Category();
        m.addAttribute("category", category);
        return "add-category";
    }

    @PostMapping("/create-category")
    public String createCategory(@ModelAttribute("category") Category category) {
        if (categoryService.saveOrUpdate(category) != null) {
            return "redirect:/home";
        }
        return "add-category";
    }

    @RequestMapping("edit-category/{id}")
    public String addCategory(@PathVariable("id") Integer idCategory, Model m) {
        Category category = categoryService.findById(idCategory);
        m.addAttribute("category", category);
        return "edit-category";
    }

    @RequestMapping("delete-category/{id}")
    public String addCategory(@PathVariable("id") Integer idCategory, RedirectAttributes redirectAttributes) {
        if (!categoryService.exitstedProduct(idCategory)) {
            categoryService.delete(idCategory);
        } else {
            redirectAttributes.addFlashAttribute("message", "không thể xóa vì trong danh mục này vẫn còn sàn phẩm");
        }
        return "redirect:/home";
    }

}
