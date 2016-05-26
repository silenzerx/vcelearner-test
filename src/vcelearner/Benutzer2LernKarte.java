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


/**
 *
 * @author Chris
 */
public class Benutzer2LernKarte {
    
    // Verbindungsvariablen
    
    static Connection con = null;
    static Statement st = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;
    
    // Objectvariablen

    private int benutzer_id;
    private int lernKarte_id;
    private boolean wiedervorlage;
    
    //    Konstruktoren

    public Benutzer2LernKarte(int benutzer_id, int lernKarte_id, boolean wiedervorlage) {
        this.benutzer_id = benutzer_id;
        this.lernKarte_id = lernKarte_id;
        this.wiedervorlage = wiedervorlage;
    }
    
//    GETTER

    public int getBenutzer_id() {
        return benutzer_id;
    }

    public int getLernKarte_id() {
        return lernKarte_id;
    }

    public boolean isWiedervorlage() {
        return wiedervorlage;
    }
    
    
    
    
}
