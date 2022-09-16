import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        String products[][] = {{"Молоко", "100"}, {"Крупа", "50"}, {"Чай", "80"}, {"Сахар", "60"}};
        int prices[] = {100, 50, 80, 60};
        int productsCount[] = new int[products.length]; // накопительный счетчик продуктов одного типа в корзине

        while (true) {
            System.out.println("Выберите номер продукта из списка ниже и количество через пробел. Для завершения программы и вывода итогов введите end:");
            for (int i = 0; i < products.length; i++) {
                System.out.println((i + 1) + ". " + products[i] + " - " + prices[i] + " руб.");
            }
            String choice = scanner.nextLine();
            ;
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
                productCount = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели что-то совсем непонятное");
                continue;
            }
            if (parts.length != 2) {
                System.out.println("Введите корректные значения");
                continue;
            }
            if (productNumber < 0 || productNumber >= products.length) {
                System.out.println("Выберите порядковый номер в соответствии с представленным списком");
                continue;
            } else if (productCount < 0) {
                System.out.println("Мы не можем положить в корзину " + productCount + " " + products[productNumber]);
                continue;
            } else if (productCount == 0) {
                //System.out.println("Мы конечно можем представить что положили в корзину невидимый товар, но лучше введите корректные значения");
                //continue;
                productsCount[productNumber] = 0;
            }
            productsCount[productNumber] += productCount;
            System.out.println("Вы положили в корзину " + products[productNumber] + ", " + productCount + ", шт");
        }
        int bill = 0;
        for (int i1 = 0; i1 < products.length; i1++) {
            if (productsCount[i1] != 0) {
                bill += productsCount[i1] * prices[i1];
                System.out.println(products[i1] + " - " + productsCount[i1] + " шт., " + prices[i1] + " руб/шт., " + productsCount[i1] * prices[i1] + " руб. в сумме");
            }
        }
        System.out.println("Общая сумма покупок: " + bill + " руб.");
    }
}