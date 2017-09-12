/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gyak2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author :)
 */
public class Resztvevo {
    
    private String nev;
    private int penz;
    private List<Rendezveny> rendezvenyek = new ArrayList<>();

    public Resztvevo(String nev) {
        this.nev = nev;
    }
    
    public boolean belephet(Rendezveny rendezveny){
        int reszveteliDij = this.reszveteliDij(rendezveny);
        return reszveteliDij <= penz;
    }

    protected int reszveteliDij(Rendezveny rendezveny) {
        return rendezveny.getJegyAr();
    }    
    
    public void fizet(int reszveteliDij) {
        if(this.penz >= reszveteliDij) this.penz -= reszveteliDij;
    }

    public void penztKap(int penz) {
        this.penz += penz;
    }

    void resztVesz(Rendezveny rendezveny) {
        rendezvenyek.add(rendezveny);
    }    
    @Override
    public String toString() {
        return nev;
    }
    
    public String getNev() {
        return nev;
    }

    public List<Rendezveny> getRendezvenyek() {
        return new ArrayList<>(rendezvenyek);
    }



}
