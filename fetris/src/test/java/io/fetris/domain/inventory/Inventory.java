package io.fetris.domain.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Inventory {

    private List<Ingredient> ingredients = new ArrayList<>();

    public List<Ingredient> getAllIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    public void keep(Ingredient... ingredient) {
        ingredients.addAll(Arrays.asList(ingredient));
    }

    public Optional<Ingredient> takeOut(String ingredientName) {
        return ingredients.stream()
                .filter(x -> x.getName().equals(ingredientName))
                .findFirst();
    }
}