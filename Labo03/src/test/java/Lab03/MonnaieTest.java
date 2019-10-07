package Lab03;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonnaieTest {

    Monnaie m1;
    Monnaie m2;
    Monnaie m3;

    @BeforeEach
    public void setUp() throws Exception{
        m1 = new Monnaie (25,43);
        m2 = new Monnaie(17,35);
        m3 = new Monnaie(234, 56);
    }

    @AfterEach
    public void tearDown() throws Exception{
        m1 = null;
        m2 = null;
        m3 = null;
    }


    @Test
    void getDollars() {
        assertEquals(25, m1.getDollars());
        System.out.println(m1.getDollars());
        assertEquals(234, m3.getDollars());
        assertFalse(2 == m3.getDollars());
    }

    @Test
    void getcents() {
        assertEquals(56, m3.getCents());
        assertEquals(35, m2.getCents());
        assertFalse(2 == m3.getCents());
    }

    @Test
    void getTotalCents() {
        assertEquals(23456, m3.getTotalCents());
        assertEquals(2543, m1.getTotalCents());
        assertEquals(1735, m2.getTotalCents());
        assertNotEquals(35, m1.getTotalCents());
    }

    @Test
    void additioner() {
        assertEquals(2543, m1.getTotalCents());
        m1.additioner(m2);
        assertNotEquals(2543, m1.getTotalCents());
        assertEquals(4278, m1.getTotalCents());
    }

    @Test
    void convertir() {
        assertNotNull(m3);
        assertTrue("234.56$".equals(m3.convertir()));
        assertFalse("2.56$".equals(m3.convertir()));
    }

    @Test
    void pourcentage() {
        assertTrue("234.56$".equals(m3.convertir()));
        m3.pourcentage(50);
        assertFalse("234.56$".equals(m3.convertir()));
        assertTrue("117.28$".equals(m3.convertir()));
        assertEquals(11728, m3.getTotalCents());
    }

    @Test
    void soustraire() throws OperationNonValideException {
        assertEquals(2543, m1.getTotalCents());
        m1.soustraire(m2);
        assertNotEquals(2543, m1.getTotalCents());
        assertEquals(808, m1.getTotalCents());

    }
}