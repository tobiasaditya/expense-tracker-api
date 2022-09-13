package com.obider.expensetrackerapi.category;

import com.obider.expensetrackerapi.category.entity.ShowCategory;
import com.obider.expensetrackerapi.category.helper.Formatter;
import com.obider.expensetrackerapi.category.input.InputCategory;
import com.obider.expensetrackerapi.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> getAllCategories(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("userId");
        List<Category> result =  categoryService.getAllCategories(userId);
        List<ShowCategory> format = Formatter.formatCategories(result);

        return ResponseHandler.generateResponse("Success get categories",HttpStatus.OK,format);
    }

    @GetMapping(path = "/get/{categoryId}")
    public ResponseEntity<Object> getCategoryById(HttpServletRequest request, @PathVariable("categoryId") Integer categoryId){
        Integer userId = (Integer) request.getAttribute("userId");
        Category result =  categoryService.getUserCategoryById(userId,categoryId);
        if (result==null){
            return ResponseHandler.generateResponse("Category not found",HttpStatus.NOT_FOUND,result);
        }
        ShowCategory format = Formatter.formatCategory(result);
        return ResponseHandler.generateResponse("Success get category by id",HttpStatus.OK,format);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Object> addCategory(HttpServletRequest request, @RequestBody InputCategory inputCategory){
        Integer userId = (Integer) request.getAttribute("userId");
        categoryService.addCategory(userId,inputCategory);
        return ResponseHandler.generateResponse("Success create",HttpStatus.OK);
    }

    @PutMapping(path = "/update/{categoryId}")
    public ResponseEntity<Object> updateCategory(
            HttpServletRequest request,
            @PathVariable("categoryId") Integer categoryId,
            @RequestBody InputCategory inputCategory){
        Integer userId = (Integer) request.getAttribute("userId");
        boolean result = categoryService.updateCategory(userId,categoryId,inputCategory);
        if (!result){
            return ResponseHandler.generateResponse("Failed update category",HttpStatus.BAD_REQUEST);
        }
        return ResponseHandler.generateResponse("Success update category",HttpStatus.OK);

    }
}
