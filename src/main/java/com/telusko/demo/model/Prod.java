package com.telusko.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String brand;
    private Double price;
    private String category;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy") This can also be done from here but it is given in the frontend UI. So, here I have commented this one.
    private Date releaseDate;
    private Boolean prod_available;
    private Integer quantity;
    private String imageName;
    private String imageType;

    @Lob
    private byte[] imageDate;

    public void getImageType(@Nullable String contentType) {
    }
}
