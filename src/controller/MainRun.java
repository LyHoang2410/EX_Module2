package controller;

import model.Product;
import service.Implement.ProductManager;

import java.util.Scanner;

public class MainRun {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManager productManager = new ProductManager(scanner);

        int choice = -1;
        do {
            System.out.println("MENU");
            System.out.println("1. Check List");
            System.out.println("2. Add new product");
            System.out.println("3. Update product");
            System.out.println("4. Delete Product");
            System.out.println("5. Sort product");
            System.out.println("6. Find product highest price");
            System.out.println("7. Read form file");
            System.out.println("8. Write from file");
            System.out.println("9.Exit");
            System.out.println("Enter your choice");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    productManager.displayAll();
                    break;
                case 2:
                    Product productCreate = productManager.create();
                    System.out.println("Product created is:");
                    productManager.displayOne(productCreate);
                    break;
                case 3:
                    Product productUpdate = productManager.update();
                    System.out.println("Product updated is:");
                    productManager.displayOne(productUpdate);
                    break;
                case 4:
                    Product productDelete = productManager.delete();
                    System.out.println("Product delete is: ");
                    productManager.displayOne(productDelete);
                    break;
                case 5:
                    int sortChoice = -1;
                    do {
                        System.out.println("1. Sort products by Price(Low to High");
                        System.out.println("2. Sort products by Price(High to Low");
                        System.out.println("0. Back to Menu");
                        try {
                            sortChoice = Integer.parseInt(scanner.nextLine());
                        } catch (Exception e) {
                            System.out.println("Error, please try again: ");
                            continue;
                        }
                        switch (sortChoice) {
                            case 1:
                                productManager.ascendingSort();
                                break;
                            case 2:
                                productManager.descendingSort();
                                break;
                            case 0:
                                break;
                        }
                    } while (sortChoice != 0);
                    break;
                case 6:
                    productManager.searchByMaxPrice();
                    break;
                case 7:
                    productManager.readFileCsv();
                    break;
                case 8:
                    productManager.writeFileCsv();
                    break;
                case 9:
                    System.exit(9);
                    break;
            }
        } while (true);
    }

}
