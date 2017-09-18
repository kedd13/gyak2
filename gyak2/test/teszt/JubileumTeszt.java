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
 * @author :)
 */
public class JubileumTeszt {

    private final String CIM = "cim";
    private final String IDOPONT = "dátum";
    private final int JEGY_AR = 1000;
    private final String NEV = "nev";
    private final double KEDVEZMENY_SZAZALEK = 10;

    public JubileumTeszt() {
    }

    private Rendezveny rendezveny;
    private Resztvevo resztvevo;

    @Before
    public void setUp() {
        rendezveny = new Rendezveny(CIM, IDOPONT, JEGY_AR);
        resztvevo = new Resztvevo(NEV);
        PTEsResztvevo.setKedvezmenySzazalek(KEDVEZMENY_SZAZALEK);
    }

    @Test
    public void rendezvenyTeszt() {
        // feltételezés: nincs senki és nincs bevétel
        assertTrue(rendezveny.getResztvevokSzama() == 0);
        assertEquals(rendezveny.getBevetel(), 0);

        resztvevo.penztKap(1500);
        rendezveny.resztVesz(resztvevo);

        // feltételezés: nem igaz, hogy nincs bevétel
        assertFalse(rendezveny.getBevetel() == 0);

        // feltételezés: részt tud venni, vagyis van már résztvevő 
        // és van bevétel
        // (ha egyenlőséget vizsgálunk, akkor az assertEquals és az 
        // assertTrue is jó.
        
        assertEquals(rendezveny.getResztvevokSzama(), 1);
        assertTrue(rendezveny.getBevetel() == JEGY_AR);

        // feltételezés: nem tud még egyszer részt venni, mert nincs elég pénze
        rendezveny.resztVesz(resztvevo);

        assertFalse(rendezveny.getResztvevokSzama() > 1);
        
        // Eddig csak annyit láttunk, hogy ha egy valaki részt tud venni, 
        // akkor helyes a résztvevőszám és a bevétel. 
        // Azt, hogy nem csak sima értékadásról, hanem összegzésről van szó,
        // csak akkor látjuk, ha még egy résztvevő is részt vesz a rendezvényen.
        // De mivel a kedvezményt is szeretnénk ellenőrizni, ezért legyen most
        // a résztvevő egy PTE polgár. 
        // Mivel a korábbi résztvevőre már nincs szükségünk ebben a metódusban,
        // nyugodtan felülírhatjuk. 
        // Mivel csak lokálisan használjuk, akár be is égethetjük az adatait.

        resztvevo = new PTEsResztvevo("nev", "azonosito");

        resztvevo.penztKap(900);
        rendezveny.resztVesz(resztvevo);

        // feltételezés: már ketten vannak és nőtt a bevétel
        assertEquals(rendezveny.getResztvevokSzama(), 2);
        assertTrue(rendezveny.getBevetel() == 1900);
    }

    @Test
    public void resztvevoTeszt() {
        // Most azt akarjuk ellenőrizni, hogy a résztvevőhöz tartozó 
        // rendezvények listája helyesen bővül-e, ezért kell még egy rendezvény.
        // Ezt elég lokálisan megadni
        
        Rendezveny rendezveny2 = new Rendezveny("cim2", IDOPONT, JEGY_AR);

        resztvevo.penztKap(JEGY_AR);

        // Látható, hogy a rendezvényen ismét nincs senki, mert lefutott a 
        // @Before annotációjú metódus.
        
        assertTrue(rendezveny.getResztvevokSzama() == 0);

        // feltételezés: a résztvevő még nem járt egyetlen rendezvényen sem
        
        assertTrue(resztvevo.getRendezvenyek().isEmpty());

        rendezveny.resztVesz(resztvevo);

        // feltételezés: részt vett a rendezvényen és az általa látogatott
        // rendezvények listája is bővült. 
        // Arról még nem volt szó tisztességesen, hogy azt is ellenőrizni 
        // tudjuk, hogy a konkrét rendezvény szerepel-e a listán - ezt majd 
        // később próbálja ki önállóan, ha már tanulta a Contains() metódus 
        // pontos használatát.
        // Most csak annyit feltételezünk, hogy a listában van már egy elem.
        
        assertEquals(rendezveny.getResztvevokSzama(), 1);
        assertTrue(resztvevo.getRendezvenyek().size() == 1);

        resztvevo.penztKap(JEGY_AR);

        // feltételezés: be tud menni a második rendezvényre is.
        
        assertTrue(resztvevo.belephet(rendezveny2));

        // feltételezés: bővült azoknak a rendezvényeknek a listája, amelyeken
        // részt vett,
        // és a 2. rendezvényen már van 1 résztvevő.

//        assertTrue(resztvevo.getRendezvenyek().size() == 2);
//        assertEquals(rendezveny2.getResztvevokSzama(), 1);

        // a kód javítása utáni feltételezések:
        
        rendezveny2.resztVesz(resztvevo);
        
        assertTrue(resztvevo.getRendezvenyek().size() == 2);
        assertEquals(rendezveny2.getResztvevokSzama(), 1);
    }
}

