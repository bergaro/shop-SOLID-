package products;

import java.math.BigDecimal;

public class Strawberry extends Product {
    private int berriesPerPack;                                     // Количесвто ягод в упаковке


    private Strawberry(String productName, BigDecimal price) {
        super(productName, price);
    }
    /**
     * Приступить к созданию объекта strawberry
     * @param productName имя продукта
     * @param price цена продукта за шт.
     * @return экземпляр класса Strawberry
     */
    public static Strawberry build(String productName, BigDecimal price) {
        return new Strawberry(productName, price);
    }

    public Strawberry pWeight(double weight) {
        super.setProductWeight(weight);
        return this;
    }

    public Strawberry pCountry(String countryOfOrigin) {
        super.setCountryOfOrigin(countryOfOrigin);
        return this;
    }

    public Strawberry pBerriesPerPack(int berriesPerPack) {
        this.berriesPerPack = berriesPerPack;
        return this;
    }

    public Strawberry pQuantity(int productQuantity) {
        super.setProductQuantity(productQuantity);
        return this;
    }


}
