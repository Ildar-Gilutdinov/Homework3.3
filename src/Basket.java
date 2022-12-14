import java.io.*;

public class Basket {

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

    public void saveTxt(File textFile) throws FileNotFoundException { //метод сохранения корзины в текстовый файл;
        try (PrintWriter out = new PrintWriter(textFile)) {
            for (String s : getProducts()) //продукты
                out.print(s + " ");
            out.print("\n"); //чтение по строке
            for (int i : getPrices()) // цены
                out.print(i + " ");
            out.print("\n"); //чтение по строке
            for (long e : getProductBasket()) // кол-во продуктов
                out.print(e + " ");
        }
    }

    static Basket loadFromTxtFile(File textFile) throws Exception {
        if (textFile.exists()) {
            try (BufferedReader in = new BufferedReader(new FileReader(textFile));) {

                String[] products = in.readLine().strip().split(" ");

                String[] pricesStr = in.readLine().strip().split(" ");
                int[] prices = new int[pricesStr.length];

                for (int i = 0; i < prices.length; i++) {
                    prices[i] = Integer.parseInt(pricesStr[i]);
                }

                Basket basket = new Basket(products, prices);

                String[] amountsStr = in.readLine().strip().split(" ");

                for (int i = 0; i < amountsStr.length; i++) {
                    basket.productBasket[i] = Integer.parseInt(amountsStr[i]);
                }
                return basket;
            }
        } else {
            String[] products = {"Хлеб", "Яблоки", "Молоко", "Рыба"};  //товар
            int[] prices = {60, 120, 50, 250};  //цена
            Basket basket = new Basket(products, prices);  //в корзину продукты и цену
            return basket;
        }
    }

    public String[] getProducts() {
        return products;
    }

    public int[] getPrices() {
        return prices;
    }

    public long[] getProductBasket() {
        return productBasket;
    }
}
