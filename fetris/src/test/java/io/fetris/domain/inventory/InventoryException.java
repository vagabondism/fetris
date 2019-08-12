package io.fetris.domain.inventory;

public class InventoryException extends RuntimeException {

    public static final String INGREDIENT_NOT_FOUND = "존재하지 않는 재료입니다.";
    public static final String INGREDIENT_SHOULD_NOT_BE_NULL = "null 인 재료는 넣을 수 없습니다.";

    public InventoryException(String message) {
        super(message);
    }
}
