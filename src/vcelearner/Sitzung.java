package vcelearner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;

/**
 *
 * @author J.Weidehaas
 */
public class Sitzung {

    private ArrayList<LernKarte> lKs = LernKarte.getAll();
    private int aktuellerLKIndex = 0;

    @Override
    public String toString() {
        return "Sitzung{" + "lKs=" + lKs + ", aktuellerLKIndex=" + aktuellerLKIndex + '}';
    }
    
    

    public LernKarte getAktuelleLernKarte() {
        return lKs.get(aktuellerLKIndex);
    }

    public void setAktuelleLernKarte(LernKarte lK) {
        lKs.set(aktuellerLKIndex, lK);
        LernKarte.update(lK);
    }

    public void addLernKarte(LernKarte lK) {
        LernKarte.insert(lK);
        lKs.add(lK);
        aktuellerLKIndex = lKs.size() - 1;
    }

    public void removeLernKarte() {
        LernKarte.delete(lKs.get(aktuellerLKIndex));
        lKs.remove(lKs.get(aktuellerLKIndex));
        if (aktuellerLKIndex >= lKs.size()) {
            aktuellerLKIndex = lKs.size() - 1;
        }
    }

    public LernKarte getNextLernKarte() {
        if (aktuellerLKIndex == lKs.size() - 1) {
            aktuellerLKIndex = 0;
        } else {
            aktuellerLKIndex++;
        }
        return getAktuelleLernKarte();
    }

    public LernKarte getPrevLernKarte() {
        if (aktuellerLKIndex == 0) {
            aktuellerLKIndex = lKs.size() - 1;
        } else {
            aktuellerLKIndex--;
        }
        return getAktuelleLernKarte();
    }

    public LernKarte getById(int id) {
        for (int i = 0; i < lKs.size(); i++) {
            if (lKs.get(i).getId() == id) {
                aktuellerLKIndex = i;
                break;
            }
        }
        return getAktuelleLernKarte();
    }

//    public void addAktuelleLernKarte (LernKarte lK){
//        LernKarte.insert(lK);
//        lKs.add(lK);
//    }
//    public void removeLernKarte (LernKarte lK){
//        lKs.remove(lK);
//        LernKarte.delete(lK);
//    }
//    public LernKarte geheZu(int id) {
//        for (LernKarte lK : lKs) {
//            if (lK.getId() == id) {
//                aktuellerLKIndex = lKs.indexOf(lK);
//            }
//            break;
//        }
//        return getAktuelleLernKarte();
//    }
}
