import basket.Basket;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] products = {{"Молоко", "100"}, {"Крупа", "50"}, {"Чай", "80"}, {"Сахар", "60"}};
        Basket basket = new Basket(products);
        File basketTxt = new File("basket.txt");
        if (basketTxt.exists()) {
            String[][] strings = Basket.loadFromFile(basketTxt);
            for (int i = 0; i < products.length; i++) {
                for (int j = 0; j < strings.length; j++) {
                    if (strings[j][0].equals(products[i][0])) {
                        basket.addToCart(i, Integer.parseInt(strings[j][1]));
                    }
                }
            }
        }
        else System.out.println("Ранее созданная корзина отсутствует, будет формироваться новая");
        System.out.println();
        int prices[] = {100, 50, 80, 60};
        int productsCount[] = new int[products.length]; // накопительный счетчик продуктов одного типа в корзине
        while (true) {
            basket.printBasket();
            System.out.println("Выберите номер продукта из списка и количество через пробел. " +
                    "Для завершения программы и вывода итогов введите end:");
            String choice = scanner.nextLine();

            if (choice.equals("end")) {
                break;
            }

            String parts[] = choice.split(" "); // создаем массив из номера товара и количества
            if (parts.length != 2) {
                System.out.println("Некорректный ввод! Нужно ввести два числа!");
                continue;
            }
            int productNumber;
            int productCount;
            try {
                productNumber = Integer.parseInt(parts[0]) - 1; //порядковый номер продукта в массиве,
                productCount = Integer.parseInt(parts[1]); // количество единиц данного продукта
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели что-то совсем непонятное");
                continue;
            }
            if (productNumber < 0 || productNumber > products.length) {
                System.out.println("Выберите порядковый номер в соответствии с представленным списком");
                continue;
            } else if (productCount < 0) {
                System.out.println("Мы не можем положить в корзину " + productCount + " " + products[productNumber]);
                continue;
            } else if (productCount == 0) {
                System.out.println("Вы ничего не положили в корзину");
                //continue;
                productsCount[productNumber] = 0;
            }
            basket.addToCart(productNumber, productCount);
            basket.saveTxt(basketTxt);
        }
        basket.printCart();
    }
}