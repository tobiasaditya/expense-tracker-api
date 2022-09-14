package com.obider.expensetrackerapi.category.service;

import com.obider.expensetrackerapi.category.entity.Category;
import com.obider.expensetrackerapi.category.input.InputCategory;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories(Integer userId);
    Category getUserCategoryById(Integer userId, Integer categoryId);
    void addCategory(Integer userId, InputCategory inputCategory);
    boolean updateCategory(Integer userId, Integer categoryId, InputCategory inputCategory);
    boolean removeCategoryWithTransactions(Integer userId, Integer categoryId);
}
