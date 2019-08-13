package io.fetris.domain.inventory;


import java.time.LocalDate;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Ingredient {

    private final String name;
    private final LocalDate keepDate;
    private final int count;

    public Ingredient(String name, LocalDate keepDate, int count) {
        this.name = name;
        this.keepDate = keepDate;
        this.count = count;
    }

    public Ingredient(String name, LocalDate keepDate) {
        this(name, keepDate, 1);
    }

    String getName() {
        return name;
    }

    LocalDate getKeepDate() {
        return keepDate;
    }

    public int getCount() {
        return count;
    }
}
