import java.io.*;

public class Basket implements Serializable {

    private String[] products;
    private int[] prices;
    private long[] productBasket;//продуктовая корзина

    public Basket(String[] products, int[] prices) {
        this.products = products; // название продукта
        this.prices = prices; // цена продукта
        productBasket = new long[products.length]; //корзина
    }

    public void addToCart(int productNum, int amount) { // метод добавления amount штук продукта номер productNum в корзину;
        productBasket[productNum] += amount;
    }

    public void printCart() { //метод вывода на экран покупательской корзины.
        int sumProduct = 0;
        int sumCount = 0;
        for (int i = 0; i < productBasket.length; i++) {
            if (productBasket[i] != 0) {          // // вывод "товаров" при покупке
                System.out.println(products[i] + " кол-во " + productBasket[i] + " шт " + prices[i] + " руб/шт " + (productBasket[i] * prices[i]) + " руб в сумме");
                sumProduct += productBasket[i] * prices[i]; // сумма всех затрат
                sumCount += productBasket[i];
            }
        }
        System.out.println("Кол-во продуктов в корзине: " + sumCount + " шт"); // кол-во продуктов
        System.out.println("Итого: " + sumProduct + " руб"); // вывод суммы всех затрат
    }

    public String[] getProducts() {
        return products;
    }

    public int[] getPrices() {
        return prices;
    }

    public void saveBin(File file) throws Exception { // сохранение файла в бинарном формате.
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
        }
    }

    public static Basket loadFromBinFile(File file) throws Exception { //загрузка корзины из бинарного файла
        if (file.exists()) { // проверка наличия файла
            Basket basket = null;
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                basket = (Basket) ois.readObject();
            }
            return basket;
        } else {
            String[] products = {"Хлеб", "Яблоки", "Молоко", "Рыба"};  //товар
            int[] prices = {60, 120, 50, 250};  //цена
            Basket basket = new Basket(products, prices);  //в корзину продукты и цену
            return basket;
        }
    }
}
