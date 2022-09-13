package com.obider.expensetrackerapi.transaction.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InputTransaction {
    @NotNull
    private Double amount;
    @NotEmpty
    private String note;

    public InputTransaction(Double amount, String note) {
        this.amount = amount;
        this.note = note;
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
