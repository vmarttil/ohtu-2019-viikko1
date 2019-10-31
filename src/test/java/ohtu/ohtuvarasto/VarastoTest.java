package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisenTyhjanVarastonLuontiEiOnnistu() {
        Varasto nollaVarasto = new Varasto(-10);
        // tilavuuden pitäisi olla 0
        assertEquals(0, nollaVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisenTaydenVarastonLuontiEiOnnistu() {
        Varasto nollaVarasto = new Varasto(-10, 10);
        // tilavuuden pitäisi olla 0
        assertEquals(0, nollaVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void puolitaydenVarastonLuontiOnnistuu() {
        Varasto puolitaysiVarasto = new Varasto(10, 5);
        // saldon pitäisi olla 5
        assertEquals(5, puolitaysiVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void taydenVarastonLuontiOnnistuu() {
    	Varasto taysiVarasto = new Varasto(10, 10);
        // saldon pitäisi olla 10
        assertEquals(10, taysiVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ylitaydenVarastonLuontiOnnistuu() {
    	Varasto ylitaysiVarasto = new Varasto(10, 15);
        // saldon pitäisi olla 10
        assertEquals(10, ylitaysiVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alityhjanVarastonLuontiOnnistuu() {
    	Varasto ylitaysiVarasto = new Varasto(10, -5);
        // saldon pitäisi olla 0
        assertEquals(0, ylitaysiVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiTeeMitaan() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-5);
        // saldon pitäisi olla edelleen 5
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisattyYlimaaraMeneeHukkaan() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(10);
        // saldon pitäisi olla 10
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenEiTeeMitaan() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(-2);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void liikaaOttaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(12);
        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void liikaaOttaminenEiLisääLiikaaTilaa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(12);
        // varastossa pitäisi olla tilaa 10 - 8 + 12 > 10, eli 10
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void tilanteenTulostus() {
        varasto.lisaaVarastoon(8);
        // merkkijonoesityksen pitäisi kertoa, että saldo on 8 ja vielä mahtuu 2
        assertEquals("saldo = 8.0, vielä tilaa 2.0", varasto.toString());
    }
    
    
}