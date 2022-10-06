/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.Korisnik;
import domain.TezinaNaDan;


/**
 *
 * @author Marija
 */
public class SOZapamtiKorisnika extends OpstaSistemskaOperacija{

    TezinaNaDan tezinaNaDan;
    
    public SOZapamtiKorisnika(Korisnik korisnik,TezinaNaDan tezinaNaDan) {
        super(korisnik);
        this.tezinaNaDan = tezinaNaDan;
    }

    @Override
    public void izvrsenjeOperacije() throws Exception {
        
        dbbr.insert(odo);
        dbbr.insert(tezinaNaDan);
    }
    
}
