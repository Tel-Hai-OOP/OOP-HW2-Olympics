import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.DoubleStream;

import static org.junit.Assert.*;

public class GymnastTest {
    public static final String Gymnast_Regex = "\\w+\\s+birth year:\\s+\\d+\\s+nationality:\\s+\\w+\\s+height:\\s+\\d+\\s+Gymnast with difficulty scores:\\s+\\d+.\\d+\\s+\\d+.\\d+\\s+and execution scores:\\s+\\d+.\\d+\\s+\\d+.\\d+\\s+\\d+.\\d+\\s+\\d+.\\d+\\s+\\d+.\\d+\\s+for event\\s+\\w+";
    Gymnast Alice, Bob, Cindy, Dan, Edna, Fred;
    private static double[] lowDifficultyScores = new double[] {1.0, 2.0};
    private static double[] mediumDifficultyScores = new double[] {1.0, 3.0};
    private static double[] highDifficultyScores = new double[] {3.0, 5.0};

    private static double[] lowExecutionScores = new double[] {2.0, 3.0, 4.5, 5.0, 6.0};
    private static double[] mediumExecutionScore = new double[] {2.0, 3.5, 4.0, 6.6, 7.0};

    private static double[] highExecutionScores = new double[] {4.0, 5.0, 10.0, 1.0, 100.0};

    @Before
    public void setUp() {
        Alice = new Gymnast(TestConstants.ALICE, TestConstants.ALICE_BIRTH_YEAR, TestConstants.ALICE_HEIGHT, TestConstants.ISRAEL, Athlete.Gender.FEMALE);
        Bob = new Gymnast(TestConstants.BOB, TestConstants.BOB_BIRTH_YEAR, TestConstants.BOB_HEIGHT, TestConstants.USA, Athlete.Gender.MALE);
        Cindy = new Gymnast(TestConstants.CINDY, TestConstants.CINDY_BIRTH_YEAR, TestConstants.CINDY_HEIGHT, TestConstants.USA, Athlete.Gender.FEMALE);
        Dan = new Gymnast(TestConstants.DAN, TestConstants.DAN_BIRTH_YEAR, TestConstants.DAN_HEIGHT, TestConstants.CANADA, Athlete.Gender.MALE);
        Edna = new Gymnast(TestConstants.EDNA, TestConstants.EDNA_BIRTH_YEAR, TestConstants.EDNA_HEIGHT, TestConstants.CANADA, Athlete.Gender.FEMALE);
        Fred = new Gymnast(TestConstants.FRED, TestConstants.FRED_BIRTH_YEAR, TestConstants.FRED_HEIGHT, TestConstants.ISRAEL, Athlete.Gender.MALE);
    }

    @Test
    public void getGender() {
        assertEquals(Athlete.Gender.FEMALE, Alice.getGender());
        assertEquals(Athlete.Gender.MALE, Bob.getGender());
    }

    @Test
    public void canCompete() {
        // make sure it's false for the equestrian events
        for (String event : Events.getEquestrianEvents()) {
            assertFalse(Alice.canCompete(event));
            assertFalse(Bob.canCompete(event));
        }
        // check males positive
        for (String event : Events.getMaleGymnastEvents()) {
            assertTrue(Bob.canCompete(event));
        }
        // check female positive
        for (String event : Events.getFemaleGymnastEvents()) {
            assertTrue(Alice.canCompete(event));
        }

        // check males can't do female stuff
        for (String event : Events.getFemaleGymnastEvents()) {
            if (!Arrays.stream(Events.getMaleGymnastEvents()).anyMatch(Predicate.isEqual(event))) {
                assertFalse(Bob.canCompete(event));
            }
        }

        // check females can't do male stuff
        for (String event : Events.getMaleGymnastEvents()) {
            if (!Arrays.stream(Events.getFemaleGymnastEvents()).anyMatch(Predicate.isEqual(event))) {
                assertFalse(Alice.canCompete(event));
            }
        }
    }

