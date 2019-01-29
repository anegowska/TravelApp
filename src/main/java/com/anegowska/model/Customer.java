package com.anegowska.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="CUSTOMERS")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "surname")
    @NotNull
    private String surname;

    @Column(name = "pesel", length = 11)
    @NotNull
    private Long pesel;

    @Column(name = "phone")
    @NotNull
    private Long phone;

    @ManyToMany
    @JoinTable(name = "CUSTOMERS_TO_TRAVELS",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "travel_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"customer_id", "travel_id"}))
    private List<Travel> travels;


    public Customer() {
    }

    public Customer(String name, String surname, Long pesel, Long phone) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.phone = phone;
    }

    public Customer(String name, String surname, Long pesel, Long phone, List<Travel> travels) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.phone = phone;
        this.travels = travels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getPesel() {
        return pesel;
    }

    public void setPesel(Long pesel) {
        this.pesel = pesel;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public List<Travel> getTravels() {
        return travels;
    }

    public void setTravels(List<Travel> travels) {
        this.travels = travels;
    }
}
