import java.time.LocalDate;

import static org.junit.Assert.*;

public class AthleteTest {

    public static final String ALICE = "Alice";
    public static final String BOB = "Bob";
    public static final String CINDY = "Cindy";

    public static final int ALICE_BIRTH_YEAR = 2009;
    public static final int BOB_BIRTH_YEAR = 2008;
    public static final int CINDY_BIRTH_YEAR = 2010;
    public static final int ALICE_HEIGHT = 140;
    public static final int BOB_HEIGHT = 130;
    public static final int CINDY_HEIGHT = 135;
    public static final String ISRAEL = "Israel";
    public static final String USA = "USA";
    public static final String CONGO = "Congo";
    public static final String CANADA = "Canada";


    Athlete Alice, Bob, Cindy;
    @org.junit.Before
    public void setUp() throws Exception {
        Alice = new Gymnast(ALICE, ALICE_BIRTH_YEAR, ALICE_HEIGHT, ISRAEL, Athlete.Gender.FEMALE);
        Bob = new Gymnast(BOB, BOB_BIRTH_YEAR, BOB_HEIGHT, USA, Athlete.Gender.MALE);
        Cindy = new Equestrian(CINDY, CINDY_BIRTH_YEAR, CINDY_HEIGHT, USA, "Article");
    }

    @org.junit.Test
    public void getAthleteName() {
        assertTrue(Alice.getAthleteName().equals(ALICE));
        assertTrue(Bob.getAthleteName().equals(BOB));
        assertTrue(Cindy.getAthleteName().equals(CINDY));
    }

    @org.junit.Test
    public void setAthleteName() {
        // test Alice changes
        Alice.setAthleteName(BOB);
        assertTrue(Alice.getAthleteName().equals(BOB));
        assertTrue(Bob.getAthleteName().equals(BOB));
        assertTrue(Cindy.getAthleteName().equals(CINDY));

        // test Bob changes
        Bob.setAthleteName(ALICE);
        assertTrue(Alice.getAthleteName().equals(BOB));
        assertTrue(Bob.getAthleteName().equals(ALICE));
        assertTrue(Cindy.getAthleteName().equals(CINDY));

        // test Cindy changes
        Cindy.setAthleteName(ALICE);
        assertTrue(Alice.getAthleteName().equals(BOB));
        assertTrue(Bob.getAthleteName().equals(ALICE));
        assertTrue(Cindy.getAthleteName().equals(ALICE));
    }

    @org.junit.Test
    public void getBirthYear() {
        assertTrue(Alice.getBirthYear() == ALICE_BIRTH_YEAR);
        assertTrue(Bob.getBirthYear()==BOB_BIRTH_YEAR);
        assertTrue(Cindy.getBirthYear()==CINDY_BIRTH_YEAR);
    }

    @org.junit.Test
    public void setBirthYear() {
        // test Alice changes
        Alice.setBirthYear(2023);
        assertTrue(Alice.getBirthYear() == 2023);
        assertTrue(Bob.getBirthYear() == BOB_BIRTH_YEAR);
        assertTrue(Cindy.getBirthYear() == CINDY_BIRTH_YEAR);

        // test Bob changes
        Bob.setBirthYear(-1);
        assertTrue(Alice.getBirthYear() == 2023);
        assertTrue(Bob.getBirthYear() == 0);
        assertTrue(Cindy.getBirthYear() == CINDY_BIRTH_YEAR);

        // test Cindy changes
        Cindy.setBirthYear(1990);
        assertTrue(Alice.getBirthYear() == 2023);
        assertTrue(Bob.getBirthYear() == 0);
        assertTrue(Cindy.getBirthYear() == 1990);
    }

