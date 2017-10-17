package kiosk.ddc.a3nomdev.myapplication.model;

/**
 * Created by 3nomdev on 10/17/17.
 */

public class Accompany {
    private Boolean status;
    private String name;

    public Accompany(Boolean status,String name)
    {
        this.status=status;
        this.name=name;
    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
