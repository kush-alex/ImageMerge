package com.kushnarenko.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@javax.persistence.Entity
public class User {

    @Id
    @Column(name = "USER_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "USER_FACEBOOK_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long facebookId;

    @NotEmpty
    @Size(min = 8, max = 60)
    @Column(name = "USER_NAME", unique = true, nullable = false)
    private String username;

    @NotEmpty
    @Size(min = 2, max = 60)
    @Column(name = "PASSWORD", nullable = false)
    @JsonIgnore
    private String password;

    @NotEmpty
    @Size(min = 8, max = 60)
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private Date dateOfBirth;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Entity> userEntity = new HashSet<>(0);

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "USER_FRIEND", joinColumns = {@JoinColumn(name = "USER_ID")})
    private Set<User> friends = new HashSet<>();

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

}
