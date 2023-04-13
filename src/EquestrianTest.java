import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class EquestrianTest {

    public static final String Equestrian_Regex = "\\w+\\s+birth year:\\s+\\d+\\s+nationality:\\s+\\w+\\s+height:\\s+\\d+\\s+Equestrian with scores:\\s+\\d+.\\d+\\s+\\d+.\\d+\\s+\\d+.\\d+\\s+\\d+.\\d+\\s+\\d+.\\d+\\s+for event\\s+\\w+\\s+with horse\\s+\\w+";

    Equestrian Edna, Fred, Ginny;

    @Before
    public void setUp() {
        Edna = new Equestrian(TestConstants.EDNA, TestConstants.EDNA_BIRTH_YEAR, TestConstants.EDNA_HEIGHT, TestConstants.ISRAEL, TestConstants.BUCEPHALUS);
        Fred = new Equestrian(TestConstants.FRED, TestConstants.FRED_BIRTH_YEAR, TestConstants.FRED_HEIGHT, TestConstants.USA, TestConstants.CALI);
        Ginny = new Equestrian(TestConstants.GINNY, TestConstants.GINNY_BIRTH_YEAR, TestConstants.GINNY_HEIGHT, TestConstants.CONGO, TestConstants.DENNYS);
    }

    @Test
    public void canCompete() {
        assertTrue(Edna.canCompete(Events.getEquestrianEvents()[0]));
        assertTrue(Edna.canCompete(Events.getEquestrianEvents()[1]));
        assertTrue(Edna.canCompete(Events.getEquestrianEvents()[2]));

        assertFalse(Edna.canCompete(Events.getFemaleGymnastEvents()[0]));
        assertFalse(Edna.canCompete(Events.getMaleGymnastEvents()[0]));
    }

    @Test
    public void getHorseName() {
        assertEquals(TestConstants.BUCEPHALUS, Edna.getHorseName());
        assertEquals(TestConstants.CALI, Fred.getHorseName());
        assertEquals(TestConstants.DENNYS, Ginny.getHorseName());
    }

    @Test
    public void setHorseName() {
        // set Edna
        Edna.setHorseName(TestConstants.CALI);
        assertEquals(TestConstants.CALI, Edna.getHorseName());
        assertEquals(TestConstants.CALI, Fred.getHorseName());
        assertEquals(TestConstants.DENNYS, Ginny.getHorseName());

        // set Fred
        Fred.setHorseName(TestConstants.BUCEPHALUS);
        assertEquals(TestConstants.CALI, Edna.getHorseName());
        assertEquals(TestConstants.BUCEPHALUS, Fred.getHorseName());
        assertEquals(TestConstants.DENNYS, Ginny.getHorseName());

        // set Ginny
        Ginny.setHorseName(TestConstants.FRED);
        assertEquals(TestConstants.CALI, Edna.getHorseName());
        assertEquals(TestConstants.BUCEPHALUS, Fred.getHorseName());
        assertEquals(TestConstants.FRED, Ginny.getHorseName());
    }

    @Test
    public void setScores() {
        String event = Events.getEquestrianEvents()[0];
        String badEvent = Events.getMaleGymnastEvents()[0];
        double[] scores1 = new double[] {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] scores2 = new double[] {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] tooShortScores = new double[] {10.0, 11.0, 12.0};
        double[] tooLongScores = new double[] {9.0, 10.0, 13.0, 12.0, 14.0, 15.0, 15.0};

        Edna.setScores(event, scores1);
        assertTrue(Arrays.equals(Edna.getScores(), scores1));

        // modify scores1
        scores1[0] = 10.0;

        // ensure Edna's scores are unchanged
        assertTrue(Arrays.equals(Edna.getScores(), scores2));

        // try to change to too short scores
        Edna.setScores(event, tooShortScores);

        // ensure they are unchanged
        assertTrue(Arrays.equals(Edna.getScores(), scores2));

        // too long scores
        Edna.setScores(event, tooLongScores);

        // ensure they are unchanged
        assertTrue(Arrays.equals(Edna.getScores(), scores2));

        // try to set to a bad event
        Edna.setScores(badEvent, scores1);

        // ensure they are unchanged
        assertTrue(Arrays.equals(Edna.getScores(), scores2));
    }

    @Test
    public void getScores() {
        String event = Events.getEquestrianEvents()[0];
        String badEvent = Events.getMaleGymnastEvents()[0];
        double[] scores1 = new double[] {1.0, 2.0, 3.0, 4.0, 5.0};

        assertTrue(Arrays.equals(Fred.getScores(), TestConstants.emptyScores));
        Fred.setScores(event, scores1);
        assertTrue(Arrays.equals(Fred.getScores(), scores1));
    }

    @Test
    public void testToString() {
        String ednaEmpty = Edna.toString();
        String fredEmpty = Fred.toString();
        assertTrue(ednaEmpty.matches(Equestrian_Regex));
        assertTrue(fredEmpty.matches(Equestrian_Regex));

        // add an event
        Edna.setEventScored(Events.getEquestrianEvents()[0]);
        assertTrue(Edna.toString().matches(Equestrian_Regex));

        // add scores
        Edna.setScores(Events.getEquestrianEvents()[1], new double[] {1.0, 2.0, 3.0, 4.0, 5.0});
        assertTrue(Edna.toString().matches(Equestrian_Regex));
        assertTrue(Fred.toString().matches(Equestrian_Regex));
    }

    @Test
    public void getPoints() {
        double[] scores1 = new double[] {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] scores2 = new double[] {-1.0, 2.0, 3.0, 4.0, 5.0};
        Edna.setScores(Events.getEquestrianEvents()[0], scores1);
        assertEquals(15.0,  Edna.getPoints(), TestConstants.DELTA);
        assertEquals(0, Fred.getPoints(), TestConstants.DELTA);
        Fred.setScores(Events.getEquestrianEvents()[1], scores2);
        assertEquals( 14.0, Fred.getPoints(), TestConstants.DELTA);
    }

    @Test
    public void getGoldMedalist() {
        double[] lowScores = new double[] {10.0, 11.0, 12.0, 13.0, 14.0};
        double[] highScores = new double[] {15.0, 11.0, 12.0, 13.0, 14.0};
        double[] mediumScores = new double[] {11.0, 11.0, 13.0, 13.0, 14.0};
        Edna.setScores(Events.getEquestrianEvents()[0], lowScores );
        Fred.setScores(Events.getEquestrianEvents()[0], mediumScores);
        Ginny.setScores(Events.getEquestrianEvents()[0], highScores);
        Equestrian[] equestrians = new Equestrian[] {Edna, Fred, Ginny};

        assertSame(Ginny, Equestrian.getGoldMedalist(Events.getEquestrianEvents()[0], equestrians));

        // check when there's a tie
        Edna.setScores(Events.getEquestrianEvents()[1], mediumScores);
        Fred.setScores(Events.getEquestrianEvents()[1], mediumScores);
        Ginny.setScores(Events.getEquestrianEvents()[1], highScores );

        assertSame(Ginny, Equestrian.getGoldMedalist(Events.getEquestrianEvents()[1], equestrians));

        // check when there's a tie at the top
        Edna.setScores(Events.getEquestrianEvents()[2], highScores);
        Fred.setScores(Events.getEquestrianEvents()[2], mediumScores);
        Ginny.setScores(Events.getEquestrianEvents()[2], highScores );

        assertSame(Edna, Equestrian.getGoldMedalist(Events.getEquestrianEvents()[2], equestrians));
    }
}