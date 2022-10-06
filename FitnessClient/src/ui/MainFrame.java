/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import communication.CommunicationController;
import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import domain.PlanVezbanja;
import domain.StavkaPlanaVezbanja;
import domain.TezinaNaDan;
import domain.TezinaVezbe;
import domain.Vezba;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Marija
 */
public class MainFrame extends javax.swing.JFrame {

    private Korisnik ucitanKorisnik;
    private List<TezinaNaDan> ucitanaIstorijaTezine;
    private List<PlanVezbanja> ucitaniPlanoviKorisnika;
    private int ucitaniPlanIndex;

    public Korisnik getUcitanKorisnik() {
        return ucitanKorisnik;
    }

    public void setUcitanKorisnik(Korisnik ucitanKorisnik) {
        this.ucitanKorisnik = ucitanKorisnik;
    }

    public List<TezinaNaDan> getUcitanaIstorijaTezine() {
        return ucitanaIstorijaTezine;
    }

    public void setUcitanaIstorijaTezine(List<TezinaNaDan> ucitanaIstorijaTezine) {
        this.ucitanaIstorijaTezine = ucitanaIstorijaTezine;
    }

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();

        prepareView();

    }

    private void ucitajPlanoveZaKorisnika() {
        try {
            ucitaniPlanoviKorisnika = CommunicationController.getInstance().searchPlanVezbanja(new PlanVezbanja(), CommunicationController.getInstance().getLoggedUser().getID());
            
            
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Sistem ne može dа nаđe izabran plan: " +ex, "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ucitajPlanIPrikaziNaziv(int index) {
        try {
            ucitaniPlanIndex = index;
            planVezbanjaNazivJTextField.setText(ucitaniPlanoviKorisnika.get(index).getNaziv());
            List<OpstiDomenskiObjekat> listObjekata = CommunicationController.getInstance().getPlanSaVezbama(ucitaniPlanoviKorisnika.get(index));
            List<StavkaPlanaVezbanja> listaStavki = new ArrayList<StavkaPlanaVezbanja>();
            for (int i = 1; i < listObjekata.size(); i++) {
                listaStavki.add((StavkaPlanaVezbanja)listObjekata.get(i));
            }
            
            TableModelPlanVezbe model = new TableModelPlanVezbe(listaStavki, (PlanVezbanja) listObjekata.get(0));
            
            planJTable.setModel(model);
            model.PoredjajStavkePoRednomBroju();
            model.updateRedniBrojevi();
            model.fireTableDataChanged();
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void prepareView() {
        try {

            SearchVezbeAndUpdateTable();

            Korisnik k = (Korisnik) CommunicationController.getInstance().getLoggedUser();
            List<OpstiDomenskiObjekat> lista = CommunicationController.getInstance().getKorisnikSaTezinama(k);
            Korisnik korisnik = (Korisnik) lista.get(0);
            setUcitanaIstorijaTezine(new ArrayList<TezinaNaDan>());

            for (int i = 1; i < lista.size(); i++) {
                ucitanaIstorijaTezine.add((TezinaNaDan) lista.get(i));
            }
            setUcitanKorisnik(korisnik);

            ucitajPlanoveZaKorisnika();
            ucitajPlanIPrikaziNaziv(0);

            TableModelTezinaNaDan model = new TableModelTezinaNaDan(ucitanaIstorijaTezine);
            model.poredjajTezinePoDatumima();
            istorijatTezineJTable.setModel(model);

        } catch (Exception ex) {
            Logger.getLogger(RadSaKorisnicimaJFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void SearchVezbeAndUpdateTable() {
        List<Vezba> listaVezbi = new ArrayList<>();
        try {
            listaVezbi = CommunicationController.getInstance().searchVezba(new Vezba(), pretragaVezbiJTextField.getText());
        } catch (Exception ex) {
            Logger.getLogger(RadSaKorisnicimaJFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Sistem ne može dа nаđe vežbu po zаdаtoj vrednosti: " +ex.getMessage(), "Greska", JOptionPane.WARNING_MESSAGE);
        }
        TableModelVezba model = new TableModelVezba(listaVezbi);
        vezbeJTable.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        planJTable = new javax.swing.JTable();
        planVezbanjaNazivJTextField = new javax.swing.JTextField();
        nextPlanVezbanjaButton = new javax.swing.JButton();
        previousPlanVezbanjaButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        istorijatTezineJTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        datumJTextField = new javax.swing.JTextField();
        tezinaJTextField = new javax.swing.JTextField();
        dodajJButton = new javax.swing.JButton();
        obrisiJButton = new javax.swing.JButton();
        azurirajTezineJButton = new javax.swing.JButton();
        dodajVezbuJButton = new javax.swing.JButton();
        obrisiVezbuJButton = new javax.swing.JButton();
        upJButton = new javax.swing.JButton();
        downJButton = new javax.swing.JButton();
        sacuvajPlanoveJButton = new javax.swing.JButton();
        obrisiPlanJButton = new javax.swing.JButton();
        noviPlanJButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        vezbeJTable = new javax.swing.JTable();
        pretragaVezbiJTextField = new javax.swing.JTextField();
        pretraziVezbeJButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        imageJLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemKorisnici = new javax.swing.JMenuItem();
        jMenuItemVezbe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Korisnicki Panel"));

        planJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Stavka broj", "Naziv", "Tezina Vezbe"
            }
        ));
        jScrollPane1.setViewportView(planJTable);

        nextPlanVezbanjaButton.setText(">>");
        nextPlanVezbanjaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextPlanVezbanjaButtonActionPerformed(evt);
            }
        });

        previousPlanVezbanjaButton.setText("<<");
        previousPlanVezbanjaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousPlanVezbanjaButtonActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Istorijat Tezine"));

        istorijatTezineJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Datum", "Tezina"
            }
        ));
        jScrollPane2.setViewportView(istorijatTezineJTable);

        jLabel1.setText("Datum (yyyy.MM.dd)");

        jLabel2.setText("Tezina:");

        dodajJButton.setText("Dodaj");
        dodajJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajJButtonActionPerformed(evt);
            }
        });

        obrisiJButton.setText("Obrisi");
        obrisiJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obrisiJButtonActionPerformed(evt);
            }
        });

        azurirajTezineJButton.setText("Azuriraj Tezine");
        azurirajTezineJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                azurirajTezineJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(datumJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                    .addComponent(tezinaJTextField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dodajJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(obrisiJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(azurirajTezineJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datumJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(dodajJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tezinaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(obrisiJButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(azurirajTezineJButton)
                .addGap(13, 13, 13))
        );

        dodajVezbuJButton.setText("Dodaj Vezbu");
        dodajVezbuJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajVezbuJButtonActionPerformed(evt);
            }
        });

        obrisiVezbuJButton.setText("Obrisi Vezbu");
        obrisiVezbuJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obrisiVezbuJButtonActionPerformed(evt);
            }
        });

        upJButton.setText("▲");
        upJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upJButtonActionPerformed(evt);
            }
        });

        downJButton.setText("▼");
        downJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downJButtonActionPerformed(evt);
            }
        });

        sacuvajPlanoveJButton.setText("Azururaj Plan");
        sacuvajPlanoveJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sacuvajPlanoveJButtonActionPerformed(evt);
            }
        });

        obrisiPlanJButton.setText("Obrisi Plan");
        obrisiPlanJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obrisiPlanJButtonActionPerformed(evt);
            }
        });

        noviPlanJButton.setText("Novi Plan");
        noviPlanJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noviPlanJButtonActionPerformed(evt);
            }
        });

        vezbeJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        vezbeJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vezbeJTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(vezbeJTable);

        pretraziVezbeJButton.setText("Pretrazi Vezbe");
        pretraziVezbeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pretraziVezbeJButtonActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(imageJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(imageJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(previousPlanVezbanjaButton)
                        .addGap(30, 30, 30)
                        .addComponent(planVezbanjaNazivJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nextPlanVezbanjaButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(noviPlanJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sacuvajPlanoveJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(obrisiPlanJButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pretragaVezbiJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pretraziVezbeJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(dodajVezbuJButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(obrisiVezbuJButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(upJButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(downJButton))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(266, 266, 266))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noviPlanJButton)
                            .addComponent(sacuvajPlanoveJButton)
                            .addComponent(obrisiPlanJButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nextPlanVezbanjaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(planVezbanjaNazivJTextField))
                            .addComponent(previousPlanVezbanjaButton))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dodajVezbuJButton)
                        .addComponent(obrisiVezbuJButton)
                        .addComponent(upJButton)
                        .addComponent(downJButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 36, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(18, 30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pretragaVezbiJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pretraziVezbeJButton))
                .addContainerGap())
        );

        jMenu1.setText("File");

        jMenuItemKorisnici.setText("Korisnici");
        jMenuItemKorisnici.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKorisniciActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemKorisnici);

        jMenuItemVezbe.setText("Vezbe");
        jMenuItemVezbe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVezbeActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemVezbe);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemKorisniciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKorisniciActionPerformed
        JFrame f = new RadSaKorisnicimaJFrame();
        try {
            f.setTitle("User: " + CommunicationController.getInstance().getLoggedUser().getIme() + " " + CommunicationController.getInstance().getLoggedUser().getPrezime());
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        f.setVisible(true);
    }//GEN-LAST:event_jMenuItemKorisniciActionPerformed

    private void jMenuItemVezbeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVezbeActionPerformed
        JFrame f = new RadSaVezbamaJFrame();
        try {
            f.setTitle("User: " + CommunicationController.getInstance().getLoggedUser().getIme() + " " + CommunicationController.getInstance().getLoggedUser().getPrezime());
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        f.setVisible(true);
    }//GEN-LAST:event_jMenuItemVezbeActionPerformed

    private void azurirajTezineJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_azurirajTezineJButtonActionPerformed
        try {

            Korisnik k = CommunicationController.getInstance().getLoggedUser();

            TableModelTezinaNaDan tableModelTezinaNaDan = (TableModelTezinaNaDan) istorijatTezineJTable.getModel();

            CommunicationController.getInstance().updateKorisnik(k, tableModelTezinaNaDan.getTezineNaDan());
            JOptionPane.showMessageDialog(this, "Korisnik updateovan", "Obavestenje", JOptionPane.PLAIN_MESSAGE);
            prepareView();
        } catch (Exception ex) {
            Logger.getLogger(RadSaKorisnicimaDetailsJPanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex, "Greska", JOptionPane.ERROR_MESSAGE);
            prepareView();
        }
   
    }//GEN-LAST:event_azurirajTezineJButtonActionPerformed

    private void pretraziVezbeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pretraziVezbeJButtonActionPerformed
        SearchVezbeAndUpdateTable();
    }//GEN-LAST:event_pretraziVezbeJButtonActionPerformed

    private void vezbeJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vezbeJTableMouseClicked
        int selectedRow = vezbeJTable.getSelectedRow();
        if (selectedRow != -1) {
            try {
                TableModelVezba model = (TableModelVezba) vezbeJTable.getModel();
                Vezba vezba = model.getVezbaAt(selectedRow);

                BufferedImage myPicture = ImageIO.read(new File(vezba.getPutDoFajla()));
                ImageIcon ii = new ImageIcon(myPicture);
                imageJLabel.setIcon(ii);

            } catch (IIOException ex) {
                Logger.getLogger(RadSaVezbamaDetailsJPanel.class.getName()).log(Level.SEVERE, null, ex);

                try {
                    BufferedImage myPicture = ImageIO.read(new File("files\\noImage.jpg"));
                    ImageIcon ii = new ImageIcon(myPicture);
                    imageJLabel.setIcon(ii);
                } catch (IOException ex1) {
                    Logger.getLogger(RadSaVezbamaDetailsJPanel.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_vezbeJTableMouseClicked

    private void nextPlanVezbanjaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextPlanVezbanjaButtonActionPerformed
ucitajPlanoveZaKorisnika();
        if (ucitaniPlanIndex == ucitaniPlanoviKorisnika.size() - 1) {
            ucitaniPlanIndex = 0;
        } else {
            ucitaniPlanIndex = ucitaniPlanIndex + 1;
        }
        ucitajPlanIPrikaziNaziv(ucitaniPlanIndex);
       
    }//GEN-LAST:event_nextPlanVezbanjaButtonActionPerformed

    private void previousPlanVezbanjaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousPlanVezbanjaButtonActionPerformed
        ucitajPlanoveZaKorisnika();
        if (ucitaniPlanIndex == 0) {
            ucitaniPlanIndex = ucitaniPlanoviKorisnika.size() - 1;
        } else {
            ucitaniPlanIndex = ucitaniPlanIndex - 1;
        }
        ucitajPlanIPrikaziNaziv(ucitaniPlanIndex);
    }//GEN-LAST:event_previousPlanVezbanjaButtonActionPerformed

    private void dodajJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajJButtonActionPerformed
        try {
            TableModelTezinaNaDan model = (TableModelTezinaNaDan) istorijatTezineJTable.getModel();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            TezinaNaDan t = new TezinaNaDan(sdf.parse(datumJTextField.getText()), Double.parseDouble(tezinaJTextField.getText()), ucitanKorisnik);

            model.addTezinaNaDan(t);
            model.poredjajTezinePoDatumima();
            model.fireTableDataChanged();
        } catch (ParseException ex) {
            Logger.getLogger(RadSaKorisnicimaDetailsJPanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex, "Greska u parsiranju", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(RadSaKorisnicimaDetailsJPanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex, "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_dodajJButtonActionPerformed

    private void obrisiJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obrisiJButtonActionPerformed
        int selectedRow = istorijatTezineJTable.getSelectedRow();
        if (selectedRow != -1) {
            TableModelTezinaNaDan model = (TableModelTezinaNaDan) istorijatTezineJTable.getModel();
            model.removeTezinaNaDanAt(selectedRow);
            model.fireTableDataChanged();

        }
    }//GEN-LAST:event_obrisiJButtonActionPerformed

    private void dodajVezbuJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajVezbuJButtonActionPerformed
        if (vezbeJTable.getSelectedRow() != -1) {
            TableModelPlanVezbe model1 = (TableModelPlanVezbe) planJTable.getModel();
            TableModelVezba model2 = (TableModelVezba) vezbeJTable.getModel();
            
            model1.ubaciINapraviNovuStavkuPlana(model2.getVezbaAt(vezbeJTable.getSelectedRow()));
            model1.fireTableDataChanged();
            model2.fireTableDataChanged();
        }
    }//GEN-LAST:event_dodajVezbuJButtonActionPerformed

    private void upJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upJButtonActionPerformed
        TableModelPlanVezbe model = (TableModelPlanVezbe) planJTable.getModel();
        model.zameniDveStavke(planJTable.getSelectedRow(), planJTable.getSelectedRow()-1);
        model.PoredjajStavkePoRednomBroju();
        model.fireTableDataChanged();
    }//GEN-LAST:event_upJButtonActionPerformed

    private void downJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downJButtonActionPerformed
        TableModelPlanVezbe model = (TableModelPlanVezbe) planJTable.getModel();
        model.zameniDveStavke(planJTable.getSelectedRow(), planJTable.getSelectedRow()+1);
        model.PoredjajStavkePoRednomBroju();
        model.fireTableDataChanged();
        
    }//GEN-LAST:event_downJButtonActionPerformed

    private void obrisiVezbuJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obrisiVezbuJButtonActionPerformed
        TableModelPlanVezbe model = (TableModelPlanVezbe) planJTable.getModel();
        model.removeStavkuPlana(planJTable.getSelectedRow());
        model.fireTableDataChanged();
    }//GEN-LAST:event_obrisiVezbuJButtonActionPerformed

    private void obrisiPlanJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obrisiPlanJButtonActionPerformed

        try {
            CommunicationController.getInstance().deletePlanVezbanja(ucitaniPlanoviKorisnika.get(ucitaniPlanIndex));
            JOptionPane.showMessageDialog(this, "Plan uspesno obrisan", "Obavestenje", JOptionPane.PLAIN_MESSAGE);
            ucitaniPlanIndex = ucitaniPlanIndex -1;
            
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Greska prilikom brisanja plana: " +ex, "Greska", JOptionPane.ERROR_MESSAGE);
        }

        ucitajPlanoveZaKorisnika();
        ucitajPlanIPrikaziNaziv(ucitaniPlanIndex);

    }//GEN-LAST:event_obrisiPlanJButtonActionPerformed

    private void sacuvajPlanoveJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sacuvajPlanoveJButtonActionPerformed
        try {
            TableModelPlanVezbe model = (TableModelPlanVezbe) planJTable.getModel();
            PlanVezbanja p = model.getPlanVezbanja();
            p.setNaziv(planVezbanjaNazivJTextField.getText());
            model.setPlanVezbanja(p);
            CommunicationController.getInstance().updatePlanVezbanja(model.getPlanVezbanja(), model.getStavke());
            JOptionPane.showMessageDialog(this, "Plan uspešno sačuvan", "Obavestenje", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Greska pri cuvanju plana: " +ex, "Greska", JOptionPane.ERROR_MESSAGE);
        }
        
        prepareView();

      //  ucitaniPlanIndex = ucitaniPlanoviKorisnika.size()-1;
      ucitaniPlanIndex = 0;  
      ucitajPlanIPrikaziNaziv(ucitaniPlanIndex);
    }//GEN-LAST:event_sacuvajPlanoveJButtonActionPerformed

    private void noviPlanJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noviPlanJButtonActionPerformed
        try {
            int i = ucitaniPlanoviKorisnika.size()+1;
            PlanVezbanja p = new PlanVezbanja("Plan "+i, CommunicationController.getInstance().getLoggedUser());
            CommunicationController.getInstance().savePlanVezbanja(p);
            JOptionPane.showMessageDialog(this, "Plan uspešno dodat", "Obavestenje", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Greska pri cuvanju plana: " +ex, "Greska", JOptionPane.ERROR_MESSAGE);
        }
        prepareView();
        ucitaniPlanIndex = ucitaniPlanoviKorisnika.size()-1;
        ucitajPlanIPrikaziNaziv(ucitaniPlanIndex);
        
    }//GEN-LAST:event_noviPlanJButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton azurirajTezineJButton;
    private javax.swing.JTextField datumJTextField;
    private javax.swing.JButton dodajJButton;
    private javax.swing.JButton dodajVezbuJButton;
    private javax.swing.JButton downJButton;
    private javax.swing.JLabel imageJLabel;
    private javax.swing.JTable istorijatTezineJTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemKorisnici;
    private javax.swing.JMenuItem jMenuItemVezbe;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton nextPlanVezbanjaButton;
    private javax.swing.JButton noviPlanJButton;
    private javax.swing.JButton obrisiJButton;
    private javax.swing.JButton obrisiPlanJButton;
    private javax.swing.JButton obrisiVezbuJButton;
    private javax.swing.JTable planJTable;
    private javax.swing.JTextField planVezbanjaNazivJTextField;
    private javax.swing.JTextField pretragaVezbiJTextField;
    private javax.swing.JButton pretraziVezbeJButton;
    private javax.swing.JButton previousPlanVezbanjaButton;
    private javax.swing.JButton sacuvajPlanoveJButton;
    private javax.swing.JTextField tezinaJTextField;
    private javax.swing.JButton upJButton;
    private javax.swing.JTable vezbeJTable;
    // End of variables declaration//GEN-END:variables
}
