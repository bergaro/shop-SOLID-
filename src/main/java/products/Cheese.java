package products;

import java.math.BigDecimal;

public class Cheese extends Product {
    private String cheeseBase;                                      //Сырная основа


    private Cheese(String productName, BigDecimal price) {
        super(productName, price);
    }
    /**
     * Приступить к созданию объекта cheese
     * @param productName имя продукта
     * @param price цена продукта за шт.
     * @return экземпляр класса Cheese
     */
    public static Cheese build(String productName, BigDecimal price) {
        return new Cheese(productName, price);
    }

    public Cheese pWeight(double productWeight) {
        super.setProductWeight(productWeight);
        return this;
    }

    public Cheese pCountry(String countryOfOrigin) {
        super.setCountryOfOrigin(countryOfOrigin);
        return this;
    }

    public Cheese pCheeseBase(String cheeseBase) {
        this.cheeseBase = cheeseBase;
        return this;
    }

    public Cheese pQuantity(int productQuantity) {
        super.setProductQuantity(productQuantity);
        return this;
    }

}
