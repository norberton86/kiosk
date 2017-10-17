package kiosk.ddc.a3nomdev.myapplication.model;

/**
 * Created by 3nomdev on 10/17/17.
 */

public class Client {
    private Integer id;
    private String name;
    private String address;

    public Client(Integer id,String name,String address)
    {
        this.id=id;
        this.name=name;
        this.address=address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}