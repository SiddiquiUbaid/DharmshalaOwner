package com.example.dharmshalaowner.Domain;

public class ImageDataClass {
    String imageCover, imageHotel1, imageHotel2, imageRoom1, imageRoom2;

    public ImageDataClass() {
    }


    public ImageDataClass(String imageCover, String imageHotel1, String imageHotel2, String imageRoom1, String imageRoom2) {
        this.imageCover = imageCover;
        this.imageHotel1 = imageHotel1;
        this.imageHotel2 = imageHotel2;
        this.imageRoom1 = imageRoom1;
        this.imageRoom2 = imageRoom2;
    }



    public String getImageCover() {
        return imageCover;
    }

    public void setImageCover(String imageCover) {
        this.imageCover = imageCover;
    }

    public String getImageHotel1() {
        return imageHotel1;
    }

    public void setImageHotel1(String imageHotel1) {
        this.imageHotel1 = imageHotel1;
    }

    public String getImageHotel2() {
        return imageHotel2;
    }

    public void setImageHotel2(String imageHote2) {
        this.imageHotel2 = imageHote2;
    }

    public String getImageRoom1() {
        return imageRoom1;
    }

    public void setImageRoom1(String imageRoom1) {
        this.imageRoom1 = imageRoom1;
    }

    public String getImageRoom2() {
        return imageRoom2;
    }

    public void setImageRoom2(String imageRoom2) {
        this.imageRoom2 = imageRoom2;
    }




}
