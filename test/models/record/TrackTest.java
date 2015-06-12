package models.record;

import models.database.DatabaseConnector;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the Track class.
 */
public class TrackTest {

    DatabaseConnector databaseConnector;

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        databaseConnector = new DatabaseConnector();
        databaseConnector.loadDrivers();
        databaseConnector.makeConnection("jdbc:mysql://188.166.78.36/contextbase", "context", "password");
    }

    /**
     * Test for the constructor.
     */
    @Test
    public void testTrack1() {
        assertNotNull(new Track());
    }

//    /**
//     * Test for the constructor.
//     */
//    @Test
//    public void testTrack2() {
//        Track track = new Track(databaseConnector.executeQuery("SELECT * FROM tracks LIMIT 1"));
//        assertNotNull(track);
//        assertEquals(100005416, track.getId());
//        assertEquals(963825, track.getDuration());
//    }

    /**
     * Test for the constructor.
     */
    @Test
    public void testTrack3() {
        Track track = new Track(1, 1);
        assertNotNull(track);
        assertEquals(1, track.getId());
        assertEquals(1, track.getDuration());
    }

    /**
     * Test for the process() method.
     */
    @Test
    public void testProcess1() {
        ResultSet resultSet = databaseConnector.executeQuery("SELECT * FROM tracks LIMIT 1");
        Track track = new Track();
        assertTrue(track.process(resultSet));
    }

//    /**
//     * Test for the process() method.
//     */
//    @Test
//    public void testProcess2() {
//        ResultSet resultSet = databaseConnector.executeQuery("SELECT * FROM comments LIMIT 1");
//        Track track = new Track();
//        assertFalse(track.process(resultSet));
//    }

    /**
     * Test for the getter and setter of the id of the track.
     */
    @Test
    public void testId() {
        Track track = new Track();
        assertEquals(0, track.getId());
        track.setId(1);
        assertEquals(1, track.getId());
    }

    /**
     * Test for the getter and setter of the duration of the track.
     */
    @Test
    public void testDuration() {
        Track track = new Track();
        assertEquals(0, track.getDuration());
        track.setDuration(1);
        assertEquals(1, track.getDuration());
    }

    /**
     * Test for the equals() method.
     */
    @Test
    public void testEquals1() {
        Track a = new Track(1, 1);
        Track b = new Track(1, 1);

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }

    /**
     * Test for the equals() method.
     */
    @Test
    public void testEquals2() {
        Track a = new Track(1, 1);
        Track b = new Track(2, 1);

        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

    /**
     * Test for the equals() method.
     */
    @Test
    public void testEquals4() {
        assertFalse(new Track(1, 1).equals(2));
    }

    /**
     * Test for the hashCode() method.
     */
    @Test
    public void testHashCode() {
        Track a = new Track(1, 1);
        assertEquals(32, a.hashCode());
    }

    /**
     * Test for the compareTo() method.
     */
    @Test
    public void testCompareTo1() {
        Track a = new Track(1, 1);
        Track b = new Track(2, 2);
        assertEquals(-1, a.compareTo(b));
    }

    /**
     * Test for the compareTo() method.
     */
    @Test
    public void testCompareTo2() {
        Track a = new Track(1, 1);
        Track b = new Track(2, 2);
        ArrayList<Track> tracks = new ArrayList<Track>();
        tracks.add(b);
        tracks.add(a);
        Collections.sort(tracks);
        assertEquals(1, tracks.get(0).getId());
    }
}
