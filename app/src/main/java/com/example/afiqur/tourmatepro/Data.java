package com.example.afiqur.tourmatepro;



public class Data {



    public String getSignupUrl() {
        return signupUrl;
    }

    public String getLoginUrl() {
        return LoginUrl;
    }

    public static String ip = "http://192.168.0.9:70/turemate/";
    public static String ip2 = "http://192.168..0.9:70/";
    public static String signupUrl = ip+"user/doSignup";
    public static String LoginUrl = ip+"user/login";
    public static String TravelList = ip+"user/travel_list";
    public static String AddTravelEvent = ip+"user/add_travel_event";
    public static String addExpenseCost = ip+"user/add_expense";
    public static String addImageData = ip+"user/add_image_data";
    public static String MomentListUrl = ip+"user/load_all_moment";
    public static String ExpenseListUrl = ip+"user/load_all_expense";
    public static String ImageInsert = ip+"user/uploadtoserverImage";


}
