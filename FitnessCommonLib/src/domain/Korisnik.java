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
public class Korisnik implements Serializable,OpstiDomenskiObjekat{
    private Long iDKorisnik;
    private String ime;
    private String prezime;
    private String email;
    private String username;
    private String password;
    private double visina;

    public Korisnik() {
    }

    public Korisnik(Long iDKorisnik, String ime, String prezime, String email, String username, String password, double visina) {
        this.iDKorisnik = iDKorisnik;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.username = username;
        this.password = password;
        this.visina = visina;
    }

    public Korisnik(String ime, String prezime, String email, String username, String password, double visina) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.username = username;
        this.password = password;
        this.visina = visina;
    }

    
    
    
    public Long getIDKorisnik() {
        return iDKorisnik;
    }

    public void setIDKorisnik(Long IDKorisnik) {
        this.iDKorisnik = IDKorisnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getVisina() {
        return visina;
    }

    public void setVisina(double visina) {
        this.visina = visina;
    }

    @Override
    public String dajNazivTabele() {
        return "korisnik";
    }

    @Override
    public String dajNaziveAtributa() {
        return "ime, prezime, email, username, password, visina";
    //bez autoincrementiranih polja
    }

    @Override
    public String dajNazivAtributa(int brojKolone) {
        switch (brojKolone) {
            case 1:
                return "iDKorisnik";
            case 2:
                return "ime";
            case 3:
                return "prezime";
            case 4:
                return "email";
            case 5:
                return "username";
            case 6:
                return "password";
            case 7:
                return "visina";
        }

        return "";
    }

    @Override
    public String dajVrednostiAtributa() {
        return "'"+ime+"', '"+prezime+"', '"+email+"', '"+username+"', '"+password+"', '"+visina+"'";
        //nema iDKorisnik zato sto je autoinctementiran
               }

    @Override
    public String postaviVrednostiAtributa() {
        return "ime='" + ime + "', prezime='" + prezime +"', email='" + email +"', username='" + username +"', password='" + password +"', visina='" + visina + "'";
        //bez autoincrementiranih polja
    }

    @Override
    public boolean isAutoincrement() {
       return true;
    }

    @Override
    public void setId(Object id) {
       iDKorisnik = (Long)id;
    }

    @Override
    public String getID() {
        return iDKorisnik.toString();
    }

    @Override
    public String dajNazivPrimarnogKljuca() {
        return "iDKorisnik";
    }

    @Override
    public List<String> dajKompleksniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<String> dajAtributeZaPretragu() {
        List<String> lista = new ArrayList<>();
        lista.add("ime");
        lista.add("prezime");
        lista.add("username");
        return lista;
    }
    
}
