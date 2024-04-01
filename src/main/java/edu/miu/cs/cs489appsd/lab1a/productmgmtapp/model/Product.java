package edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;



@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

    @XmlElement
    private long productId;
    @XmlElement
    private String name;
    @XmlElement
    private LocalDate dateSupplied;
    @XmlElement
    private int quantityInStock;
    @XmlElement
    private BigDecimal unitPrice;

    // Setters
    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateSupplied(LocalDate dateSupplied) {
        this.dateSupplied = dateSupplied;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // Getters
    public long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateSupplied() {
        return dateSupplied;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }


    public String toJson() {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        return gson.toJson(this);
    }

    public String toXml() {
        try {
            javax.xml.bind.JAXBContext jaxbContext = javax.xml.bind.JAXBContext.newInstance(Product.class);
            javax.xml.bind.Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
            java.io.StringWriter sw = new java.io.StringWriter();
            jaxbMarshaller.marshal(this, sw);
            return sw.toString();
        } catch (javax.xml.bind.JAXBException e) {
            return null;
        }
    }

}
