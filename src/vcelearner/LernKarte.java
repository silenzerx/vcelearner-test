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
public class LernKarte {

    // Verbindungsvariablen
    static Connection con = null;
    static Statement st = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;

    private int id;
    private String frage;
    private int schwierigkeitsGrad;
    private ArrayList<Themenbereich> tBs = null;
    private ArrayList<PotentielleAntwort> pAs = null;

    public LernKarte(int id, String frage, int schwierigkeitsGrad) {
        this.id = id;
        this.frage = frage;
        this.schwierigkeitsGrad = schwierigkeitsGrad;
    }

    public LernKarte(String frage, int schwierigkeitsGrad) {
        this.frage = frage;
        this.schwierigkeitsGrad = schwierigkeitsGrad;
    }

    public int getId() {
        return id;
    }

    public String getFrage() {
        return frage;
    }

    public int getSchwierigkeitsGrad() {
        return schwierigkeitsGrad;
    }

    public ArrayList<Themenbereich> gettBs() {
        return tBs;
    }

    public ArrayList<PotentielleAntwort> getpAs() {
        return pAs;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public void setSchwierigkeitsGrad(int schwierigkeitsGrad) {
        this.schwierigkeitsGrad = schwierigkeitsGrad;
    }

    public void settBs(ArrayList<Themenbereich> tBs) {
        this.tBs = tBs;
    }

    public void setpAs(ArrayList<PotentielleAntwort> pAs) {
        this.pAs = pAs;
    }

    public static void insert(LernKarte lK) {

        try {
            // VERBINDUNG AUFBBAUEN:
            con = DriverManager.getConnection("jdbc:mysql://192.168.2.3:3306/vcetrainer","Petra","Panke");
            // STATEMENT
            String Sql = "INSERT INTO lernkarte VALUES (null, ?, ?)";
            pst = con.prepareStatement(Sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, lK.getFrage());
            pst.setInt(2, lK.getSchwierigkeitsGrad());

            pst.executeUpdate();
            rst = pst.getGeneratedKeys();
            while (rst.next()) {
                lK.setId(rst.getInt(1));
            }

            //Zugehörigkeit zu Themenbereichen speichern
            for (Themenbereich tB : lK.gettBs()) {
                Lernkarte2Themenbereich lK2TB = new Lernkarte2Themenbereich(lK.getId(), tB.getId());
                Lernkarte2Themenbereich.insert(lK2TB);
            }
            //Zugehörige PotentielleAntworten speichern
            for (PotentielleAntwort pA : lK.getpAs()) {
                pA.setLernKarte_id(lK.getId());
                PotentielleAntwort.insert(pA);
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

    }

    public static void delete(LernKarte lK) {

        try {
            Lernkarte2Themenbereich.delete(lK.getId());
            PotentielleAntwort.delete(lK.getId());

            con = DriverManager.getConnection("jdbc:mysql://192.168.2.3:3306/vcetrainer","Petra","Panke");
            String Sql = "DELETE FROM lernkarte WHERE id=?";
            pst = con.prepareStatement(Sql);
            pst.setInt(1, lK.getId());

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

    public static ArrayList<LernKarte> getAll() {
        ArrayList<LernKarte> lKs = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.2.3:3306/vcetrainer","Petra","Panke");
            String sql = "SELECT * FROM lernkarte";
            st = con.createStatement();
            rst = st.executeQuery(sql);
            while (rst.next()) {

                LernKarte lK = new LernKarte(rst.getInt("id"), rst.getString("frage"), rst.getInt("schwierigkeitsGrad"));

                lK.setpAs(PotentielleAntwort.getAllByLernKarte_id(lK.getId()));

                ArrayList<Lernkarte2Themenbereich> lK2TBs = Lernkarte2Themenbereich.getAllByLernKarte_id(lK.getId());

                ArrayList<Themenbereich> tBs = new ArrayList<>();
                for (Lernkarte2Themenbereich lK2TB : lK2TBs) {
                    tBs.add(Themenbereich.getById(lK2TB.getThemenBereich_id()));

                }
                lK.settBs(tBs);
                lKs.add(lK);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (st != null) {
                    st.close();
                }
                if (rst != null) {
                    rst.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return lKs;
    }
    
     public static LernKarte getById(int lkid) {
       LernKarte lK = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.2.3:3306/vcetrainer","Petra","Panke");
            String sql = "SELECT * FROM lernkarte WHERE id=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, lkid);
            rst = pst.executeQuery();
            while (rst.next()) {

                lK = new LernKarte(rst.getInt("id"), rst.getString("frage"), rst.getInt("schwierigkeitsGrad"));

                lK.setpAs(PotentielleAntwort.getAllByLernKarte_id(lK.getId()));

                ArrayList<Lernkarte2Themenbereich> lK2TBs = Lernkarte2Themenbereich.getAllByLernKarte_id(lK.getId());

                ArrayList<Themenbereich> tBs = new ArrayList<>();
                for (Lernkarte2Themenbereich lK2TB : lK2TBs) {
                    tBs.add(Themenbereich.getById(lK2TB.getThemenBereich_id()));

                }
                lK.settBs(tBs);
               
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (st != null) {
                    st.close();
                }
                if (rst != null) {
                    rst.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return lK;
    }   

    public static void update(LernKarte lK) {

        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.2.3:3306/vcetrainer","Petra","Panke");
            String sql = "UPDATE lernkarte SET frage=?, schwierigkeitsgrad=? WHERE id=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, lK.getFrage());
            pst.setInt(2, lK.getSchwierigkeitsGrad());
            pst.setInt(3, lK.getId());

            pst.executeUpdate();

            Lernkarte2Themenbereich.delete(lK.getId());

            for (Themenbereich tB : lK.gettBs()) {
                Lernkarte2Themenbereich lK2TB = new Lernkarte2Themenbereich(lK.getId(), tB.getId());
                Lernkarte2Themenbereich.insert(lK2TB);
            }
            PotentielleAntwort.delete(lK.getId());

            for (PotentielleAntwort pA : lK.getpAs()) {
                pA.setLernKarte_id(lK.getId());
                PotentielleAntwort.insert(pA);
            }

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
//    @Override
//    public String toString() {
//        return "LernKarte{" + "id=" + id + ", frage=" + frage + ", schwierigkeitsGrad=" + schwierigkeitsGrad + ", tBs=" + tBs + ", pAs=" + pAs + '}';
//    }

    @Override
    public String toString() {
        return "LernKarte ID: " + id
                + "\n" + "Schwierigkeitsgrad: " + schwierigkeitsGrad
                + "\n" + "Themenbereiche: " + tBs
                + "\n" + "Frage: " + frage
                + "\n" + "Potentielle Antworten: " + pAs + "\n";
    }
}
