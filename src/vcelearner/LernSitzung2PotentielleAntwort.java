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
public class LernSitzung2PotentielleAntwort {
    
    // Verbindungsvariablen
    
    static Connection con = null;
    static Statement st = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;
    
    // Objectvariablen
    
    private int lernSitzung_id;
    private int potentielleAntwort_id;
    
//    Konstruktoren

    public LernSitzung2PotentielleAntwort(int lernSitzung_id, int potentielleAntwort_id) {
        this.lernSitzung_id = lernSitzung_id;
        this.potentielleAntwort_id = potentielleAntwort_id;
    }
    
//    GETTER

    public int getLernSitzung_id() {
        return lernSitzung_id;
    }

    public int getPotentielleAntwort_id() {
        return potentielleAntwort_id;
    }
    
    
    
    
}
