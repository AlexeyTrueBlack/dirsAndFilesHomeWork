package basket;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Basket {

    protected String[][] productsList; // двумерный массив-список товаров с ценами, доступных к покупке
    protected int[] itemsInCart; // массив количества каждого товара в корзине
    protected int bill = 0;


    public Basket(String[][] productsList) {
        this.productsList = productsList;
        this.itemsInCart = new int[productsList.length];
    }

    public void printBasket () {
        System.out.println("Перечень товаров к покупке:\n");
        if (productsList.length != 0) {
            for (int i = 0; i < productsList.length; i++) {
                System.out.println(productsList[i][0] + ", цена: " + productsList[i][1] + " руб/шт.");
            }
        }
    }

    public void addToCart(int productNum, int amount) {
        itemsInCart[productNum] += amount;
        System.out.println("Вы положили в корзину " + productsList[productNum][0] + ", " + amount + " шт.");
        bill += Integer.parseInt(productsList[productNum][1]) * amount;
    }

    public void printCart() {
        System.out.println("Итоговая корзина покупок:\n");
        for (int i = 0; i < itemsInCart.length; i++) {
            if (itemsInCart[i] != 0) {
                System.out.println(productsList[i][0] + ", " + productsList[i][1] + " руб/шт: "
                        + itemsInCart[i] + " шт, " + (Integer.parseInt(productsList[i][1]) * itemsInCart[i]) + " руб");
            }
        }
        System.out.println("Общая стоимость: " + bill);
    }

    public void saveTxt(File textFile) {
        try (BufferedWriter saveCartToFile = new BufferedWriter(new FileWriter(textFile))) {
            for (int i = 0; i < itemsInCart.length; i++) {
                if (itemsInCart[i] != 0) {
                    // заносим в файл наименование товара из списка
                    // количество товара берем из массива количества товара в корзине
                    // в конце строки стоимость товара
                    saveCartToFile.write((productsList[i][0] + ": " +
                            itemsInCart[i] + " шт, " +
                            Integer.parseInt(productsList[i][1]) * itemsInCart[i]) + " руб");
                    saveCartToFile.append("\n");
                    saveCartToFile.flush();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String[][] loadFromFile(File textFile) {
        String dataFromFile = "";
        try (BufferedReader loadCartFromFile = new BufferedReader(new FileReader(textFile))){
            // заводим переменную для чтения из файла, т.к. при упоминании метода в скобках
            // цикла / ифа он выполняется,
            // итого без переменной у нас каждая вторая итерация цикла останется без данных
            String s;
            while (true) {
                if ((s = loadCartFromFile.readLine()) == null) break;
                dataFromFile += s + "\n";
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        // я не вспомню как создавался этот пиздец...
        String[] splitLines = dataFromFile.split("\\n");
        String[][] productsListFromFile = new String[splitLines.length][2];
        for (int i = 0; i < splitLines.length; i++) {
            String[] split = splitLines[i].split(": |, | ");
            productsListFromFile[i][0] = split[0];
            productsListFromFile[i][1] = split[1];
        }
        System.out.println(Arrays.deepToString(productsListFromFile));
        return productsListFromFile;
    }
}
