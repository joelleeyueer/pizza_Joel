package nus.pizza.models;

import jakarta.validation.constraints.*;

public class DeliveryDetails {
    
    @NotNull(message="Please input your name or alias")
    @Min(value=3, message="Name too short (<3 characters)")
    private String name;

    @NotNull(message="Please input the delivery address or postal code")
    @Min(value=6)
    private String address;

    @NotNull(message="Please input your phone number.")
    @Pattern(regexp="(\\6|8|9)[0-9]{7}", message="Please input a valid Singaporean phone number")
    @Min(value=8)
    private String phone;

    private Boolean rush;

   
    private Integer tip;

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
