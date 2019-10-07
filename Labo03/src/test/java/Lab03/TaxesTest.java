package Lab03;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxesTest {

    boolean thrown;

    @Before
    public void setUp() throws Exception{
        thrown = false;
    }

    @After
    public void tearDown() throws Exception{

    }

    @Test
    public void ajouterTaxes() throws IllegalArgumentException{
        long exp = 114;
        long actual = Taxes.ajouterTaxes(100, true, true);
        assertEquals(exp, actual);
    }

    @Test
    public void ajouterTaxes2() throws IllegalArgumentException{
        assertEquals(105, Taxes.ajouterTaxes(100, true, false));
    }

    @Test
    public void ajouterTaxes3() throws IllegalArgumentException{
        assertNotEquals(109, Taxes.ajouterTaxes(100, true, true));
    }

    @Test
    public void ajouterTaxes4(){
        try{
            Taxes.ajouterTaxes(0, false, true);
        } catch (java.lang.IllegalArgumentException e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void ajouterTaxes5(){
        try{
            Taxes.ajouterTaxes(-12, false, true);
        } catch (java.lang.IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }


}