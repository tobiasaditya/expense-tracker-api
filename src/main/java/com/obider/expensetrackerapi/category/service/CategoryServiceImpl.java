package com.obider.expensetrackerapi.category.service;

import com.obider.expensetrackerapi.category.entity.Category;
import com.obider.expensetrackerapi.category.input.InputCategory;
import com.obider.expensetrackerapi.category.repository.CategoryRepository;
import com.obider.expensetrackerapi.user.service.UserService;
import com.obider.expensetrackerapi.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    UserService userService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, UserService userService) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    @Override
    public List<Category> getAllCategories(Integer userId) {
        Optional<List<Category>> foundCategories = categoryRepository.findByUser_Id(userId);
        if (foundCategories.isEmpty()){
            return new ArrayList<>();
        }
        return foundCategories.get();
    }

    @Override
    public Category getUserCategoryById(Integer userId, Integer categoryId) {
        Optional<Category> foundCategory =categoryRepository.findByIdAndUser_Id(categoryId,userId);
        if (foundCategory.isEmpty()){
            return null;
        }
        return foundCategory.get();
    }

    @Override
    public void addCategory(User user, InputCategory inputCategory) {
        Category newCategory = new Category(inputCategory.getTitle(),inputCategory.getDescription(),user);
        categoryRepository.save(newCategory);
    }

    @Override
    public boolean updateCategory(Integer userId, Integer categoryId, InputCategory inputCategory) {
        Category foundCategory = getUserCategoryById(userId,categoryId);
        if (foundCategory == null){
            return false;
        }
        foundCategory.setTitle(inputCategory.getTitle());
        foundCategory.setDescription(inputCategory.getDescription());
        categoryRepository.save(foundCategory);
        return true;
    }

    @Override
    public boolean removeCategoryWithTransactions(Integer userId, Integer categoryId) {
        Category foundCategory = getUserCategoryById(userId,categoryId);
        if (foundCategory == null){
            return false;
        }
        categoryRepository.deleteById(foundCategory.getId());
        return true;
    }
}
