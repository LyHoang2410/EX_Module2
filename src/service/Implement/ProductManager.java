package service.Implement;

import model.Product;
import service.Manager;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProductManager implements Manager<Product> {
    private final Scanner scanner;
    private ArrayList<Product> products;
    private final String PATH_FILE = "C:\\Users\\Asus\\Desktop\\M2_Final_Exam\\Module2\\src\\data\\ProductCsv";

    public ProductManager(Scanner scanner) {
        products = new ArrayList<>();
        this.scanner = scanner;
        checkDefaultIndex();
    }

    private void checkDefaultIndex() {
        if (products.isEmpty()) {
            Product.INDEX = 0;
        } else {
            Product.INDEX = products.get(products.size() - 1).getCode();
        }
    }


    @Override
    public Product create() {
        System.out.println("Enter name product:");
        String name = scanner.nextLine();
        Double price = null;
        try {
            System.out.println("Enter price product:");
            price = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Error, please re-enter");
        }
        int quantity = 0;
        try {
            System.out.println("Enter quantity product:");
            quantity = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Error, please re-enter");
        }
        System.out.println("Enter describe product:");
        String describe = scanner.nextLine();
        Product product = new Product(name, price, quantity, describe);
        products.add(product);
        return product;
    }

    @Override
    public Product update() {
        Product product = getByCode();
        if (product != null) {
            System.out.println("Enter new name product: ");
            String name = scanner.nextLine();
            if (!name.equals("")) {
                product.setName(name);
            }
            System.out.println("Enter new price product: ");
            String price = scanner.nextLine();
            if (!price.equals("")) {
                product.setPrice(Double.parseDouble(price));
            }
            System.out.println("Enter new quantity product: ");
            String quantity = scanner.nextLine();
            if (!quantity.equals("")) {
                product.setQuantity(Integer.parseInt(quantity));
            }
            System.out.println("Enter new describe product: ");
            String describe = scanner.nextLine();
            if (!describe.equals("")) {
                product.setDescribe(describe);
            }
        }
        return product;
    }

    @Override
    public Product delete() {
        Product product = getByCode();
        if (product != null) {
            products.remove(product);
        }
        return product;
    }

    @Override
    public Product getByCode() {
        displayAll();
        System.out.println("Enter code you want to choice");
        int code = Integer.parseInt(scanner.nextLine());
        for (Product product : products) {
            if (product.getCode() == code) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void displayAll() {
        System.out.printf("%-5s%-15s%-25s%-20s%s",
                "CODE", "PRODUCT NAME", "PRICE", "QUANTITY", "DESCRIBE\n");
        for (Product product : products) {
            product.display();
        }
    }

    public void displayOne(Product product) {
        if (product != null) {
            product.display();
        } else {
            System.out.println("errors, non-existent counterparts");
        }
    }

    public Product ascendingSort() {
        Collections.sort(products, (o1, o2) -> {
            if (o1.getPrice() > o2.getPrice()) return 1;
            else if (o1.getPrice() < o2.getPrice()) return -1;
            else return 0;
        });
        System.out.println("Product sorted by price (Low to High):");
        displayAll();
        return null;
    }

    public Product descendingSort() {
        Collections.sort(products, (o1, o2) -> {
            if (o1.getPrice() < o2.getPrice()) return 1;
            else if (o1.getPrice() > o2.getPrice()) return -1;
            else return 0;
        });
        System.out.println("Product sorted by price (High to Low):");
        displayAll();
        return null;
    }

    public void searchByMaxPrice() {
        double maxPrice = products.get(0).getPrice();
        for (Product product : products) {
            if (product.getPrice() > maxPrice) {
                maxPrice = product.getPrice();
            }
        }
        for (Product product : products) {
            if (product.getPrice() == maxPrice) {
                System.out.println(product);
            }
        }
    }

    public void readFileCsv() {
        File file = new File("C:\\Users\\Asus\\Desktop\\M2_Final_Exam\\Module2\\src\\data\\ProductCsv");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(",");
                products.add(new Product(strings[1],
                        Double.parseDouble(strings[2]),
                        Integer.parseInt(strings[3]), strings[4]));
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeFileCsv() {
        File file = new File("C:\\Users\\Asus\\Desktop\\M2_Final_Exam\\Module2\\src\\data\\ProductCsv");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (Product product : products) {
                bufferedWriter.write(product.writeFileCsv() + "\n");
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