    @org.junit.Test
    public void testSetBirthYear() {
        // test Alice changes
        Alice.setBirthYear(LocalDate.of(2023, 1, 10));
        assertTrue(Alice.getBirthYear() == 2023);
        assertTrue(Bob.getBirthYear() == BOB_BIRTH_YEAR);
        assertTrue(Cindy.getBirthYear() == CINDY_BIRTH_YEAR);

        // test Bob changes
        Bob.setBirthYear(LocalDate.of(-1, 2, 20));
        assertTrue(Alice.getBirthYear() == 2023);
        assertTrue(Bob.getBirthYear() == 0);
        assertTrue(Cindy.getBirthYear() == CINDY_BIRTH_YEAR);

        // test Cindy changes
        Cindy.setBirthYear(LocalDate.of(1990, 3, 30));
        assertTrue(Alice.getBirthYear() == 2023);
        assertTrue(Bob.getBirthYear() == 0);
        assertTrue(Cindy.getBirthYear() == 1990);
    }

    @org.junit.Test
    public void getHeight() {
        assertEquals(Alice.getHeight(), ALICE_HEIGHT);
        assertEquals(Bob.getHeight(), BOB_HEIGHT);
        assertEquals(Cindy.getHeight(), CINDY_HEIGHT);
    }

    @org.junit.Test
    public void setHeight() {
        // test Alice height
        Alice.setHeight(200);
        assertEquals(Alice.getHeight(), 200);
        assertEquals(Bob.getHeight(), BOB_HEIGHT);
        assertEquals(Cindy.getHeight(), CINDY_HEIGHT);

        // test Bob height
        Bob.setHeight(300);
        assertEquals(Alice.getHeight(), 200);
        assertEquals(Bob.getHeight(), 300);
        assertEquals(Cindy.getHeight(), CINDY_HEIGHT);

        // test Cindy height
        Cindy.setHeight(-100);
        assertEquals(Alice.getHeight(), 200);
        assertEquals(Bob.getHeight(), 300);
        assertEquals(Cindy.getHeight(), 0);
    }

    @org.junit.Test
    public void getNation() {
        assertTrue(Alice.getNation().equals(ISRAEL));
        assertTrue(Bob.getNation().equals(USA));
        assertTrue(Cindy.getNation().equals(USA));
    }

    @org.junit.Test
    public void setNation() {
        // test Alice
        Alice.setNation(CONGO);
        assertEquals(Alice.getNation(), CONGO);
        assertEquals(Bob.getNation(), USA);
        assertEquals(Cindy.getNation(), USA);

        // test Bob
        Bob.setNation("USA");
        assertEquals(Alice.getNation(), CONGO);
        assertEquals(Bob.getNation(), USA);
        assertEquals(Cindy.getNation(), USA);

        // test Cindy
        Cindy.setNation(CANADA);
        assertEquals(Alice.getNation(), CONGO);
        assertEquals(Bob.getNation(), USA);
        assertEquals(Cindy.getNation(), CANADA);
    }

    @org.junit.Test
    public void getEventScored() {
        assertNull(Alice.getEventScored());
        assertNull(Bob.getEventScored());
        assertNull(Cindy.getEventScored());
    }

    @org.junit.Test
    public void setEventScored() {
        // set Alice
        Alice.setEventScored(Events.getFemaleGymnastEvents()[0]);
        assertEquals(Alice.getEventScored(), Events.getFemaleGymnastEvents()[0]);

        // set Bob
        Bob.setEventScored(Events.getMaleGymnastEvents()[0]);
        assertEquals(Alice.getEventScored(), Events.getFemaleGymnastEvents()[0]);
        assertEquals(Bob.getEventScored(), Events.getMaleGymnastEvents()[0]);

        // set Cindy
        Cindy.setEventScored(Events.getEquestrianEvents()[1]);
        assertEquals(Alice.getEventScored(), Events.getFemaleGymnastEvents()[0]);
        assertEquals(Bob.getEventScored(), Events.getMaleGymnastEvents()[0]);
        assertEquals(Cindy.getEventScored(), Events.getEquestrianEvents()[1]);
    }
}