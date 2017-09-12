/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teszt;

import gyak2.PTEsResztvevo;
import gyak2.Rendezveny;
import gyak2.Resztvevo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hallgato
 */
public class JubileumTeszt {

    private final String CIM = "cim";
    private final String IDOPONT = "dátum";
    private final int JEGY_AR = 1000;
    private final double KEDVEZMENY_SZAZALEK = 10;
    
    public JubileumTeszt() {
    }
    
    private Rendezveny rendezveny;
    @Before
    public void setUp() {
        rendezveny = new Rendezveny(CIM, IDOPONT, JEGY_AR);
        PTEsResztvevo.setKedvezmenySzazalek(KEDVEZMENY_SZAZALEK);
    }

     @Test
     public void rendezvenyTeszt() {
         // feltétel: nincs résztvevő, nincs bevétel
         assertTrue(rendezveny.getResztvevokSzama() == 0);
         assertEquals(rendezveny.getBevetel(), 0);
         
         Resztvevo resztvevo = new Resztvevo("nev");
         resztvevo.penztKap(1500);
         
         rendezveny.resztVesz(resztvevo);
         
         // feltételezés: már nem igaz, hogy nincs bevétel
         assertFalse(rendezveny.getBevetel() == 0);
         
         // feltételezés: 1 résztvevő van, bevétel 1000 Ft
         assertTrue(rendezveny.getResztvevokSzama() == 1);
         assertTrue(rendezveny.getBevetel() == JEGY_AR);
         
         // feltétel: még egyszer nem tud részt venni.
         rendezveny.resztVesz(resztvevo);
         assertFalse(rendezveny.getResztvevokSzama() == 2);
         
         resztvevo = new PTEsResztvevo("nev", "azonosito");
         resztvevo.penztKap(900);
         
         rendezveny.resztVesz(resztvevo);
         
         // feltételezés: részt tud venni
         assertTrue(rendezveny.getResztvevokSzama() == 2);
         assertEquals(rendezveny.getBevetel(), 1900);
     }
     
     @Test
     public void ResztvevoTeszt(){
         Resztvevo resztvevo = new Resztvevo("nev");
         Rendezveny rendezveny1 = new Rendezveny("cim1", IDOPONT, JEGY_AR);
         Rendezveny rendezveny2 = new Rendezveny("cim2", IDOPONT, JEGY_AR);
         
         resztvevo.penztKap(1500);
         
         // feltételezés: részt tud venni az elsőn
         rendezveny1.resztVesz(resztvevo);
         
         assertTrue(rendezveny1.getResztvevokSzama() == 1);
         assertTrue(resztvevo.getRendezvenyek().size() == 1);
         
         resztvevo.penztKap(JEGY_AR);
         
         // feltételezés: be tud lépni a másodikra
         assertTrue(resztvevo.belephet(rendezveny2));
         
         // feltételezés: két rendezvényen vett részt
         
         rendezveny2.resztVesz(resztvevo);
         assertTrue(resztvevo.getRendezvenyek().size() == 2);
         
         // felt.: a 2. rendezvényen van résztvevő
         
         assertTrue(rendezveny2.getResztvevokSzama() == 1);
     }
}









