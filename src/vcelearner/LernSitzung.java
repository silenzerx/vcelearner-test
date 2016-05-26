/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vcelearner;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author Chris
 */
public class LernSitzung {
    
    // Verbindungsvariablen
    
    static Connection con = null;
    static Statement st = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;
    
    // Objectvariablen
    
    private int id;
    private String typ;
    private Date datum;
    private int benutzer_id;
    
    
        
//    Konstruktoren

    public LernSitzung(int id, String typ, Date datum, int benutzer_id) {
        this.id = id;
        this.typ = typ;
        this.datum = datum;
        this.benutzer_id = benutzer_id;
    }

    public LernSitzung(String typ, Date datum, int benutzer_id) {
        this.typ = typ;
        this.datum = datum;
        this.benutzer_id = benutzer_id;
    }
    
        
//    GETTER

    public int getId() {
        return id;
    }

    public String getTyp() {
        return typ;
    }

    public Date getDatum() {
        return datum;
    }

    public int getBenutzer_id() {
        return benutzer_id;
    }
    
    
    
}
