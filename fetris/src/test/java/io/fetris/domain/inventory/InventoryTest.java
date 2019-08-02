package io.fetris.domain.inventory;

import java.util.Optional;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InventoryTest {


    @Test
    public void 인벤토리는_보관된_재료들을_반환한다() {
        // given
        Inventory inventory = new Inventory();
        assertThat(inventory.getAllIngredients()).hasSize(0);

        // when
        inventory.keep(new Ingredient("양파"), new Ingredient("마늘"));

        // then
        assertThat(inventory.getAllIngredients()).hasSize(2);
    }

    @Test
    public void 인밴토리는_보관된_재료를_꺼낼_수_있다() {
        // given
        String ingredientName = "양파";
        Inventory inventory = new Inventory();
        inventory.keep(new Ingredient(ingredientName), new Ingredient("마늘"));

        // when
        Optional<Ingredient> ingredient = inventory.takeOut(ingredientName);

        // then
        assertThat(ingredient).isNotEmpty();
        assertThat(ingredient.get().getName()).isEqualTo(ingredientName);
    }
}
