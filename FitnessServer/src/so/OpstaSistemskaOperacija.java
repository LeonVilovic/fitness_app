/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import database.DatabaseBroker;
import domain.OpstiDomenskiObjekat;
import exception.ValidationException;
import validator.Validator;

/**
 *
 * @author Marija
 */
public abstract class OpstaSistemskaOperacija {
    DatabaseBroker dbbr;
    Validator validator;
    OpstiDomenskiObjekat odo;
    Object response;

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
    
    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public OpstaSistemskaOperacija(OpstiDomenskiObjekat odo) {
        dbbr=new DatabaseBroker();
        this.odo=odo;
    }
    
    public void uspostaviKonekciju() throws Exception{
        dbbr.connect();
    }
    
    public void raskiniKonekciju() throws Exception{
        dbbr.disconnect();
    }
    
    public void proveriPreduslove() throws ValidationException{
        
        
        if(validator!=null){
            validator.validate(odo);
        }
    }
    
    public abstract void izvrsenjeOperacije() throws Exception;
    
    
    public void opsteIzvrsenje() throws Exception{
            
        uspostaviKonekciju();
        
        proveriPreduslove();
        
        try{
            izvrsenjeOperacije();
            dbbr.commit();
        }catch(Exception e){
            dbbr.rollback();
            throw e;
        }finally{
            raskiniKonekciju();
        }
      
    }
    
    public OpstiDomenskiObjekat getOdo(){
        return odo;
    }
}