    @Test
    public void getDifficultyScores() {
        double[] emptyScores = new double[] {0.0, 0.0};
        assertTrue(Arrays.equals(emptyScores, Alice.getDifficultyScores()));

        Alice.setDifficultyScores(lowDifficultyScores);
        assertTrue(Arrays.equals(lowDifficultyScores, Alice.getDifficultyScores()));
        double[] AliceScores = Alice.getDifficultyScores();
        // modify them and check that Alice's are unchanged
        AliceScores[0]++;
        assertTrue(Arrays.equals(lowDifficultyScores, Alice.getDifficultyScores()));
    }

    @Test
    public void setDifficultyScores() {
        double[] copyLowDifficultyScores = lowDifficultyScores.clone();
        Alice.setDifficultyScores(copyLowDifficultyScores);
        // modify the array and check that Alice's are unchanged
        double[] oldLowDifficultyScores = copyLowDifficultyScores.clone();
        copyLowDifficultyScores[0]++;
        assertTrue(Arrays.equals(oldLowDifficultyScores, Alice.getDifficultyScores()));

        // make sure that the scores are not below 0
        double[] negativeDifficultyScores = new double[] {-4.0, 5.0};
        Bob.setDifficultyScores(negativeDifficultyScores);
        assertTrue(Arrays.equals(new double[] {0.0, 5.0}, Bob.getDifficultyScores()));

        // make sure for an array that's too long
        double[] tooLongDifficultyScores = new double[] {4.0, 5.0, 6.0};
        Bob.setDifficultyScores(tooLongDifficultyScores);
        assertTrue(Arrays.equals(new double[] {0.0, 5.0}, Bob.getDifficultyScores()));

        // make sure for an array that's too short
        double[] tooShortDifficultyScores = new double[] {7.9};
        Bob.setDifficultyScores(tooShortDifficultyScores);
        assertTrue(Arrays.equals(new double[] {0.0, 5.0}, Bob.getDifficultyScores()));
    }

    @Test
    public void getExecutionScores() {
        // set her scores
        Alice.setExecutionScores(lowExecutionScores);
        // get them back
        double[] aliceExecutionScores = Alice.getExecutionScores();
        // change the returned values and ensure they are unaffected
        aliceExecutionScores[0]++;
        assertTrue(Arrays.equals(Alice.getExecutionScores(), lowExecutionScores));
    }

