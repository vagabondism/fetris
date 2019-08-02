package io.fetris.domain.inventory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class InventoryTest {


    @Test
    public void 인벤토리는_보관된_재료들을_반환한다() {
        // given
        Inventory inventory = new Inventory();
        assertThat(inventory.getAllIngredients()).hasSize(0);

        // when
        inventory.keep(new Ingredient(), new Ingredient());

        // then
        assertThat(inventory.getAllIngredients()).hasSize(2);
    }

//    @Test
//    public void 인밴토리는_보관된_재료를_꺼낼_수_있다() {
//        Inventory inventory = new Inventory();
//
//        inventory.keep(new Ingredient(), new Ingredient());
//
//
//    }
}
