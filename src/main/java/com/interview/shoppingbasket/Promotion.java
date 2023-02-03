package com.interview.shoppingbasket;
import lombok.Data;

@Data
public class Promotion {
    private String productCode;
    private String  type;

    public Promotion(String productCode, String type) {
        this.productCode = productCode;
        this.type = type;
    }

    public boolean appliesTo(String productCode) {
        return this.productCode.equals(productCode);
    }

    public double apply(double itemTotal) {
        switch (type) {
            case "Two_for_One":
                return itemTotal / 2 + itemTotal % 2;
            case "Fifty_Percent_Off":
                return (int) (itemTotal * 0.5);
            case "Ten_Percent_Off":
                return (int) (itemTotal * 0.9);
            default:
                return itemTotal;
        }
    }
}
