package com.obider.expensetrackerapi.category.entity;

import com.obider.expensetrackerapi.transaction.entity.Transaction;
import com.obider.expensetrackerapi.user.entity.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @SequenceGenerator(
            name = "categories_seq",
            sequenceName = "categories_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "categories_seq"
    )
    private Integer id;
    private String title;
    private String description;
    @Transient
    private Double totalExpense=0d;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "category")
    private List<Transaction> transactions;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category() {
    }

    public Category(String title, String description, User user) {
        this(null,title,description,user);
    }

    public Category(Integer id, String title, String description, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", totalExpense=" + totalExpense +
                ", user=" + user +
                '}';
    }

    interface ShowCategory{
        Integer getId();
        String getTitle();
        String getDescription();
        String getUser_Id();
    }
}
