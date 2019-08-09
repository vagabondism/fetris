package io.fetris.domain.inventory;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InventoryTest {


    @Test
    public void 인벤토리는_보관된_재료들을_반환한다() {
        // given
        Inventory inventory = new Inventory();
        assertThat(inventory.getAllIngredients()).hasSize(0);

        // when
        inventory.keep(new Ingredient("양파", LocalDate.now()), new Ingredient("마늘", LocalDate.now()));

        // then
        assertThat(inventory.getAllIngredients()).hasSize(2);
    }

    @Test
    public void 인밴토리는_보관된_재료를_꺼낼_수_있다() {
        // given
        String ingredientName = "양파";
        Inventory inventory = new Inventory();
        inventory.keep(new Ingredient(ingredientName, LocalDate.now()), new Ingredient("마늘", LocalDate.now()));

        // when
        Optional<Ingredient> ingredient = inventory.takeOut(ingredientName);

        // then
        assertThat(ingredient).isNotEmpty();
        assertThat(ingredient.get().getName()).isEqualTo(ingredientName);
    }

    @Test
    public void 인벤토리에서_재료를_꺼낼때는_오래된_재료부터_꺼낸다() {
        String ingredientName = "양파";
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate today = LocalDate.now();

        Ingredient ingredient = getInventory(ingredientName, yesterday, today)
                .takeOut(ingredientName)
                .get();
        assertThat(ingredient).isEqualTo(new Ingredient(ingredientName, yesterday));

        Ingredient ingredient1 = getInventory(ingredientName, today, yesterday)
                .takeOut(ingredientName)
                .get();
        assertThat(ingredient1).isEqualTo(new Ingredient(ingredientName, yesterday));

        Ingredient ingredient2 = getInventory(ingredientName, today, yesterday, today)
                .takeOut(ingredientName)
                .get();
        assertThat(ingredient2).isEqualTo(new Ingredient(ingredientName, yesterday));

        Ingredient ingredient3 = getInventory(ingredientName, yesterday)
                .takeOut(ingredientName)
                .get();
        assertThat(ingredient3).isEqualTo(new Ingredient(ingredientName, yesterday));
    }

    private Inventory getInventory(String ingredientName, LocalDate ...localDate) {
        Inventory inventory = new Inventory();
        Arrays.stream(localDate).forEach(e -> inventory.keep(new Ingredient(ingredientName, e)));
        return inventory;
    }

    @Test
    public void 재료를_null_로_넣으면_IllegalArgumentExceiption을_반환한다() {
        // given
        Inventory inventory = new Inventory();

        // when & then
        assertThatThrownBy(() -> inventory.keep(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inventory.keep(null, null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inventory.keep(new Ingredient("마늘", LocalDate.now()), null)).isInstanceOf(IllegalArgumentException.class);
    }
}
