/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaAssignment;

/**
 *
 * @author homew
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Booking {
    //Validate booking function
    public static int validatebooking(String username, String icnumber, String contactnumber, String email, String idname) {
        int error = 0;
        //error 1 = input error
        //error 2 = IC number invalid
        //error 0 = no error

        //Data validation for name, contact number, email
        if (username.length() < 2 || username.length() > 50 || username.matches("[+-]?\\d*(\\.\\d+)?") == true
                || contactnumber.length() < 10 || contactnumber.length() > 11 || contactnumber.matches("[+-]?\\d*(\\.\\d+)?") == false
                || email.contains("@") == false || email.contains(".com") == false) {
                    //Display error 1
                    error = 1;
        }

        //Data validation for IC / Passport
        if (idname.equals("IC / Passport :")) {
            if (icnumber.length() != 12 || icnumber.matches("[+-]?\\d*(\\.\\d+)?") == false) {
                //Display error 2
                error = 2;
            }
        }
        //If no eeror Display error 0
        return error;
    }
    
    //Booked room id function
    public static ArrayList<String> bookedroomid(String startdate1, String enddate1) {
        //List out arraylist from booking start and end date arraylist.
        ArrayList<String> bookingstartdate = LoadData.bookingstartdate();
        ArrayList<String> bookingenddate = LoadData.bookingenddate();
        
        //Set Date Format
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy/MM/dd");
        
        //Set date format to string
        Date startdate = null, enddate = null;
        try {
            //Enter date input from the arraylist above.
            startdate = DateFormat.parse(startdate1);
            enddate = DateFormat.parse(enddate1);
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null,"System Error");
        }
        
        //Create new arraylist
        ArrayList<String> index = new ArrayList<String>();
        
        //Insert booking index where both start and end date that overlaps with another booking's date.
        int count = 0;
        while (count < bookingstartdate.size()) {
            //Set date format to string
            Date bookedstartdate1 = null, bookedenddate1 = null;
            try {
                //Count both start and end date.
                bookedstartdate1 = DateFormat.parse(bookingstartdate.get(count));
                bookedenddate1 = DateFormat.parse(bookingenddate.get(count));
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null,"System Error");
            }
            
            //When the duration is not within both start date and end date duration.
            if (startdate.after(bookedenddate1) || enddate.before(bookedstartdate1)) {
            } else {
                index.add(String.valueOf(count));
            }
            count++;
        }
        
        //List all booking room id into an arraylist
        ArrayList<String> roombookingid = LoadData.roombookingid();
        
        //Create an arraylist to store all the booked rooms
        ArrayList<String> bookedroomid = new ArrayList<String>();
        
        //Set default value
        int count1 = 0;
        //List out all the booked room id into the arraylist.
        while (count1 < index.size()) {
            int index1 = Integer.parseInt(index.get(count1));
            bookedroomid.add(roombookingid.get(index1));
            count1++;
        }
        return bookedroomid;
    }
    
    //Load room booking data into table function
    public static void LoadBookingTable(DefaultTableModel model) {
        //Load booking details data from bookingsdetails.txt into the table.
        //Count number of line/data in bookingdetails.txt
        int line1 = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("bookingdetails.txt"))) {
            while (reader.readLine() != null) {
                line1++;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"System Error");
        }
        
        //Every 10 data = 1 room booking
        int bookings = (line1 / 10);
        
        //Setting up the string (tempdata) to input all the data which is required for the table
        String tempdata = "";
        
        //Set number of table rows.
        model.setRowCount(bookings);
        
        //Insert data to tempdata from bookingdetails.txt
        File bookingfile = new File("bookingdetails.txt");
        try {
            Scanner myReader = new Scanner(bookingfile);
            for (int row = 0; row != bookings; row++) {
                for (int i = 0; i != 12; i++) {
                    while (myReader.hasNextLine() && i != 0) {
                        String data = myReader.nextLine();
                        tempdata = tempdata + data + "\n";
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        
        //Split string tempdata into an array under string eachdata
        String[] eachdata = tempdata.split("\n");
        
        //Load the array into table
        int i = 0;
        //10 column and row = total number of room bookings
        for (int row = 0; row != bookings; row++) {
            for (int col = 0; col != 10; col++) {
                model.setValueAt(eachdata[i], row, col);
                i++;
            }
        }
    }
    
    //Modify room booking function
    public static void modifybooking(String name, String citizen, String ic, String contact, String email, String roomtype, String roomid, String startdate, String enddate, String price) {
        
        //Create new string array to store data from room booking textbox input
        ArrayList<String> modifydata = new ArrayList<String>();
        modifydata.add(name);
        modifydata.add(citizen);
        modifydata.add(ic);
        modifydata.add(contact);
        modifydata.add(email);
        modifydata.add(roomtype);
        modifydata.add(roomid);
        modifydata.add(startdate);
        modifydata.add(enddate);
        modifydata.add(price);
        
        //Insert all room booking data into an arraylist
        ArrayList<String> bookingdata = LoadData.bookingdata();
        
        //Create new string
        String newdata = ""; 
        int count = 0;
        //Insert new data by looking through the username index 0.
        while (count < bookingdata.size()) {
            if (bookingdata.get(count).equals(modifydata.get(0))) {
                for (int i = 0; i != 10; i++) {
                    //Every data line once complete skips to next line with \n
                    newdata = newdata + modifydata.get(i) + "\n";
                    if (i < 9) {
                        count++;
                    }
                }
            } else {
                newdata = newdata + bookingdata.get(count) + "\n";
            }
            count++;
        }

        //Rewrite the room booking data in bookingdetails.txt
        File bookingfile1 = new File("bookingdetails.txt");
        try {
            FileWriter bookingfile2 = new FileWriter(bookingfile1, false);
            bookingfile2.write(newdata);
            bookingfile2.close();
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null,"File System Error");
        }          
    }
    
    //Delete room booking function
    public static void deletebooking(String name) {
        //Create new arraylist and insert room booking data into the arraylist
        ArrayList<String> bookingdata = LoadData.bookingdata();

        //Create new string and insert all data except the deleted data by looking through the username string
        String newdata = ""; int count = 0;
        while (count < bookingdata.size()) {
            if (bookingdata.get(count).equals(name)) {
                count = count + 9;
            } else {
                //Every data line once complete skips to next line with \n
                newdata = newdata + bookingdata.get(count) + "\n";
            }
            count++;
        }

        //Rewrite the room booking data in the bookingdetails.txt
        File bookingfile1 = new File("bookingdetails.txt");
        try {
            FileWriter bookingfile2 = new FileWriter(bookingfile1, false);
            bookingfile2.write(newdata);
            bookingfile2.close();
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null,"File System Error");
        }          
    }
    
}
