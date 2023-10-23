package com.example.carsharing.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mark;
    private String model;
    private double motorCapacity;
    @Enumerated(EnumType.STRING)
    private Status status=Status.AVAILABLE;

    @Enumerated(EnumType.STRING)
    private Fuel fuel;
    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "car",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<Rent> rentList = new ArrayList<>();
}
