package com.obider.expensetrackerapi.category.entity;

import com.obider.expensetrackerapi.category.Category;

import java.util.ArrayList;
import java.util.List;

interface IShowCategory{
    Integer getId();
    String getTitle();
    String getDescription();

}

public class ShowCategory implements IShowCategory {
    private Integer id;
    private String title;
    private String description;

    public ShowCategory(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public ShowCategory(Category category) {
        this(category.getId(), category.getTitle(), category.getDescription());
    }


    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ShowCategory{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
