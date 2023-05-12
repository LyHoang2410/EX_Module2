package model;

import java.io.Serializable;

public class Product implements Serializable {
    public static final long serialVersionUID = 1994L;
    public static int INDEX;
    private int code;
    private String name;
    private Double price;
    private int quantity;
    private String describe;

    public Product() {
    }

    public Product(String name, Double price, int quantity, String describe) {
        this.code = ++INDEX;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.describe = describe;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void display() {
        System.out.printf("%-5s%-15s%-25s%-20s%s",
                this.code,
                this.name,
                this.price,
                this.quantity,
                this.describe + "\n");
    }

    public String writeFileCsv() {
        return  code + "," + name + "," + price + "," + quantity + "," + describe;
    }
    @Override
    public String toString() {
        return name + '\'' +
                "," + price +
                "," + quantity +
                "," + describe + '\'';
    }
}
