package products;

import java.math.BigDecimal;

public class Potato extends Product {
    private String potatoVariety;                                   //Сорт картофеля

    private Potato(String productName, BigDecimal price) {
        super(productName, price);
    }
    /**
     * Приступить к созданию объекта potato
     * @param productName имя продукта
     * @param price цена продукта за шт.
     * @return экземпляр класса Potato
     */
    public static Potato build(String productName, BigDecimal price) {
        return new Potato(productName, price);
    }

    public Potato pWeight(double weight) {
        super.setProductWeight(weight);
        return this;
    }

    public Potato pCountry(String countryOfOrigin) {
        super.setCountryOfOrigin(countryOfOrigin);
        return this;
    }

    public Potato pVariety(String potatoVariety) {
        this.potatoVariety = potatoVariety;
        return this;
    }

    public Potato pQuantity(int productQuantity) {
        super.setProductQuantity(productQuantity);
        return this;
    }

}
