package vcelearner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author J.Weidehaas
 */
public class Lernkarte2Themenbereich {

    // Verbindungsvariablen
    static Connection con = null;
    static Statement st = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;

    private int id;
    private int lernKarte_id;
    private int themenBereich_id;

    public Lernkarte2Themenbereich(int lernKarte_id, int themenBereich_id) {
        this.lernKarte_id = lernKarte_id;
        this.themenBereich_id = themenBereich_id;
    }

    public Lernkarte2Themenbereich(int id, int lernKarte_id, int themenBereich_id) {
        this.id = id;
        this.lernKarte_id = lernKarte_id;
        this.themenBereich_id = themenBereich_id;
    }

    public int getId() {
        return id;
    }

    public int getLernKarte_id() {
        return lernKarte_id;
    }

    public int getThemenBereich_id() {
        return themenBereich_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void insert(Lernkarte2Themenbereich lK2TB) {

        try {
            // VERBINDUNG AUFBBAUEN:
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vcetrainer","root","ncc1701d");
            // STATEMENT
            String Sql = "INSERT INTO lernkarte2themenbereich VALUES (null, ?, ?)";
            pst = con.prepareStatement(Sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, lK2TB.getLernKarte_id());
            pst.setInt(2, lK2TB.getThemenBereich_id());

            pst.executeUpdate();
            rst = pst.getGeneratedKeys();
            while (rst.next()) {
                lK2TB.setId(rst.getInt(1));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rst != null) {
                    rst.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
//                Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update() {

        try {
            // VERBINDUNG AUFBBAUEN:
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vcetrainer","root","ncc1701d");
            // PREPARED STATEMENT:
            String Sql = "UPDATE lernkarte2themenbereich SET lernkarte_id=?, themenbereich_id=? WHERE id=?";
            pst = con.prepareStatement(Sql);
            pst.setInt(1, lernKarte_id);
            pst.setInt(2, themenBereich_id);
            pst.setInt(3, id);

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
//                Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void delete(int lKid) {

        try {
            // VERBINDUNG AUFBAUEN:
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vcetrainer","root","ncc1701d");
            // STATEMENT
            String Sql = "DELETE FROM lernkarte2themenbereich WHERE lernkarte_id=?";
            pst = con.prepareStatement(Sql);
            pst.setInt(1, lKid);

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
            
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
 public static ArrayList<Lernkarte2Themenbereich> getAllByLernKarte_id(int lKid) {
        ArrayList<Lernkarte2Themenbereich> lK2TBs = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vcetrainer","root","ncc1701d");
            String Sql = "SELECT * FROM lernkarte2themenbereich WHERE lernkarte_id=?";
            pst = con.prepareStatement(Sql);
            pst.setInt(1, lKid);
            rst = pst.executeQuery();
            while (rst.next()) {

                Lernkarte2Themenbereich lK2TB = new Lernkarte2Themenbereich(rst.getInt("lernkarte_id"), rst.getInt("themenbereich_id"));
                lK2TB.setId(rst.getInt("id"));
                lK2TBs.add(lK2TB);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rst != null) {
                    rst.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return lK2TBs;
    }
    
    @Override
    public String toString() {
        return "Lernkarte2Themenbereich{" + "id=" + id + ", lernKarte_id=" + lernKarte_id + ", themenBereich_id=" + themenBereich_id + '}';
    }

}
