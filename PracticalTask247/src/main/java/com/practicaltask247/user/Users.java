package com.practicaltask247.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    private String name;
    @Column(name = "email",unique = true)
    @Email(regexp = "^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$", message = "Enter valid email id")
    private String email;
    private String password;

    public Users(Users users){
        this.Id = users.getId();
        this.name = users.getName();
        this.email = users.getEmail();
        this.password = users.getPassword();
    }
}
