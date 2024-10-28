package models;

public abstract class Tour {
    protected String name;
    protected double price;
    protected String city;

    public Tour(String name, double price, String city) {
        this.name = name;
        this.price = price;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCity() {
        return city;
    }

    public abstract void displayDetails();
}
