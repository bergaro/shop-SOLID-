package user;

import products.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
    private Map<Product, Integer> productList;                      // Не смотря на Map - является списком товаров
    private BigDecimal totalSum;

    Basket() {
        productList = new HashMap<>();
        totalSum = new BigDecimal(0);
    }
    /**
     * Возвращает объект(Корзину) списка(Map) заказа
     * @return
     */
    public Map<Product, Integer> getRealBasket() {
        return productList;
    }
    public void resetToZero() {
        BigDecimal zero = new BigDecimal(0);
        totalSum = totalSum.multiply(zero);
    }
    /**
     * Получить итоговую сумму козины
     * @return
     */
    public BigDecimal getTotalSum() {
        return totalSum;
    }
    /**
     * Добавить товар в корзину
     * @param product продукт
     * @param quantity необходимое кол-во продукта
     */
    public void setProductInBasket(Product product, int quantity) {
        BigDecimal posSum;
        productList.put(product, quantity);
        posSum = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        totalSum = totalSum.add(posSum);

    }
    /**
     * Получить содержимое корзины
     * @return
     */
    public String getProductList() {
        String myBasket = "\nКорзина: \n";
        List<Product> products = new ArrayList<>(productList.keySet());
        int numProduct;
        if(productList.isEmpty()) {
            return "Корзина заказов пуста.";
        } else {
            for(Product product : products) {
                numProduct = productList.get(product);
                myBasket += "- " + product.getProductName() + ", кол-во: " + numProduct + " шт.\n";
            }
            myBasket += "Сумма заказа: " + totalSum + " руб. \n";

            return myBasket;
        }
    }

}
