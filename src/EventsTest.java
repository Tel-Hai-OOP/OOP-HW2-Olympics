import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the Events class for proper behavior
 *
 * @author Michael J. May
 * @version 1.0
 */
public class EventsTest {

    @Test
    public void getEquestrianEvents() {
        assertEquals("Dressage", Events.getEquestrianEvents()[0]);
        assertEquals("Eventing", Events.getEquestrianEvents()[1]);
        assertEquals("Jumping", Events.getEquestrianEvents()[2]);
    }

    @Test
    public void getMaleGymnastEvents() {
        assertEquals("All_Around", Events.getMaleGymnastEvents()[0]);
        assertEquals("Floor_Exercise", Events.getMaleGymnastEvents()[1]);
        assertEquals("Horizontal_Bar", Events.getMaleGymnastEvents()[2]);
        assertEquals("Parallel_Bars", Events.getMaleGymnastEvents()[3]);
        assertEquals("Pommel_Horse", Events.getMaleGymnastEvents()[4]);
        assertEquals("Rings", Events.getMaleGymnastEvents()[5]);
        assertEquals("Vault", Events.getMaleGymnastEvents()[6]);
    }

    @Test
    public void getFemaleGymnastEvents() {
        assertEquals("All_Around", Events.getFemaleGymnastEvents()[0]);
        assertEquals("Balance_Beam", Events.getFemaleGymnastEvents()[1]);
        assertEquals("Floor_Exercise", Events.getFemaleGymnastEvents()[2]);
        assertEquals("Uneven_Bars", Events.getFemaleGymnastEvents()[3]);
        assertEquals("Vault", Events.getFemaleGymnastEvents()[4]);
    }}