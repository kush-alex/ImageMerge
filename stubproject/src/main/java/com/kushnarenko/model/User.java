package com.kushnarenko.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "USER_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "USER_FACEBOOK_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String facebookId;

    @NotEmpty
    @Size(min = 8, max = 60)
    @Column(name = "NAME", nullable = false)
    private String name = new String();

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private Date dateOfBirth = new Date();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Thing> things = new LinkedHashSet<>();

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", facebookId='" + facebookId + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", role=" + role +
                '}';
    }
}
