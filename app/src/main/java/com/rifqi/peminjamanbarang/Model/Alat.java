package com.rifqi.peminjamanbarang.Model;

/**
 * Created by USER on 28/12/2017.
 */

public class Alat  {
    private String Name,Image,Description,MenuId;

    public Alat(String name, String image, String description, String menuId) {
        Name = name;
        Image = image;
        Description = description;
        MenuId = menuId;
    }

    public Alat() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
