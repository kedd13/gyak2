/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gyak2;

/**
 *
 * @author :)
 */
public class Rendezveny {
    
    private String cim;
    private String idoPont;
    private int jegyAr;
    private int resztvevokSzama;
    private int bevetel;

    public Rendezveny(String cim, String idoPont, int jegyAr) {
        this.cim = cim;
        this.idoPont = idoPont;
        this.jegyAr = jegyAr;
    }
    
    /**
     * A résztvevő részt vesz.
     * @param resztvevo 
     */
    public void resztVesz(Resztvevo resztvevo){
        if(resztvevo.belephet(this)){
            resztvevo.fizet(resztvevo.reszveteliDij(this));
            resztvevo.resztVesz(this);
            resztvevokSzama++;
            bevetel += resztvevo.reszveteliDij(this);
        }
    }

    @Override
    public String toString() {
        return cim + ", időpontja: " + idoPont + ", jegyár: " + jegyAr;
    }    

    public String getCim() {
        return cim;
    }

    public String getIdoPont() {
        return idoPont;
    }

    public int getJegyAr() {
        return jegyAr;
    }

    public int getResztvevokSzama() {
        return resztvevokSzama;
    }

    public int getBevetel() {
        return bevetel;
    }

    public void setIdoPont(String idoPont) {
        this.idoPont = idoPont;
    }

    public void setJegyAr(int jegyAr) {
        this.jegyAr = jegyAr;
    }    
}
