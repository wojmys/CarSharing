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
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isTopCustomer;
    @OneToMany(
            targetEntity = Borrow.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Borrow> borrowList = new ArrayList<>();


}
