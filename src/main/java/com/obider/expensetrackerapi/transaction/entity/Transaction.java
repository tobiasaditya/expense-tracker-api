package com.obider.expensetrackerapi.transaction.entity;

import com.obider.expensetrackerapi.category.Category;
import com.obider.expensetrackerapi.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "transaction_seq",
            sequenceName = "transaction_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_seq"
    )
    private Integer id;
    private double amount;
    private String note;
    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Transaction(){

    }
    public Transaction(Integer id, double amount, String note, Long transactionDate, User user, Category category) {
        this.id = id;
        this.amount = amount;
        this.note = note;
        this.transactionDate = LocalDateTime.now();
        this.user = user;
        this.category = category;
    }
    public Transaction(double amount, String note, User user, Category category) {
        this.amount = amount;
        this.note = note;
        this.transactionDate = LocalDateTime.now();
        this.user = user;
        this.category = category;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", note='" + note + '\'' +
                ", transactionDate=" + transactionDate +
                ", user=" + user +
                ", category=" + category +
                '}';
    }
}
