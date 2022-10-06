/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;

/**
 *
 * @author Marija
 */
public interface OpstiDomenskiObjekat {
    public String dajNazivTabele();
    public String dajNaziveAtributa();
    public String dajNazivAtributa(int brojKolone);
    public String dajVrednostiAtributa();
    public String postaviVrednostiAtributa();
    public boolean isAutoincrement();
    public void setId(Object id);
    public String getID();
    public String dajNazivPrimarnogKljuca();
    public List<String> dajKompleksniKljuc();
    public List<String> dajAtributeZaPretragu();
}
