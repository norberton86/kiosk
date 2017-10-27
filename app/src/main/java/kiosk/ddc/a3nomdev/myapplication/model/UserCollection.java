package kiosk.ddc.a3nomdev.myapplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 3nomdev on 10/27/17.
 */

public class UserCollection implements Serializable {
    User user;
    private List<User> users;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<String> StringForFinal()
    {
        List<String> result=new ArrayList<String>();

        for(User u: users)
        {
            result.add(u.getFirstName()+" , "+u.getLastName()+ "  Table - "+u.getTableNumber());
        }
        return result;
    }

    public List<User> Chossed()
    {
        List<User> results=new ArrayList<User>();
        for(User u: users)
        {
            if(u.getCheckIn())
            results.add(u);
        }
        return  results;
    }
}
