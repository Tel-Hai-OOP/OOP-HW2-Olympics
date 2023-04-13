/**
 * Class storing static information about events in the olympics assignment.  Code to be used for Assignment 2 in the
 * Object Oriented Programming (Java) course at Tel Hai Spring 2023.
 *
 * @author Michael J. May
 * @version 1.0
 */
public class Events {
    /**
     * The events that male gymnasts can compete in.
     */
    private enum MaleGymnastEvents {All_Around, Floor_Exercise, Horizontal_Bar, Parallel_Bars, Pommel_Horse, Rings, Vault};

    /**
     * The events that female gymnasts can compete it.
     */
    private enum FemaleGymnastEvents {All_Around, Balance_Beam, Floor_Exercise, Uneven_Bars, Vault};

    /**
     * The equestrian events supported
     */
    private enum EquestrianEvents {Dressage, Eventing, Jumping};

    /**
     * Gets the Equestrian events in the olympics
     * @return  An array of the events
     */
    public static String[] getEquestrianEvents(){
        String[] events  = new String[EquestrianEvents.values().length];
        for (EquestrianEvents event : EquestrianEvents.values())
        {
            events[event.ordinal()] = event.name();
        }
        return events;
    }

    /**
     * Gets the male gymnast events
     * @return  An array of the events
     */
    public static String[] getMaleGymnastEvents() {
        String[] events = new String[MaleGymnastEvents.values().length];
        for (MaleGymnastEvents event : MaleGymnastEvents.values()) {
            events[event.ordinal()] = event.name();
        }
        return events;
    }

    /**
     * Gets the female gymnast events
     * @return  An array of the events
     */
    public static String[] getFemaleGymnastEvents() {
        String[] events = new String[FemaleGymnastEvents.values().length];
        for (FemaleGymnastEvents event : FemaleGymnastEvents.values()) {
            events[event.ordinal()] = event.name();
        }
        return events;
    }
}
