/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import domain.TezinaNaDan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marija
 */
public class SOUcitajKorisnika extends OpstaSistemskaOperacija {

    public SOUcitajKorisnika(OpstiDomenskiObjekat odo) {
        super(odo);
    }

    @Override
    public void izvrsenjeOperacije() throws Exception {
        Korisnik k = (Korisnik) odo;
        TezinaNaDan t = new TezinaNaDan();
        List<OpstiDomenskiObjekat> listaObjekataZaVracanje = new ArrayList<>();
        List<OpstiDomenskiObjekat> listaTezinaNaDan = new ArrayList<>();
        listaObjekataZaVracanje.add(dbbr.getODOUsingPK(odo, k.getIDKorisnik()));
        listaTezinaNaDan = dbbr.search(t, k.getID());
        listaObjekataZaVracanje.addAll(listaTezinaNaDan);
        response = listaObjekataZaVracanje;
        // prvi objekat je tipa korisnik ostali su tipa tezinaNaDan
    }
    
}
