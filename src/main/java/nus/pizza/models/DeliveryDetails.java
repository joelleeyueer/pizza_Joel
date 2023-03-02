package nus.pizza.models;

import jakarta.validation.constraints.*;

public class DeliveryDetails {
    
    @NotNull(message="Please input your name or alias")
    @Size(min=3, message="Name too short (<3 characters)")
    private String name;

    @NotNull(message="Please input the delivery address or postal code")
    @Size(min=6, message="Address too short (<6 characters)")
    private String address;

    @NotNull(message="Please input your phone number.")
    @Pattern(regexp="(\\6|8|9)[0-9]{7}", message="Please input a valid Singaporean phone number")
    private String phone;

    private Boolean rush;

   
    private Integer tip=0;

    private String comments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getRush() {
        return rush;
    }

    public void setRush(Boolean rush) {
        this.rush = rush;
    }

    public Integer getTip() {
        return tip;
    }

    public void setTip(Integer tip) {
        this.tip = tip;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    

}
