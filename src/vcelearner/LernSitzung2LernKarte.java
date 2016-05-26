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
public class LernSitzung2LernKarte {
    
    // Verbindungsvariablen
    
    static Connection con = null;
    static Statement st = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;
    
    // Objectvariablen
    
    private int lernSitzung_id;
    private int lernKarte_id;
    private boolean gemogelt;
    
//    Konstruktoren

    public LernSitzung2LernKarte(int lernSitzung_id, int lernKarte_id, boolean gemogelt) {
        this.lernSitzung_id = lernSitzung_id;
        this.lernKarte_id = lernKarte_id;
        this.gemogelt = gemogelt;
    }
    
//    GETTER

    public int getLernSitzung_id() {
        return lernSitzung_id;
    }

    public int getLernkarte_id() {
        return lernKarte_id;
    }

    public boolean isGemogelt() {
        return gemogelt;
    }
    
    
    
}
