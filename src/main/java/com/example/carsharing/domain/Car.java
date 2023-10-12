package com.example.carsharing.domain;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

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
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mark;
    private String model;
    private double motorCapacity;

    @Enumerated(EnumType.STRING)
    private Fuel fuel;
    @OneToMany(
            targetEntity = Borrow.class,
            mappedBy = "car",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Borrow> borrowList = new ArrayList<>();
}
