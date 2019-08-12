package io.fetris.domain.inventory;

import static io.fetris.domain.inventory.InventoryException.INGREDIENT_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class InventoryTest {


    @Test
    public void 인벤토리에서_보관된_재료들을_조회할_수_있다() {
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
        Ingredient onion = new Ingredient("양파");
        Ingredient garlic = new Ingredient("마늘");
        Inventory inventory = new Inventory();
        inventory.keep(onion, garlic);

        // when
        Ingredient ingredient = inventory.takeOut(garlic.getName());

        // then
        assertThat(ingredient).isEqualTo(garlic);
        assertThat(inventory.getAllIngredients()).containsExactly(onion);
    }

    @Test
    public void 존재하지_않는_재료를_꺼내면_예외() {
        // given
        Inventory inventory = new Inventory();
        Ingredient onion = new Ingredient("양파");
        inventory.keep(onion);

        // when & than
        assertThatThrownBy(() -> inventory.takeOut("용의 혀"))
            .isInstanceOf(InventoryException.class)
            .hasMessage(INGREDIENT_NOT_FOUND);
    }

    @Test
    public void 인벤토리에서_재료를_꺼낼때_다수의_동일한_재료가_있을때는_오래된_것부터_꺼낸다() {
        // given
        Ingredient oldOnion = new Ingredient("양파", LocalDate.now().minusDays(1));
        Ingredient oldestOnion = new Ingredient("양파", LocalDate.now().minusDays(2));
        Ingredient rottenOnion = new Ingredient("양파", LocalDate.now().minusDays(7));

        // when & then
        assertThat(new Inventory(oldOnion, rottenOnion).takeOut("양파")).isEqualTo(rottenOnion);
        assertThat(new Inventory(rottenOnion, oldOnion).takeOut("양파")).isEqualTo(rottenOnion);
        assertThat(new Inventory(oldestOnion, rottenOnion, oldOnion).takeOut("양파")).isEqualTo(rottenOnion);
        assertThat(new Inventory(rottenOnion).takeOut("양파")).isEqualTo(rottenOnion);
    }

    @Test
    public void 재료를_null_로_넣으면_IllegalArgumentExceiption을_반환한다() {
        // given
        Inventory inventory = new Inventory();

        // when & then
        assertThatThrownBy(() -> inventory.keep(null)).isInstanceOf(InventoryException.class);
        assertThatThrownBy(() -> inventory.keep(null, null)).isInstanceOf(InventoryException.class);
        assertThatThrownBy(() -> inventory.keep(new Ingredient("마늘", LocalDate.now()), null)).isInstanceOf(InventoryException.class);
    }
}
