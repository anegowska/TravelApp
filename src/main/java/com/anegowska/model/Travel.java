package com.anegowska.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TRAVELS")
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number_of_days")
    @NotNull
    private Integer numberOfDays;

    @Column(name = "price")
    private Integer price;

    @Column(name = "hotel_name")
    private String hotelName;


    public Travel() {
    }

    public Travel(Integer numberOfDays, Integer price, String hotelName) {
        this.numberOfDays = numberOfDays;
        this.price = price;
        this.hotelName = hotelName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
