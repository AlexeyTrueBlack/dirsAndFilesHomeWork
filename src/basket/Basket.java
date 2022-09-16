package basket;

import java.io.File;

public class Basket {

    protected String[][] productsList;
    protected int[] itemsInCart = new int[productsList.length];

    public Basket(String[][] productsList) {
        this.productsList = productsList;
    }

    public void addToCart(int productNum, int amount) {
        itemsInCart[productNum] = amount;
    }

    public void printCart() {

    }

    public File saveTxt(File textFile) {

    }

    public static Basket loadFromFile(File textFile) {

    }
}
