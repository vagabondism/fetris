package io.fetris.domain.inventory;

import static io.fetris.domain.inventory.InventoryException.INGREDIENT_NOT_FOUND;
import static io.fetris.domain.inventory.InventoryException.INGREDIENT_SHOULD_NOT_BE_NULL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Inventory {

    private List<Ingredient> ingredients = new ArrayList<>();

    public Inventory(Ingredient... ingredients) {
        this.keep(ingredients);
    }

    public List<Ingredient> getAllIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    public void keep(Ingredient... ingredients) {
        validateIngredient(ingredients);

        this.ingredients.addAll(Arrays.asList(ingredients));
    }

    public Ingredient takeOut(String ingredientName) {
        Ingredient ingredient = getOldestIngredient(ingredientName);
        return takeOut(ingredient.getName(), ingredient.getCount());
    }

    public Ingredient takeOut(String ingredientName, int count) {
        Ingredient ingredient = getOldestIngredient(ingredientName);

        ingredients.remove(ingredient);
        int count1 = ingredient.getCount() - count;

        if (count1 > 0) {
            keep(new Ingredient(ingredient.getName(), ingredient.getKeepDate(), count1));
        }

        return new Ingredient(ingredient.getName(), ingredient.getKeepDate(), count);
    }

    private Ingredient getOldestIngredient(String ingredientName) {
        return ingredients.stream()
            .filter(x -> x.getName().equals(ingredientName))
            .sorted(Comparator.comparing(Ingredient::getKeepDate))
            .findFirst()
            .orElseThrow(() -> new InventoryException(INGREDIENT_NOT_FOUND));
    }

    private void validateIngredient(Ingredient[] ingredient) {
        if (Objects.isNull(ingredient)) {
            throw new InventoryException(INGREDIENT_SHOULD_NOT_BE_NULL);
        }
        if(Arrays.stream(ingredient).anyMatch(Objects::isNull)) {
            throw new InventoryException(INGREDIENT_SHOULD_NOT_BE_NULL);
        }
    }
}
