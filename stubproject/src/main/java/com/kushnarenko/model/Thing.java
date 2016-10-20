package com.kushnarenko.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Thing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Size(min = 8, max = 60)
    @Column(name = "FIELD", unique = true, nullable = false)
    private String field;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="USER_ID")
    private User user;

    @Override
    public String toString() {
        return "Thing{" +
                "id=" + id +
                ", field='" + field + '\'' +
                '}';
    }
}
