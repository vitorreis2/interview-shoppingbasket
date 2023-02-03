package com.interview.shoppingbasket;

import java.util.List;

public class RetailPriceCheckoutStep implements CheckoutStep {
    private PricingService pricingService;
    private double retailTotal;

    public RetailPriceCheckoutStep(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @Override
    public void execute(CheckoutContext checkoutContext) {
        Basket basket = checkoutContext.getBasket();
        List<Promotion> promotions = checkoutContext.getPromotions();
        retailTotal = 0.0;

        for (BasketItem basketItem: basket.getItems()) {
            int quantity = basketItem.getQuantity();
            double price = pricingService.getPrice(basketItem.getProductCode());
            basketItem.setProductRetailPrice(price);
            retailTotal += quantity*price;

            for (Promotion promotion : promotions) {
                retailTotal = this.applyPromotion(promotion, basketItem, retailTotal);
            }
        }

        checkoutContext.setRetailPriceTotal(retailTotal);
    }

    public double applyPromotion(Promotion promotion, BasketItem item, double price) {

        if (promotion.appliesTo(item.getProductCode())) {
            price = promotion.apply(price);
        }
        return price;
    }
}