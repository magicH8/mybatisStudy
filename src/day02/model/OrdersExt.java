package day02.model;

import java.util.Date;

public class OrdersExt extends Orders {
    private String username;
    private String address;

    @Override
    public String toString() {
        return "OrdersExt{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}' + super.toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
