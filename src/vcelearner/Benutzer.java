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
public class Benutzer {
    
    // Verbindungsvariablen
    
    static Connection con = null;
    static Statement st = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;
    
    // Objectvariablen
    
    private int id;
    private String login ;
    private String passwort;
    private String vorname;
    private String nachname;
    
    
//    Konstruktoren

    public Benutzer(int id, String login, String passwort, String vorname, String nachname) {
        this.id = id;
        this.login = login;
        this.passwort = passwort;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public Benutzer(String login, String passwort, String vorname, String nachname) {
        this.login = login;
        this.passwort = passwort;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public Benutzer(String login, String passwort) {
        this.login = login;
        this.passwort = passwort;
    }
    
    
//    GETTER

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }
    
    
    
    
    
    
    
    
    
    
}
