/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.OpstiDomenskiObjekat;
import java.util.List;

/**
 *
 * @author Marija
 */
public class SOPretraziKorisnike extends OpstaSistemskaOperacija {

    String kriterijumPretrage;
    List<OpstiDomenskiObjekat> rezultatPretrage;

    public SOPretraziKorisnike(OpstiDomenskiObjekat odo, String kriterijumPretrage) {
        super(odo);
        this.kriterijumPretrage = kriterijumPretrage;
    }

    @Override
    public void izvrsenjeOperacije() throws Exception {
        rezultatPretrage = dbbr.search(odo, kriterijumPretrage);
        response = rezultatPretrage;
    }

}
