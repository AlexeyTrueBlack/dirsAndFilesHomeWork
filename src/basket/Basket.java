package basket;

import java.io.*;

public class Basket {

    protected String[][] productsList; // двумерный массив-список товаров с ценами, доступных к покупке
    protected int[] itemsInCart; // массив количества каждого товара в корзине
    protected int bill = 0;

    public Basket() {
    }

    public Basket(String[][] productsList) {
        this.productsList = productsList;
        this.itemsInCart = new int[productsList.length];
    }

    public void addToCart(int productNum, int amount) {
        itemsInCart[productNum] += amount;
        bill += Integer.parseInt(productsList[productNum][1]) * amount;
    }

    public void printCart() {
        System.out.println("Your cart:");
        for (int i = 0; i < itemsInCart.length; i++) {
            if (itemsInCart[i] != 0) {
                System.out.println(productsList[i][0] + ", " + productsList[i][1] + " rub/pcs: "
                        + itemsInCart[i] + " pcs, " + (Integer.parseInt(productsList[i][1]) * itemsInCart[i]) + " rub");
            }
        }
        System.out.println("Total cost: " + bill);
    }

    public void saveTxt(File textFile) {
        try (BufferedWriter saveCartToFile = new BufferedWriter(new FileWriter(textFile))) {
            for (int i = 0; i < itemsInCart.length; i++) {
                if (itemsInCart[i] != 0) {
                    // заносим в файл наименование товара из списка
                    // количество товара берем из массива количества товара в корзине
                    // в конце строки стоимость товара
                    saveCartToFile.write((productsList[i][0] + ": " +
                            itemsInCart[i] + " pcs, " +
                            Integer.parseInt(productsList[i][1]) * itemsInCart[i]) + " rub");
                    saveCartToFile.append("\n");
                    saveCartToFile.flush();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Basket loadFromFile(File textFile) {
        StringBuilder dataFromFile = new StringBuilder();
        try (BufferedReader loadCartFromFile = new BufferedReader(new FileReader(textFile))) {
            // заводим переменную для чтения из файла, т.к. при упоминании метода в скобках
            // цикла / ифа он выполняется,
            // итого без переменной у нас каждая вторая итерация цикла останется без данных
            String s;
            while ((s = loadCartFromFile.readLine()) != null) {
                dataFromFile.append(s).append("\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        // Сначала разбиваем файл на массив построчно
        String[] splitLines = dataFromFile.toString().split("\\n");
        // создаем двумерный массив для записи наименований и цен из файла
        String[][] productsListFromFile = new String[splitLines.length][2];
        for (int i = 0; i < splitLines.length; i++) {
            String[] split = splitLines[i].split(": |, | ");
            productsListFromFile[i][0] = split[0];
            productsListFromFile[i][1] = split[1];
        }
        return new Basket(productsListFromFile);
    }

    public String[][] getProductsList() {
        return productsList;
    }
}