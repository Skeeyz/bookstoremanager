package org.example.DAO;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestConnect{
    public static void main(String []args){
        LocalDate today = LocalDate.now();
        System.out.print(today.toString());
        Date today1= new Date();
        try {
            today1= new SimpleDateFormat("yyyy-MM-dd").parse(today.toString());
        } catch (ParseException ex) {
            Logger.getLogger(TestConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print(today1.toString());
    }
}