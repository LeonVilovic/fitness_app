/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.Vezba;

/**
 *
 * @author Marija
 */
public class SOZapamtiVezbu extends OpstaSistemskaOperacija {

    public SOZapamtiVezbu(Vezba vezba) {
        super(vezba);
    }

    @Override
    public void izvrsenjeOperacije() throws Exception {
        dbbr.insert(odo);
    }
}
