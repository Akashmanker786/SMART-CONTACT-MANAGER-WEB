package com.scm20.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User  implements UserDetails {

    @Id
    private String userId;
    
    @Column(nullable = false)
     private String name;
    
    @Column(unique = true , nullable = false)
    private String email;

    @Getter(value = AccessLevel.NONE)
    private String password;

    private String about;

    @Column(length = 1000)
    private String profilePic;

    private String phoneNumber;
    
    //information
    @Getter(value = AccessLevel.NONE)
    private boolean isEnabled = true;

    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;

    


//  Mapping with other tables

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)

    private List<Contact> contacts = new ArrayList<>();




    // UserDetails interface methods

    @ElementCollection(fetch =  FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      Collection<SimpleGrantedAuthority> roles  =  roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }


    @Override
    public String getUsername() {
        System.out.println("get username method running of userDetails");
        return this.email;   
    }


    @Override
    public boolean isAccountNonExpired() {        
        return true;   
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        
       return true;
    }


    @Override
    public String getPassword() {
        System.out.println("get password method running of userDetails");
        return this.password;

    }


    @Override
    public boolean isEnabled() {
       return this.isEnabled;
    }
}
