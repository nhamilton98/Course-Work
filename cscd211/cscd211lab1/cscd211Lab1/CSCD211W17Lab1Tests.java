package cscd211Lab1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;

import cscd211Classes.Sandwich;

public class CSCD211W17Lab1Tests
{
	private String[] array = { "Turkey", "Ceddar Cheese" };
	private Sandwich basic = new Sandwich("Turkey", 100, this.array);

    @Before
    public void setUp() throws Exception
    {
        /* Empty setUp */
    }

    @Test
    public void testSimpleSandwichCalories()
    {
        assertEquals(100, this.basic.getCalories());
    }

    @Test
    public void testSimpleSandwichToppings()
    {
        assertNotSame(this.array, this.basic.getToppings());
    }

}