    @Test
    public void setExecutionScores() {
        Alice.setExecutionScores(lowExecutionScores);
        //modify the array and check that Alice's are unchanged
        double[] oldLowExecutionScores = lowExecutionScores.clone();
        lowExecutionScores[0]++;
        assertTrue(Arrays.equals(oldLowExecutionScores, Alice.getExecutionScores()));

        // check for an array that is too long
        double[] tooLongExecutionScores = new double[] {4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
        Alice.setExecutionScores(tooLongExecutionScores);
        assertTrue(Arrays.equals(oldLowExecutionScores, Alice.getExecutionScores()));

        // check for an array that is too short
        double[] tooShortExecutionScores = new double[] {4.0, 5.0, 6.5, 7.5};
        Alice.setExecutionScores(tooShortExecutionScores);
        assertTrue(Arrays.equals(oldLowExecutionScores, Alice.getExecutionScores()));
    }

    @Test
    public void getPoints() {
        // test on the low execution and difficulty scores
        Alice.setDifficultyScores(lowDifficultyScores);
        Alice.setExecutionScores(lowExecutionScores);
        // answer should be 14
        assertEquals(5.666667, Alice.getPoints(), TestConstants.DELTA);

        // check with the mediums
        Alice.setDifficultyScores(mediumDifficultyScores);
        Alice.setExecutionScores(mediumExecutionScore);
        assertEquals(6.7, Alice.getPoints(), TestConstants.DELTA);

        // check with the highs
        Cindy.setDifficultyScores(highDifficultyScores);
        Cindy.setExecutionScores(highExecutionScores);
        assertEquals(10.33333, Cindy.getPoints(), TestConstants.DELTA);
    }

    @Test
    public void testToString() {
        // check empty
        assertTrue(Alice.toString().matches(Gymnast_Regex));
        // check adding an event
        Alice.setEventScored(Events.getFemaleGymnastEvents()[0]);
        assertTrue(Alice.toString().matches(Gymnast_Regex));
        // check adding execution
        Alice.setExecutionScores(lowExecutionScores);
        assertTrue(Alice.toString().matches(Gymnast_Regex));
        // check adding difficulty
        Alice.setDifficultyScores(mediumDifficultyScores);
        assertTrue(Alice.toString().matches(Gymnast_Regex));
    }

    @Test
    public void setScores() {
        // set with difficulty and execution
        Alice.setScores(Events.getFemaleGymnastEvents()[0], lowDifficultyScores, lowExecutionScores);
        assertEquals(Events.getFemaleGymnastEvents()[0], Alice.getEventScored());
        assertTrue(Arrays.equals(lowDifficultyScores, Alice.getDifficultyScores()));
        assertTrue(Arrays.equals(lowExecutionScores, Alice.getExecutionScores()));

        // check if the event is not appropriate
        double[] emptyDifficultyScores = {0.0, 0.0};
        // bob with a female event
        Bob.setScores(Events.getFemaleGymnastEvents()[1], mediumDifficultyScores, mediumExecutionScore);
        assertNull(Bob.getEventScored());
        assertTrue(Arrays.equals(emptyDifficultyScores, Bob.getDifficultyScores()));
        assertTrue(Arrays.equals(TestConstants.emptyScores, Bob.getExecutionScores()));

        // alice with an equestrian event
        Alice.setScores(Events.getEquestrianEvents()[0], mediumDifficultyScores, mediumExecutionScore);
        assertEquals(Alice.getEventScored(), Events.getFemaleGymnastEvents()[0]);
        assertTrue(Arrays.equals(lowDifficultyScores, Alice.getDifficultyScores()));
        assertTrue(Arrays.equals(lowExecutionScores, Alice.getExecutionScores()));

        // check with missing arrays
        Alice.setScores("", null, null);
        assertEquals(Events.getFemaleGymnastEvents()[0], Alice.getEventScored());
        assertTrue(Arrays.equals(lowDifficultyScores, Alice.getDifficultyScores()));
        assertTrue(Arrays.equals(lowExecutionScores, Alice.getExecutionScores()));

        Alice.setScores(null, null, null);
        assertEquals(Events.getFemaleGymnastEvents()[0], Alice.getEventScored());
        assertTrue(Arrays.equals(lowDifficultyScores, Alice.getDifficultyScores()));
        assertTrue(Arrays.equals(lowExecutionScores, Alice.getExecutionScores()));

        Alice.setScores(Events.getFemaleGymnastEvents()[1], null, null);
        assertEquals(Events.getFemaleGymnastEvents()[0], Alice.getEventScored());
        assertTrue(Arrays.equals(lowDifficultyScores, Alice.getDifficultyScores()));
        assertTrue(Arrays.equals(lowExecutionScores, Alice.getExecutionScores()));

        // check with arrays of the wrong size
        Alice.setScores(Events.getFemaleGymnastEvents()[1], mediumExecutionScore, mediumExecutionScore);
        assertEquals(Events.getFemaleGymnastEvents()[0], Alice.getEventScored());
        assertTrue(Arrays.equals(lowDifficultyScores, Alice.getDifficultyScores()));
        assertTrue(Arrays.equals(lowExecutionScores, Alice.getExecutionScores()));
    }

    @Test
    public void testSetScores() {
        // set with difficulty and execution
        Alice.setScores(Events.getFemaleGymnastEvents()[0], DoubleStream.concat(Arrays.stream(lowDifficultyScores), Arrays.stream(lowExecutionScores)).toArray());
        assertEquals(Events.getFemaleGymnastEvents()[0], Alice.getEventScored());
        assertTrue(Arrays.equals(lowDifficultyScores, Alice.getDifficultyScores()));
        assertTrue(Arrays.equals(lowExecutionScores, Alice.getExecutionScores()));

        // check if the event is not appropriate
        double[] emptyDifficultyScores = {0.0, 0.0};
        // bob with a female event
        Bob.setScores(Events.getFemaleGymnastEvents()[1], DoubleStream.concat(Arrays.stream(mediumDifficultyScores), Arrays.stream(mediumExecutionScore)).toArray());
        assertNull(Bob.getEventScored());
        assertTrue(Arrays.equals(emptyDifficultyScores, Bob.getDifficultyScores()));
        assertTrue(Arrays.equals(TestConstants.emptyScores, Bob.getExecutionScores()));

        // alice with an equestrian event
        Alice.setScores(Events.getEquestrianEvents()[0], DoubleStream.concat(Arrays.stream(mediumDifficultyScores), Arrays.stream(mediumExecutionScore)).toArray());
        assertEquals(Events.getFemaleGymnastEvents()[0], Alice.getEventScored());
        assertTrue(Arrays.equals(lowDifficultyScores, Alice.getDifficultyScores()));
        assertTrue(Arrays.equals(lowExecutionScores, Alice.getExecutionScores()));

        // check with missing arrays
        Alice.setScores("", null);
        assertEquals(Events.getFemaleGymnastEvents()[0], Alice.getEventScored());
        assertTrue(Arrays.equals(lowDifficultyScores, Alice.getDifficultyScores()));
        assertTrue(Arrays.equals(lowExecutionScores, Alice.getExecutionScores()));

        Alice.setScores(null, null);
        assertEquals(Events.getFemaleGymnastEvents()[0], Alice.getEventScored());
        assertTrue(Arrays.equals(lowDifficultyScores, Alice.getDifficultyScores()));
        assertTrue(Arrays.equals(lowExecutionScores, Alice.getExecutionScores()));

        Alice.setScores(Events.getFemaleGymnastEvents()[1], null);
        assertEquals(Events.getFemaleGymnastEvents()[0], Alice.getEventScored());
        assertTrue(Arrays.equals(lowDifficultyScores, Alice.getDifficultyScores()));
        assertTrue(Arrays.equals(lowExecutionScores, Alice.getExecutionScores()));

        // check with arrays of the wrong size
        Alice.setScores(Events.getFemaleGymnastEvents()[1], DoubleStream.concat(Arrays.stream(mediumExecutionScore), Arrays.stream(mediumExecutionScore)).toArray());
        assertEquals(Events.getFemaleGymnastEvents()[0], Alice.getEventScored());
        assertTrue(Arrays.equals(lowDifficultyScores, Alice.getDifficultyScores()));
        assertTrue(Arrays.equals(lowExecutionScores, Alice.getExecutionScores()));
    }

    @Test
    public void getGoldMedalist() {
        Gymnast[] femaleGymnasts = {Alice, Cindy, Edna};
        Gymnast[] maleGymnasts = {Bob, Dan, Fred};
        Gymnast[] allGymnasts = {Alice, Bob, Cindy, Dan, Edna, Fred};

        // set up some females for an event
        Alice.setScores(Events.getFemaleGymnastEvents()[0], lowDifficultyScores, lowExecutionScores);
        Cindy.setScores(Events.getFemaleGymnastEvents()[0], mediumDifficultyScores, lowExecutionScores);
        Edna.setScores(Events.getFemaleGymnastEvents()[0], highDifficultyScores, highExecutionScores);

        assertEquals(Edna, Gymnast.getGoldMedalist(Events.getFemaleGymnastEvents()[0], femaleGymnasts));

        // try with all of the gymnasts
        assertEquals(Edna, Gymnast.getGoldMedalist(Events.getFemaleGymnastEvents()[0], allGymnasts));

        // move edna to a different event
        Edna.setScores(Events.getFemaleGymnastEvents()[1], highDifficultyScores, highExecutionScores);

        assertEquals(Cindy, Gymnast.getGoldMedalist(Events.getFemaleGymnastEvents()[0], femaleGymnasts));

        // try the males with two with the same score
        Bob.setScores(Events.getMaleGymnastEvents()[3], lowDifficultyScores, lowExecutionScores);
        Dan.setScores(Events.getMaleGymnastEvents()[3], mediumDifficultyScores, mediumExecutionScore );
        Fred.setScores(Events.getMaleGymnastEvents()[3], mediumDifficultyScores, mediumExecutionScore );

        assertEquals(Dan, Gymnast.getGoldMedalist(Events.getMaleGymnastEvents()[3], maleGymnasts));
        assertEquals(Dan, Gymnast.getGoldMedalist(Events.getMaleGymnastEvents()[3], allGymnasts));
    }
}