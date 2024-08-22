package constant;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class IConstant {
    public static final String pathProduct = "./images/Product_img";
    public static final String PATH_BRAND = "./images/Brand_img";
    public static final String[] STATUS = new String[]{"wait", "proccess", "done", "cancel"};
    public static final String SIGNATURE_IMG = "./images/App_img/signature.png";
    //generate password 
    public static final String REGEX_PHONE_NUMBER = "^\\d{10}$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
    public static final String REGEX_EMAIL = "^[\\w-\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
}
