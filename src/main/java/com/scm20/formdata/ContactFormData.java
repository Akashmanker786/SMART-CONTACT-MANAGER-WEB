package com.scm20.formdata;

import org.springframework.web.multipart.MultipartFile;

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

    private String name;

    
    private String email;

    
    private String phoneNumber;

    
    private String address;


    private String picture;
    private String discription;
    private boolean favourite;



    private MultipartFile contactImage;



    public void setFavourite(boolean value){
        favourite = value;
    }

    public boolean getFavourite(){
        return favourite;
    }
}
