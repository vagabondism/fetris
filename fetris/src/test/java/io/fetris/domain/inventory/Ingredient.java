package io.fetris.domain.inventory;


import java.time.LocalDate;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Ingredient {

    private final String name;
    private final LocalDate keepDate;

    public Ingredient(String name, LocalDate keepDate) {
        this.name = name;
        this.keepDate = keepDate;
    }

    public String getName() {
        return name;
    }

    LocalDate getKeepDate() {
        return keepDate;
    }
}
