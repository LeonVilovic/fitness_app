/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import database.DatabaseBroker;
import domain.OpstiDomenskiObjekat;
import domain.Vezba;
import exception.ValidationException;
import java.util.List;

/**
 *
 * @author Marija
 */
public class ValidatorAzurirajVezbu implements Validator {

    DatabaseBroker dbbr;
    public ValidatorAzurirajVezbu() {
    }

    @Override
    public void validate(Object value) throws ValidationException {
        try {
            Vezba v = (Vezba) value;
            
            if (v.getNaziv() == null || v.getNaziv().matches("")) {
                throw new Exception("Polje Naziv je obavezno!");
            }
            if (v.getNaziv().length()>18 ) {
                throw new Exception("Naziv je predugacak");
            }
            if (v.getNaziv().length()<2 ) {
                throw new Exception("Naziv mora imati bar 2 slova");
            }
           if (v.getPutDoFajla().length()>100 ) {
                throw new Exception("Pud do fajla je predugacak");
            }

            
           
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ValidationException(ex.getMessage());
        }
    }

}
