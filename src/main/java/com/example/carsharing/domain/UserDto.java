package com.example.carsharing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String name;
    private boolean isTopCustomer;
    private List<Long> rentIds;
}
