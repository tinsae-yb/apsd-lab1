package edu.miu.cs.cs489appsd.lab1a.productmgmtapp.repo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model.Product;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.util.LocalDateAdapter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
public class ProductRepo {

     List<Product> productList = new ArrayList<>();
    public ProductRepo(){
        loadProductsFromFile();
    }



    @XmlElement(name = "product")
    public List<Product> getProducts() {
        return productList;
    }




     void loadProductsFromFile(){


        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/main/java/edu/miu/cs/cs489appsd/lab1a/productmgmtapp/products.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                long productId = Long.parseLong(parts[0]);
                String name = parts[1];
                LocalDate dateSupplied = LocalDate.parse(parts[2]);
                int quantityInStock = Integer.parseInt(parts[3]);
                BigDecimal unitPrice = new BigDecimal(parts[4]);

                Product product = new Product();
                product.setProductId(productId);
                product.setName(name);
                product.setDateSupplied(dateSupplied);
                product.setQuantityInStock(quantityInStock);
                product.setUnitPrice(unitPrice);

                productList.add(product);
            }
        } catch (IOException e) {
            System.out.println("exception : " + e.getMessage());
        }


    }

    public String toXML() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ProductRepo.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(this, stringWriter);
            return stringWriter.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toJson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        return gson.toJson(productList);
    }


    public String toCSV() {
        StringBuilder csv = new StringBuilder();


        csv.append("productId,name,dateSupplied,quantityInStock,unitPrice\n");


        for (Product product : productList) {
            csv.append(product.getProductId()).append(",")
                    .append(product.getName()).append(",")
                    .append(product.getDateSupplied()).append(",")
                    .append(product.getQuantityInStock()).append(",")
                    .append(product.getUnitPrice()).append("\n");
        }

        return csv.toString();
    }

}
