package ro.robert.epidemicrelief.visitor.model;

public class ProductNecessity {
    private Integer uuid;
    private Long stock;

    public ProductNecessity(Integer uuid, Long stock) {
        this.uuid = uuid;
        this.stock = stock;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}