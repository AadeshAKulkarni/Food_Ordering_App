package com.example.dell.models;

/**
 * Created by Dell on 10/16/2016.
 */

public class NavItem {
    private String title;
    private String subTitle;
    private int resIcon;

    public NavItem(int resIcon, String title) {
        this.resIcon = resIcon;
       // this.subTitle = subTitle;
        this.title = title;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    //public String getSubTitle() {       return subTitle;    }

   // public void setSubTitle(String subTitle) { this.subTitle = subTitle;    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
