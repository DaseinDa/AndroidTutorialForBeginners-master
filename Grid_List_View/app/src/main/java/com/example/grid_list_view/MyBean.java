package com.example.grid_list_view;
/**
 * 实体类
 * @author NanFeiLong
 *
 */

public class MyBean {

    private String iconName;
    private int  imageId;

    public MyBean(String iconName, int imageId) {
        super();
        this.iconName = iconName;
        this.imageId = imageId;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }


    @Override
    public String toString() {
        return "MyBean [iconName=" + iconName + ", imageId=" + imageId
                + ", getIconName()=" + getIconName() + ", getImageId()="
                + getImageId() + ", getClass()=" + getClass() + ", hashCode()="
                + hashCode() + ", toString()=" + super.toString() + "]";
    }












}
