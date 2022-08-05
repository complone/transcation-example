package com.example.car;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class CarBooking {
    private Integer id;
    private String name;
    private Integer amount;
    private boolean confirmed;
    private boolean cancelled;
    
    Integer getId() {
        return id;
    }
    
    void setId(Integer id) {
        this.id = id;
    }
    
    String getName() {
        return name;
    }
    
    void setName(String name) {
        this.name = name;
    }
    
    Integer getAmount() {
        return amount;
    }
    
    void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    boolean isConfirmed() {
        return confirmed;
    }
    
    void confirm() {
        this.confirmed = true;
        this.cancelled = false;
    }
    
    boolean isCancelled() {
        return cancelled;
    }
    
    void cancel() {
        this.confirmed = false;
        this.cancelled = true;
    }
}
