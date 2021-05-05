package products;

import java.math.BigDecimal;

/**
 * Создано для демонстрации принципа замены Барбары Лисков.
 * Product - абстрактный предок всех продуктов из магазина.
 * Соответственно, наслодоваться от него могут только продукты,
 * а не корзина товаров или кассир/покупатель.
 */
public abstract class Product {
    private final String productName;                               // Наименование товара
    private double productWeight;                                   // Вес продукта
    private String countryOfOrigin;                                 // Страна производитель
    private final BigDecimal price;                                 // Цена продукта
    private int productQuantity;                                    // Кол-во товаров в магазине
    private int rating = 0;                                             // Рейтинг товаров
    private boolean recommendProduct = false;

    public Product(String productName, BigDecimal price) {
        this.productName = productName;
        this.price = price;
    }

    /**
     * Получить наименование продукта
     * @return наименование продукта
     */
    public String getProductName() {
        return productName;
    }
    /**
     * Получить вес продукта
     * @return вес продукта
     */
    public double getProductWeight() {
        return productWeight;
    }
    /**
     * Получить страну производитель продукта
     * @return страна производитель продукта
     */
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }
    /**
     * Получить цену продукта
     * @return цена продукта
     */
    public BigDecimal getPrice() {
        return price;
    }
    /**
     * Получить кол-во продукции на складе
     * @return кол-во продукции по складу
     */
    public int getProductQuantity() {
        return productQuantity;
    }
    /**
     * Получить рейтинг товара
     * @return рейтинг товара
     */
    public int getRating() { return rating; }
    /**
     * Получить флаг рекомендуемдации продукта
     * @return
     */
    public boolean getRecommendProduct() { return recommendProduct; }
    /**
     * Установить вес продукта
     * @param productWeight вес продукта
     */
    protected void setProductWeight(double productWeight) {
        this.productWeight = productWeight;
    }
    /**
     * Установить страну производитель
     * @param countryOfOrigin страна производитель
     */
    protected void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }
    /**
     * Установить кол-во продукции на складе
     * @param productQuantity кол-во продукции на складе
     */
    public void setProductQuantity(int productQuantity) { this.productQuantity = productQuantity; }
    /**
     * Установить определённый рейтинг для товара
     * @param rating
     */
    public void setRating(int rating) {
        if(rating > 0) {
            this.rating = rating;
        }
    }
    /**
     * Добавить товар в рекомендуемые
     * @param recommendProduct
     */
    public void setRecommendProduct(boolean recommendProduct) {
        this.recommendProduct = recommendProduct;
    }
    /**
     * Увеличить рейтинг товара на 1 единицу
     */
    public void iterationRating() {
        rating++;
    }
    /**
     * Уменьшить рейтинг товара на 1 единицу
     */
    public void decrementRating() {
        if(rating != 0) {
            rating--;
        }
    }
    @Override
    public String toString() {
        return productName + ", производство: " + countryOfOrigin + ", кол-во: "+ productQuantity +
                " шт., цена за ед. " + price + " руб.";
    }
}
