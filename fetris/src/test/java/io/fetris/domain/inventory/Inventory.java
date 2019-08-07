package io.fetris.domain.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Inventory {

    private List<Ingredient> ingredients = new ArrayList<>();

    public List<Ingredient> getAllIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    public void keep(Ingredient... ingredient) {
        validateIngredient(ingredient);

        ingredients.addAll(Arrays.asList(ingredient));
    }

    public Optional<Ingredient> takeOut(String ingredientName) {
        return ingredients.stream()
                .filter(x -> x.getName().equals(ingredientName))
                .findFirst();
    }

    private void validateIngredient(Ingredient[] ingredient) {
        if (Objects.isNull(ingredient)) throw new IllegalArgumentException("null 인 재료는 넣을 수 없습니다.");

        long count = Arrays.stream(ingredient)
            .filter(Objects::isNull)
            .count();

        if (count > 0) throw new IllegalArgumentException("null 인 재료는 넣을 수 없습니다.");
    }
}
