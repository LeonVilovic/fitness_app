/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marija
 */
public class StavkaPlanaVezbanja implements Serializable,OpstiDomenskiObjekat{
    private PlanVezbanja iDPlanVezbanja;
    private Vezba iDVezba;
    private int redniBroj;

    public StavkaPlanaVezbanja() {
    }

    public StavkaPlanaVezbanja(PlanVezbanja iDPlanVezbanja, Vezba iDVezba, int redniBroj) {
        this.iDPlanVezbanja = iDPlanVezbanja;
        this.iDVezba = iDVezba;
        this.redniBroj = redniBroj;
    }

    public StavkaPlanaVezbanja(Vezba iDVezba, int redniBroj) {
        this.iDVezba = iDVezba;
        this.redniBroj = redniBroj;
    }

    public PlanVezbanja getiDPlanVezbanja() {
        return iDPlanVezbanja;
    }

    public void setiDPlanVezbanja(PlanVezbanja iDPlanVezbanja) {
        this.iDPlanVezbanja = iDPlanVezbanja;
    }

    public Vezba getiDVezba() {
        return iDVezba;
    }

    public void setiDVezba(Vezba iDVezba) {
        this.iDVezba = iDVezba;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    @Override
    public String dajNazivTabele() {
        return "stavkaplanavezbanja";
    }

    @Override
    public String dajNaziveAtributa() {
        return "iDPlanVezbanja, iDVezba, redniBroj";
    }

    @Override
    public String dajNazivAtributa(int brojKolone) {
        switch (brojKolone) {
            case 1:
                return "iDPlanVezbanja";
            case 2:
                return "iDVezba";
            case 3:
                return "redniBroj";
        }
        return "";
    }

    @Override
    public String dajVrednostiAtributa() {
        return "'"+iDPlanVezbanja.getID()+"', '"+iDVezba.getID()+"', '"+redniBroj+"'";
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "iDPlanVezbanja='" + iDPlanVezbanja.getID() + "', iDVezba='" + iDVezba.getID() + "', redniBroj='" + redniBroj +"'";
    }

    @Override
    public boolean isAutoincrement() {
        return false;
    }

    @Override
    public void setId(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getID() {
        return "'" + iDPlanVezbanja.getID() + "', '" + iDVezba.getID() + "'";
    //vracanje kompleksnog id-a
    }

 

    @Override
    public List<String> dajKompleksniKljuc() {
        List<String> lista = new ArrayList<>();
        lista.add("iDPlanVezbanja");
        lista.add("iDVezba");
        lista.add("redniBroj");
        return lista;
    }

    @Override
    public List<String> dajAtributeZaPretragu() {
        List<String> lista = new ArrayList<>();
        lista.add("iDPlanVezbanja");
        return lista;
    }

    @Override
    public String dajNazivPrimarnogKljuca() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
