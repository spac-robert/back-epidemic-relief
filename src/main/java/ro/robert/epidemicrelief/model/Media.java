package ro.robert.epidemicrelief.model;

import jakarta.persistence.*;

import java.sql.Blob;
import java.util.Arrays;

@Entity
@Table(name = "media")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "type")
    private String type;
    @Lob
    @Column(name = "data",columnDefinition = "LONGBLOB", length = 10000)
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Media(String name, byte[] data, Product product) {
        this.name = name;
        this.data = data;
        this.product = product;
    }

    public Media(String name, byte[] data, String type) {
        this.name = name;
        this.data = data;
        this.type = type;
    }

    public Media() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", data=" + Arrays.toString(data) +
                ", productId=" + product.getId() +
                '}';
    }
}