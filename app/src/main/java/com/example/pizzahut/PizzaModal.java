package com.example.pizzahut;

public class PizzaModal {
    private String pizzaname,pizzaprice,image,pizzaid;

    public PizzaModal() {
    }

    public PizzaModal(String pizzaname, String pizzaprice, String image, String pizzaid) {
        this.pizzaname = pizzaname;
        this.pizzaprice = pizzaprice;
        this.image = image;
        this.pizzaid = pizzaid;
    }

    public String getPizzaname() {
        return pizzaname;
    }

    public void setPizzaname(String pizzaname) {
        this.pizzaname = pizzaname;
    }

    public String getPizzaprice() {
        return pizzaprice;
    }

    public void setPizzaprice(String pizzaprice) {
        this.pizzaprice = pizzaprice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPizzaid() {
        return pizzaid;
    }

    public void setPizzaid(String pizzaid) {
        this.pizzaid = pizzaid;
    }

    @Override
    public String toString() {
        return "PizzaModal{" +
                "pizzaname='" + pizzaname + '\'' +
                ", pizzaprice='" + pizzaprice + '\'' +
                ", image='" + image + '\'' +
                ", pizzaid='" + pizzaid + '\'' +
                '}';
    }
}
