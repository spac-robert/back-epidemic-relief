package ro.robert.epidemicrelief.visitor.model;

public class ProductNecessity {
    private String uuid;
    private Long stock;

    public ProductNecessity(String uuid, Long stock) {
        this.uuid = uuid;
        this.stock = stock;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}