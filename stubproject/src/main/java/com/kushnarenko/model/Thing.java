package com.kushnarenko.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Thing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Size(max = 60)
    @Column(name = "FIELD", nullable = false)
    private String field;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="USER_ID")
    private User user;

    @JsonBackReference
    @JoinColumn(name="IMAGE1")
    private String image1;

    @JsonBackReference
    @JoinColumn(name="IMAGE2")
    private String image2;

    @JsonBackReference
    @JoinColumn(name="RESULT_IMAGE")
    private String resultImage;

    @Override
    public String toString() {
        return "Thing{" +
                "id=" + id +
                ", field='" + field + '\'' +
                '}';
    }

    public void setImages(List<String> images){
        setImage1(images.get(0));
        setImage2(images.get(1));
    }
}
