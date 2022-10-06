/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.OpstiDomenskiObjekat;
import domain.Vezba;

/**
 *
 * @author Marija
 */
public class SOUcitajVezbu extends OpstaSistemskaOperacija{

    public SOUcitajVezbu(OpstiDomenskiObjekat odo) {
        super(odo);
    }

    
    @Override
    public void izvrsenjeOperacije() throws Exception {
        Vezba v = (Vezba) odo;
        response = dbbr.getODOUsingPK(odo, v.getiDVezba());
    }
    
}
