package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
    private List<BasketItem> items = new ArrayList<>();

    public void add(String productCode, String productName, int quantity) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProductCode(productCode);
        basketItem.setProductName(productName);
        basketItem.setQuantity(quantity);

        items.add(basketItem);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void consolidateItems() {
        Map<String, BasketItem> consolidatedItems = new HashMap<>();

        for (BasketItem item : items) {
            if (consolidatedItems.containsKey(item.getProductCode())) {
                BasketItem consolidatedItem = consolidatedItems.get(item.getProductCode());
                consolidatedItem.setQuantity(consolidatedItem.getQuantity() + item.getQuantity());
            } else {
                consolidatedItems.put(item.getProductCode(), item);
            }
        }
        items.clear();
        items.addAll(consolidatedItems.values());
    }
}
