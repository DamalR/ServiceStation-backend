package lk.damal.hdrservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LubricantOil implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oilId;
    private String brand;
    private String viscosity;
    private double liters;
    private String fuelType;
    private double price;

    @JsonIgnore
    @OneToMany(mappedBy = "lubricantOil")
    private List<Category> categories = new ArrayList<>();
}
