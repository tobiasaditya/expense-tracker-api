package com.obider.expensetrackerapi.category.helper;

import com.obider.expensetrackerapi.category.entity.Category;
import com.obider.expensetrackerapi.category.entity.ShowCategory;

import java.util.ArrayList;
import java.util.List;

public class Formatter {
    public static List<ShowCategory> formatCategories(List<Category> categories){
        List<ShowCategory> formatted = new ArrayList<>();
        for (int i = 0; i< categories.size() ; i++){
            formatted.add(formatCategory(categories.get(i)));
        }
        return formatted;
    }
    public static ShowCategory formatCategory(Category category){
        return new ShowCategory(category);
    }
}
