/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gyak2;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author :)
 */
class Ablak extends JFrame{
    private int szelesseg;
    private int magassag;
    private String cim;

    public Ablak(int szelesseg, int magassag, String cim) {
        this.szelesseg = szelesseg;
        this.magassag = magassag;
        this.cim = cim;
        inicializalas(); 
    }

    private void inicializalas() {
        this.setSize(szelesseg, magassag);
        this.setTitle(cim);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    void ablakbaIr(List<Rendezveny> rendezvenyek, String[] oszlopNevek) {
        //  oszlopNevek = {"Cím","Időpont","Jegyár (Ft)","Résztvevőszám","Bevétel (Ft)"};
        String[][] adatok = new String[rendezvenyek.size()][oszlopNevek.length];
        for (int i = 0; i < rendezvenyek.size(); i++) {
            adatok[i][0] = rendezvenyek.get(i).getCim();
            adatok[i][1] = rendezvenyek.get(i).getIdoPont();
            adatok[i][2] = String.valueOf(rendezvenyek.get(i).getJegyAr());
            adatok[i][3] = String.valueOf(rendezvenyek.get(i).getResztvevokSzama());
            adatok[i][4] = String.valueOf(rendezvenyek.get(i).getBevetel());
        }
        
        TableModel tableModel = new DefaultTableModel(adatok, oszlopNevek);
        JTable tabla = new JTable(tableModel);
        JScrollPane jScrollPane = new JScrollPane(tabla);
        
        this.add(jScrollPane);
        this.revalidate();
    }
    
    
}
