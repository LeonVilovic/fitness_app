/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import database.DatabaseBroker;
import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import exception.ValidationException;
import java.util.List;

/**
 *
 * @author Marija
 */
public class ValidatorUlogujKorisnka implements Validator {

    DatabaseBroker dbbr;
    
    public ValidatorUlogujKorisnka() {
    }

    @Override
    public void validate(Object value) throws ValidationException {
        try {
            Korisnik korisnik = (Korisnik) value;
            
            if ((korisnik.getUsername() == null || korisnik.getUsername().matches("")) && (korisnik.getPassword() == null || korisnik.getPassword().matches(""))) {
                throw new Exception("Niste uneli ni username ni password!");
            }

            if (korisnik.getUsername() == null || korisnik.getUsername().matches("")) {
                throw new Exception("Niste uneli username!");
            }

            if (korisnik.getPassword() == null || korisnik.getPassword().matches("")) {
                throw new Exception("Niste uneli password!");
            }
            
            dbbr = new DatabaseBroker();
            dbbr.connect();
            List<OpstiDomenskiObjekat> sviKorisnici = dbbr.search(korisnik, "");
            dbbr.disconnect();
            
            boolean postojiUsername=false;
            boolean passwordJeTacan=false;
            
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : sviKorisnici) {
                Korisnik k = (Korisnik)opstiDomenskiObjekat;
                if (k.getUsername().equals(korisnik.getUsername())) {
                    postojiUsername=true;
                    if (k.getPassword().equals(korisnik.getPassword())) {
                        passwordJeTacan=true;
                    }
                    break;
                }  
            }
            if (!postojiUsername) {
                throw new Exception("Ne postoji korisnik sa datim korisnickim imenom");
            }
            if (postojiUsername && !passwordJeTacan) {
                throw new Exception("Password je netacan");
            }
 
            

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ValidationException(ex.getMessage());
        }
    }

}
