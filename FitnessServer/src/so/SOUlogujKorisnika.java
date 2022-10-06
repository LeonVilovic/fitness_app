/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import java.util.List;

/**
 *
 * @author Marija
 */
public class SOUlogujKorisnika extends OpstaSistemskaOperacija{

    public SOUlogujKorisnika(OpstiDomenskiObjekat odo) {
        super(odo);
    }

    @Override
    public void izvrsenjeOperacije() throws Exception {
       
       Korisnik korisnikZaLogovanje = (Korisnik) odo;
      
       List<OpstiDomenskiObjekat> sviKorisnici = dbbr.search(odo, "");
       
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : sviKorisnici) {
            Korisnik k = (Korisnik) opstiDomenskiObjekat;
            if (k.getPassword().equals(korisnikZaLogovanje.getPassword()) && k.getUsername().equals(korisnikZaLogovanje.getUsername())) {
                response = k;
            }
        }
    }
    
}
