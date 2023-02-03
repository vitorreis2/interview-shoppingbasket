package com.interview.shoppingbasket;

import java.util.List;

public class PromotionCheckoutStep implements CheckoutStep {

    private PromotionsService promotionService;

    public PromotionCheckoutStep(PromotionsService promotionService) {
        this.promotionService = promotionService;
    }

    @Override
    public void execute(CheckoutContext context) {
        Basket basket = context.getBasket();
        List<Promotion> promotions = promotionService.getPromotions(basket);
        context.setPromotions(promotions);
    }

}
