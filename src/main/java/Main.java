import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {


//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document doc = builder.parse(new File("shop.xml"));

        File jsonFile = new File("basket.json");
        Basket basket = null;
        ClientLog clientLog = new ClientLog();

//        try {
//            basket = Basket.loadFromTxtFile(textFile);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        try {
            basket = Basket.loadFromJson(jsonFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println("Программа планировщик продуктовой корзины");
        System.out.println("Список возможных товаров для покупки");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < basket.getProducts().length; i++) {
            System.out.println((i + 1) + " " + basket.getProducts()[i] + " " + basket.getPrices()[i] + " руб/шт"); // вывод списка товаров и их цен
        }
        while (true) {                             //цикл while
            System.out.println("Выберите товар и количество или введите 'end' ");
            String inputString = scanner.nextLine(); // Ввод данных
            int productNumber = 0;
            int productCount = 0;
            if ("end".equals(inputString)) {       // выход из программы
                System.out.println("Ваша корзина:");
                break;
            }
            String[] myPrice = inputString.split(" "); // разделитель строки по пробелу
            productNumber = Integer.parseInt(myPrice[0]) - 1; // приведение ввода пользователя к значению массива
            productCount = Integer.parseInt(myPrice[1]); // ввод кол-ва товара пользователем

            clientLog.log(productNumber,productCount);
            basket.addToCart(productNumber, productCount);

        }

        clientLog.exportAsCSV(txtFile);
        //basket.saveTxt(textFile);
        basket.saveJson(jsonFile);

        basket.printCart();

    }
}

