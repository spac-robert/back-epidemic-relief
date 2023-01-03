package ro.robert.epidemicrelief.model;

import jakarta.persistence.*;

import java.sql.Blob;

@Entity
@Table(name = "media")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private Blob data;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Media(String name, Blob data, Product product) {
        this.name = name;
        this.data = data;
        this.product = product;
    }

    public Media(String name, Blob data) {
        this.name = name;
        this.data = data;
    }

    public Media() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Blob getData() {
        return data;
    }

    public void setData(Blob data) {
        this.data = data;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }
}