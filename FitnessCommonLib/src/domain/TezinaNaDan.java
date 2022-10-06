/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Marija
 */
public class TezinaNaDan implements Serializable,OpstiDomenskiObjekat{

    private Long iDTezinaNaDan;
    private Date datum;
    private double tezina;
    private Korisnik iDkorisnik;

    public TezinaNaDan() {
    }

    public TezinaNaDan(Long iDTezinaNaDan, Date datum, double tezina, Korisnik iDkorisnik) {
        this.iDTezinaNaDan = iDTezinaNaDan;
        this.datum = datum;
        this.tezina = tezina;
        this.iDkorisnik = iDkorisnik;
    }

    public TezinaNaDan(Date datum, double tezina, Korisnik iDkorisnik) {
        this.datum = datum;
        this.tezina = tezina;
        this.iDkorisnik = iDkorisnik;
    }

    public Long getiDTezinaNaDan() {
        return iDTezinaNaDan;
    }

    public void setiDTezinaNaDan(Long iDTezinaNaDan) {
        this.iDTezinaNaDan = iDTezinaNaDan;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public double getTezina() {
        return tezina;
    }

    public void setTezina(double tezina) {
        this.tezina = tezina;
    }

    public Korisnik getiDkorisnik() {
        return iDkorisnik;
    }

    public void setiDkorisnik(Korisnik iDkorisnik) {
        this.iDkorisnik = iDkorisnik;
    }

    @Override
    public String dajNazivTabele() {
        return "tezinanadan";
    }

    @Override
    public String dajNaziveAtributa() {
         return "datum, tezina, iDkorisnik";
    //bez autoincrementiranih polja
    }

    @Override
    public String dajNazivAtributa(int brojKolone) {
        switch (brojKolone) {
            case 1:
                return "iDTezinaNaDan";
            case 2:
                return "datum";
            case 3:
                return "tezina";
            case 4:
                return "iDkorisnik";
        }

        return "";
    }

    @Override
    public String dajVrednostiAtributa() {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        return "'"+sdf.format(datum)+"', '"+tezina+"', '"+iDkorisnik.getID()+"'";
        //bez autoincrementiranih polja
    }

    @Override
    public String postaviVrednostiAtributa() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "datum='" + sdf.format(datum) + "', tezina='" + tezina +"', iDkorisnik='" + iDkorisnik.getID() + "'";
        //bez autoincrementiranih polja
    }

    @Override
    public boolean isAutoincrement() {
        return true;
    }

    @Override
    public void setId(Object id) {
        iDTezinaNaDan = (Long) id;
    }

    @Override
    public String getID() {
         return iDTezinaNaDan.toString();
    }

    @Override
    public String dajNazivPrimarnogKljuca() {
        return "iDTezinaNaDan";
        
    }

    @Override
    public List<String> dajKompleksniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> dajAtributeZaPretragu() {
        List<String> lista = new ArrayList<>();
        lista.add("iDkorisnik");
        return lista;
    }
    
}
