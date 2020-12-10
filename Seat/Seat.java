package Seat;


import java.math.BigDecimal;

public class Seat {
    private BigDecimal price;
    private boolean reserved;

    public Seat(BigDecimal price){
        this.price = price;
        reserved = false;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
