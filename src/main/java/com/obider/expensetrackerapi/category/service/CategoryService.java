package com.obider.expensetrackerapi.category.service;

import com.obider.expensetrackerapi.category.entity.Category;
import com.obider.expensetrackerapi.category.input.InputCategory;
import com.obider.expensetrackerapi.user.entity.User;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories(Integer userId);
    Category getUserCategoryById(Integer userId, Integer categoryId);
    void addCategory(User user, InputCategory inputCategory);
    boolean updateCategory(Integer userId, Integer categoryId, InputCategory inputCategory);
    boolean removeCategoryWithTransactions(Integer userId, Integer categoryId);
}
