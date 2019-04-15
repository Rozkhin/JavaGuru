package com.company;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static java.math.BigDecimal.ROUND_CEILING;

public class Main {

    public static void main(String[] args) {
        List<Product> ListOfProducts = new ArrayList<>();
        while (true) {
            Scanner scanner = new Scanner(System.in);

            int i = MainMenu(scanner);
            System.out.println("************************************************");


        switch (i) {
            case 1:
                ListOfProducts.add(Case1addProduct());
                System.out.println("Product added ");
                break;
            case 2:
                Case2showproduct(ListOfProducts);
                break;
            case 3:
                Case3showAll(ListOfProducts);
                break;
            case 4:
                Case4RemoveElement(ListOfProducts);
                break;
            case 5:
                System.exit(0);
            default:
                System.out.println("Not correct value");
        }
        }
    }

    public static abstract class Record {
        private int counter = 0;
        private int id;

        public Record() {
            counter++;
            id = counter;
        }

        public int getId() {
            return id;
        }
    }

    public static class Product extends Record {

        private String name;
        private BigDecimal price;
        private BigDecimal ActualPrice;

        public BigDecimal getActualPrice() {
            BigDecimal prcent = this.price.divide(new BigDecimal(100));
            BigDecimal CurDisc=prcent.multiply(this.discount);
            BigDecimal ActualPrice = this.price.subtract(CurDisc);
            BigDecimal RoundeActualPrice =ActualPrice.setScale(2,ROUND_CEILING);

            return RoundeActualPrice;
        }

        enum Category {
            Fruits,
            Vegetables,
            Others;
        }

        Category cat;

       Category getCat() {
            return cat;
        }

        public void setCat(int h) {
            for(Category c : Category.values()){
                if(h == c.ordinal()){
                    this.cat = c;
                }
            }
        }

        private BigDecimal discount;
        private String description;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getDiscount() {
            return discount;
        }

        public void setDiscount(BigDecimal discount) {
            this.discount = discount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }


        public String toString() {
            return "Person{" +
                    "id=" + getId() +
                    ", name='" + name + '\'' +
                    ", Price='" + price + '\'' +
                    ", Discount='" + discount + '\'' +
                    ", Actualprice='" + discount + '\'' +
                    '}';
        }

    }

    public static int MainMenu(Scanner scanner) {
        int i=0;
        try {
            System.out.println("*************************************************************************");
                System.out.println("1:Add product");
                System.out.println("2:Recive product by id");
                System.out.println("3:Recive list of all products");
                System.out.println("4:Delete product by ID");
                System.out.println("5 exit");
                i = scanner.nextInt();
           }catch (InputMismatchException e){
            System.err.println("Enter correct value");
        }
        return i;
    }

    public static Product Case1addProduct(){
        Product AddedProduct = new Product();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Add product name :");
        String name = scanner.next();
        AddedProduct.setName(name);

        System.out.print("Add product price :");
        scanner.nextLine();
        while (scanner.hasNext()) {
            if (scanner.hasNextBigDecimal()) {
                AddedProduct.setPrice(scanner.nextBigDecimal());
                break;
            } else {
                System.out.println("Not correct value please repeat :");
                scanner.nextLine();
            }
        }
        System.out.print("Add discount % if no add 0:");
        scanner.nextLine();
        while (scanner.hasNext()){
            if(scanner.hasNextBigDecimal()){
                BigDecimal discount = scanner.nextBigDecimal();
                BigDecimal maxdiscount = BigDecimal.valueOf(100);
                BigDecimal mindiscount = BigDecimal.valueOf(0);
                if (discount.compareTo(maxdiscount) == 1) {
                    System.out.println("Discount can't be more then 100%");
                } else if (discount.compareTo(mindiscount) == -1) {
                    System.out.println("Discount can't be less then 0%");
                } else {
                    AddedProduct.setDiscount(discount);
                    break;
                }
            }else {
                System.out.println("Please add correct value ");
                scanner.next();
            }
        }
        System.out.print("Add product description");
        AddedProduct.setDescription(scanner.next());

        System.out.println("Add select category :1. Fruits; 2. Vegetables; 3.Others;" );
        int i=scanner.nextInt();
        AddedProduct.setCat(i);


        return AddedProduct;
    }

    public static void Case2showproduct(List<Product> ListOfProducts){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Product ID");
        System.out.println("Total size "+ListOfProducts.size());
        int id = scanner.nextInt();
        if (id < ListOfProducts.size()) {
            System.out.println("Product cateoty: " +ListOfProducts.get(id).getCat());
            System.out.println("Product name : " + ListOfProducts.get(id).getName());
            System.out.println("Product price: " + ListOfProducts.get(id).getPrice());
            System.out.println("Product actual price :"+ListOfProducts.get(id).getActualPrice());
            System.out.println("Product discount: " + ListOfProducts.get(id).getDiscount());
            System.out.println("Product description : " + ListOfProducts.get(id).getDescription());
            System.out.print("press any key");
            try {
                System.in.read();
            }catch (Exception e){
                       e.printStackTrace();
            }
        }
        else {System.err.println("no such ID");}
    }

    public static void Case3showAll(List<Product> ListOfProducts){
        for(int j=0;j<ListOfProducts.size();j++){
            System.out.println("Product ID "+ListOfProducts.get(j).getId());
            System.out.println("Product name "+ ListOfProducts.get(j).getName());
            System.out.println("Product price "+ListOfProducts.get(j).getPrice());
            System.out.println("Product discount "+ListOfProducts.get(j).getDiscount());
            System.out.println("Product actual price "+ListOfProducts.get(j).getActualPrice());
            System.out.println("********************************************************");
            System.out.println("");
            try {
                System.in.read();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void Case4RemoveElement(List<Product> ListOfProducts){
        int i;
        System.out.println("Enter ID  to delete");
        Scanner scanner = new Scanner(System.in);
        try {
            i = scanner.nextInt();
            ListOfProducts.remove(i);
            System.out.println("Its removed from  list");
            }catch (InputMismatchException e){
                System.err.println("Enter correct value");
        }

    }

}
