package com.scm20.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {


    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    private String discription;
    private boolean favourite = false;

    private String cloudinaryImagePublicId;

    public boolean getFavourite(){
        return favourite;
    }
        
    /// mapping with other tables

    @OneToMany(mappedBy = "contact" , cascade = CascadeType.ALL , fetch = FetchType.EAGER , orphanRemoval = true)    
    private List<SocialLinks> socialLinks = new ArrayList<>();

    @ManyToOne()
    @JsonIgnore
    private User user;


}
