package com.example.mydrive.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String password;

    @Override
    public boolean equals( Object obj) {
        User user= (User) obj;
        return user.getName().equals(name)? true:false;
    }
    @Generated(hash = 1691991404)
    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    @Override
    public int hashCode() {
        return name.hashCode();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

