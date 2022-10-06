/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import domain.PlanVezbanja;
import domain.StavkaPlanaVezbanja;
import domain.Vezba;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marija
 */
public class TableModelPlanVezbe extends AbstractTableModel {

    boolean editable;
    //List<Vezba> sveVezbe;
    List<StavkaPlanaVezbanja> stavke;
    PlanVezbanja planVezbanja;

    String[] columnNames = new String[]{"Stavka broj", "Naziv", "Tezina Vezbe"};



    public TableModelPlanVezbe(List<StavkaPlanaVezbanja> stavke, PlanVezbanja planVezbanja) {
        this.stavke = stavke;
        this.planVezbanja = planVezbanja;
    }

    public List<StavkaPlanaVezbanja> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaPlanaVezbanja> stavke) {
        this.stavke = stavke;
    }

    public PlanVezbanja getPlanVezbanja() {
        return planVezbanja;
    }

    public void setPlanVezbanja(PlanVezbanja planVezbanja) {
        this.planVezbanja = planVezbanja;
    }
    
    
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    
    
    public void ubaciPlanVezbanja(PlanVezbanja planVezbanja) {
        this.planVezbanja = planVezbanja;

    }
    public void ubaciStavkuPlana(StavkaPlanaVezbanja stavka){
            stavke.add(stavka);
    }
    
        public void removeStavkuPlana(int index){
            stavke.remove(index);
            updateRedniBrojevi();
    }
        
    public void updateRedniBrojevi() {
          if (stavke.get(0).getRedniBroj()!=1) {
                    stavke.get(0).setRedniBroj(1);
                }    
        for (int i = 0; i < stavke.size()-1; i++) {
               
                if (stavke.get(i).getRedniBroj()!=stavke.get(i+1).getRedniBroj()+1) {
                       
            stavke.get(i+1).setRedniBroj(stavke.get(i).getRedniBroj()+1);
        }  
    }
    }
        
        

    public void ubaciINapraviNovuStavkuPlana(Vezba vezba) {
        if (planVezbanja != null) {
            StavkaPlanaVezbanja stavkaPlanaVezbanja = new StavkaPlanaVezbanja();
            stavkaPlanaVezbanja.setiDVezba(vezba);
            stavkaPlanaVezbanja.setiDPlanVezbanja(planVezbanja);
            int poslednjiRedniBroj;
            if (!stavke.isEmpty()) {
                poslednjiRedniBroj = stavke.get(stavke.size()-1).getRedniBroj();
            } else { poslednjiRedniBroj=0;}
            
            
            stavkaPlanaVezbanja.setRedniBroj(poslednjiRedniBroj + 1);
            stavke.add(stavkaPlanaVezbanja);
        }
    }

        public void PoredjajStavkePoRednomBroju() {
            for (int i = 0; i < stavke.size(); i++) {
            for (int j = i+1; j < stavke.size(); j++) {
                if (stavke.get(i).getRedniBroj()>stavke.get(j).getRedniBroj()) {
                    Collections.swap(stavke, i, j);    
                }
            }
        }  
    }
    
    public void zameniDveStavke(int IndeksPrveStavke, int IndexDrugeStavke) {
        int prvaStavkaRedniBroj = stavke.get(IndeksPrveStavke).getRedniBroj();
        int drugaStavkaRedniBroj = stavke.get(IndexDrugeStavke).getRedniBroj();
        stavke.get(IndeksPrveStavke).setRedniBroj(drugaStavkaRedniBroj);
        stavke.get(IndexDrugeStavke).setRedniBroj(prvaStavkaRedniBroj);
        
      //  Collections.swap(stavke, IndexDrugeStavke, IndexDrugeStavke);
    }



    @Override
    public String getColumnName(int i) {
        return columnNames[i];
    }

    @Override
    public int getRowCount() {
        return stavke.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int x, int y) {
        StavkaPlanaVezbanja s = stavke.get(x);
        switch (y) {
            case 0:
                return s.getRedniBroj();
            case 1:
                return s.getiDVezba().getNaziv();
            case 2:
                return s.getiDVezba().getTezinaVezbe();

            default:
                return "n/a";
        }
    }

    public StavkaPlanaVezbanja getStavkaAt(int rowIndex) {
        return stavke.get(rowIndex);
    }

}
