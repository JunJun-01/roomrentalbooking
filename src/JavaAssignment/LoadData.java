/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaAssignment;

/**
 *
 * @author homew
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class LoadData {
    //Load all booking data function
    public static ArrayList<String> bookingdata() {
        //Appending every data in bookingdetails.txt to an arraylist
        ArrayList<String> bookingdata = new ArrayList<String>();
        try {
            //Open new file for the bookingdetails.txt
            File bookingfile = new File("bookingdetails.txt");
            //Extract content from the bookingdetails.txt
            Scanner myReader = new Scanner(bookingfile);
            //Read every line and add into booking data arraylist
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                bookingdata.add(data);
            }
            myReader.close();
        //Call error if file is not found.
        } catch (FileNotFoundException e) {
            System.out.println("File Not found");
        }
        //return bookingdata arraylist.
        return bookingdata;
    }
    
    //Load only room ID function from all booking data
    public static ArrayList<String> roombookingid() {
        //Set new arraylist from data in bookingdata() arraylist.
        ArrayList<String> bookingdata = bookingdata();

        //Separate every booking room id to another arraylist.
        ArrayList<String> roombookingid = new ArrayList<String>();
        //Booking room id data is contained in every index count 6.
        int index = 6;
        //Store every index count 6 data into the arraylist.
        while (index < bookingdata.size()) {
            roombookingid.add(bookingdata.get(index));
            index = index + 10;
            if (bookingdata.size() <= index) {
            }
        }
        //return roombookingid arraylist.
        return roombookingid;
    }
    
    //Load only booking start date function from all booking data
    public static ArrayList<String> bookingstartdate() {
        //Set new arraylist from data in bookingdata() arraylist.
        ArrayList<String> bookingdata = bookingdata();

        //Separate every start date to another arraylist
        ArrayList<String> bookingstartdate = new ArrayList<String>();
        //Start date data is contained in every index count 7.
        int index = 7;
        //Store every index count 7 data into the arraylist.
        while (index < bookingdata.size()) {
            bookingstartdate.add(bookingdata.get(index));
            index = index + 10;
            if (bookingdata.size() <= index) {
            }
        }
        //return bookingstartdate arraylist.
        return bookingstartdate;
    }
    
    //Load only booking end date function from all booking data
    public static ArrayList<String> bookingenddate() {
        //Set new arraylist from data in bookingdata() arraylist.
        ArrayList<String> bookingdata = bookingdata();

        //Separate every end date to another arraylist
        ArrayList<String> bookingenddate = new ArrayList<String>();
        //End date data is contained in every index count 8.
        int index = 8;
        //Store every index count 8 data into the arraylist.
        while (index < bookingdata.size()) {
            bookingenddate.add(bookingdata.get(index));
            index = index + 10;
            if (bookingdata.size() <= index) {
            }
        }
        //return bookingenddate arraylist.
        return bookingenddate;
    }
    
}

