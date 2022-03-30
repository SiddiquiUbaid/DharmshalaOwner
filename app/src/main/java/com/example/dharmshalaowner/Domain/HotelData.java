package com.example.dharmshalaowner.Domain;

import java.util.ArrayList;
import java.util.HashMap;

public class HotelData {

    String PlaceName;
    String PlaceRent;
    String heading;
    String description;

    Integer drawableImageId;


    ArrayList<String> PlaceQualityList, PlaceSecurityList;
    ArrayList<String> HotelImages, HotelRoomImages;
    ArrayList<String> numberOfGuestsList;

    ArrayList<DataClass> PlaceDescriptionList, PlaceOffersList;


    public HotelData() {
    }



    public ArrayList<DataClass> getPlaceDescriptionList() {
        return PlaceDescriptionList;
    }

    public void setPlaceDescriptionList(ArrayList<DataClass> placeDescriptionList) {
        PlaceDescriptionList = placeDescriptionList;
    }



    public ArrayList<DataClass> getPlaceOffersList() {
        return PlaceOffersList;
    }

    public void setPlaceOffersList(ArrayList<DataClass> placeOffersList) {
        PlaceOffersList = placeOffersList;
    }




    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDrawableImageId() {
        return drawableImageId;
    }

    public void setDrawableImageId(Integer drawableImageId) {
        this.drawableImageId = drawableImageId;
    }


    public ArrayList<String> getNumberOfGuestsList() {
        return numberOfGuestsList;
    }

    public void setNumberOfGuestsList(ArrayList<String> numberOfGuestsList) {
        this.numberOfGuestsList = numberOfGuestsList;
    }

    public ArrayList<String> getHotelImages() {
        return HotelImages;
    }

    public void setHotelImages(ArrayList<String> hotelImages) {
        HotelImages = hotelImages;
    }

    public ArrayList<String> getHotelRoomImages() {
        return HotelRoomImages;
    }

    public void setHotelRoomImages(ArrayList<String> hotelRoomImages) {
        HotelRoomImages = hotelRoomImages;
    }

    public ArrayList<String> getPlaceQualityList() {
        return PlaceQualityList;
    }

    public void setPlaceQualityList(ArrayList<String> placeQualityList) {
        PlaceQualityList = placeQualityList;
    }

    public ArrayList<String> getPlaceSecurityList() {
        return PlaceSecurityList;
    }

    public void setPlaceSecurityList(ArrayList<String> placeSecurityList) {
        PlaceSecurityList = placeSecurityList;
    }


    public String getPlaceName() {
        return PlaceName;
    }

    public void setPlaceName(String placeName) {
        PlaceName = placeName;
    }

    public String getPlaceRent() {
        return PlaceRent;
    }

    public void setPlaceRent(String placeRent) {
        PlaceRent = placeRent;
    }
}