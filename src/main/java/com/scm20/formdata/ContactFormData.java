package com.scm20.formdata;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactFormData {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Address [xyz@gmail.com]")
    private String email;

    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^[0-9]{10}$",message = "invalid Phone Number")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;


    private String picture;
    private String discription;
    private boolean favourite;


    public void setFavourite(boolean value){
        favourite = value;
    }

    public boolean getFavourite(){
        return favourite;
    }
}
