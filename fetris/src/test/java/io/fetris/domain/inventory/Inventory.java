package io.fetris.domain.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Inventory {

    private List<Ingredient> ingredients = new ArrayList<>();

    public List<Ingredient> getAllIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    public void keep(Ingredient ...ingredient) {
        ingredients.addAll(Arrays.asList(ingredient));
    }
}
