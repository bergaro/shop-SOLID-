package products;

import java.math.BigDecimal;

public class Watermelon extends Product{
    private String watermelonVariety;                               //Сорт арбуза

    private Watermelon(String productName, BigDecimal price) {
        super(productName, price);
    }
    /**
     * Приступить к созданию объекта watermelon
     * @param productName им продукта
     * @param price цена продукта за шт.
     * @return экземпляр класса Strawberry
     */
    public static Watermelon build(String productName, BigDecimal price) {
        return new Watermelon(productName, price);
    }

    public Watermelon pWeight(double weight) {
        super.setProductWeight(weight);
        return this;
    }

    public Watermelon pCountry(String countryOfOrigin) {
        super.setCountryOfOrigin(countryOfOrigin);
        return this;
    }

    public Watermelon pWatermelonVariety(String watermelonVariety) {
        this.watermelonVariety = watermelonVariety;
        return this;
    }

    public Watermelon pQuantity(int productQuantity) {
        super.setProductQuantity(productQuantity);
        return this;
    }

}
