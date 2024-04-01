package edu.miu.cs.cs489appsd.lab1a.productmgmtapp;

import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.repo.ProductRepo;



public class ProductMgmtApp {


    public static void main(String[] args) {

        ProductRepo productRepo = new ProductRepo();

        System.out.println("Printed in XML format");
        System.out.println(productRepo.toXML());
        System.out.println("-------------------");
        System.out.println("Printed in JSON format");
        System.out.println(productRepo.toJson());
        System.out.println("-------------------");
        System.out.println("Printed in CSV format");
        System.out.println(productRepo.toCSV());



    }

}
