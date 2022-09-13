package com.obider.expensetrackerapi.transaction.entity;

public class ShowTransaction {
    private Integer id;
    private Double amount;
    private String note;

    public ShowTransaction(){

    }
    public ShowTransaction(Integer id, Double amount, String note) {
        this.id = id;
        this.amount = amount;
        this.note = note;
    }

    public ShowTransaction(Transaction transaction) {
        this(transaction.getId(),transaction.getAmount(),transaction.getNote());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
