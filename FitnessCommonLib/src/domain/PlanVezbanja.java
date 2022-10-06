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
public class PlanVezbanja implements Serializable, OpstiDomenskiObjekat {
    private Long iDPlanVezbanja;
    private String naziv;
    private Korisnik iDKorisnik;
    

    public PlanVezbanja(Long iDPlanVezbanja, String naziv, Korisnik iDKorisnik) {
        this.iDPlanVezbanja = iDPlanVezbanja;
        this.naziv = naziv;
        this.iDKorisnik = iDKorisnik;
    }

    public PlanVezbanja(String naziv, Korisnik iDKorisnik) {
        this.naziv = naziv;
        this.iDKorisnik = iDKorisnik;
    }

    public PlanVezbanja() {
    }

    public Long getiDPlanVezbanja() {
        return iDPlanVezbanja;
    }

    public void setiDPlanVezbanja(Long iDPlanVezbanja) {
        this.iDPlanVezbanja = iDPlanVezbanja;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Korisnik getiDKorisnik() {
        return iDKorisnik;
    }

    public void setiDKorisnik(Korisnik iDKorisnik) {
        this.iDKorisnik = iDKorisnik;
    }

    @Override
    public String dajNazivTabele() {
        return "planvezbanja";
    }

    @Override
    public String dajNaziveAtributa() {
        return "naziv, iDKorisnik";
    //nema iDPlanVezbanja zato sto je autoinctementiran
    }

    @Override
    public String dajNazivAtributa(int brojKolone) {
        switch (brojKolone) {
            case 1:
                return "iDPlanVezbanja";
            case 2:
                return "naziv";
            case 3:
                return "iDKorisnik";
        }

        return "";
    }

    @Override
    public String dajVrednostiAtributa() {
        return "'"+naziv+"', '"+iDKorisnik.getID()+"'";
        //nema iDPlanVezbanja zato sto je autoinctementiran
               }

    @Override
    public String postaviVrednostiAtributa() {
        return "naziv='" + naziv + "', iDKorisnik='" + iDKorisnik.getID() + "'";
        //bez autoincrementiranih polja
    }

    @Override
    public boolean isAutoincrement() {
        return true;
    }

    @Override
    public void setId(Object id) {
        iDPlanVezbanja = (Long)id;
    }

    @Override
    public String getID() {
        return iDPlanVezbanja.toString();
    }

    @Override
    public String dajNazivPrimarnogKljuca() {
        return "iDPlanVezbanja";
                }

    @Override
    public List<String> dajKompleksniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> dajAtributeZaPretragu() {
        List<String> lista = new ArrayList<>();
        lista.add("iDKorisnik");
        return lista;
    }
    
    
}
