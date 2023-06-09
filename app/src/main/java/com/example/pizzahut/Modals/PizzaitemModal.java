package com.example.pizzahut.Modals;

public class PizzaitemModal {
    private  String PizzaName,PizzaQunty,PizzaDiscount,DiscountPrice,DateofBuy,GrandTotal,CustomerEmail,OrderId,PizzaImage;

    public PizzaitemModal() {
    }

    public PizzaitemModal(String pizzaName, String pizzaQunty, String pizzaDiscount, String discountPrice, String dateofBuy, String grandTotal, String customerEmail, String orderId, String pizzaImage) {
        PizzaName = pizzaName;
        PizzaQunty = pizzaQunty;
        PizzaDiscount = pizzaDiscount;
        DiscountPrice = discountPrice;
        DateofBuy = dateofBuy;
        GrandTotal = grandTotal;
        CustomerEmail = customerEmail;
        OrderId = orderId;
        PizzaImage = pizzaImage;
    }

    public String getPizzaName() {
        return PizzaName;
    }

    public void setPizzaName(String pizzaName) {
        PizzaName = pizzaName;
    }

    public String getPizzaQunty() {
        return PizzaQunty;
    }

    public void setPizzaQunty(String pizzaQunty) {
        PizzaQunty = pizzaQunty;
    }

    public String getPizzaDiscount() {
        return PizzaDiscount;
    }

    public void setPizzaDiscount(String pizzaDiscount) {
        PizzaDiscount = pizzaDiscount;
    }

    public String getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        DiscountPrice = discountPrice;
    }

    public String getDateofBuy() {
        return DateofBuy;
    }

    public void setDateofBuy(String dateofBuy) {
        DateofBuy = dateofBuy;
    }

    public String getGrandTotal() {
        return GrandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        GrandTotal = grandTotal;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getPizzaImage() {
        return PizzaImage;
    }

    public void setPizzaImage(String pizzaImage) {
        PizzaImage = pizzaImage;
    }
}
