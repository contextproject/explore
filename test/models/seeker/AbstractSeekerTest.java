package models.seeker;

import basic.BasicTest;

import models.record.Track;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the abstract Seeker class.
 * 
 * @since 16-06-2015
 * @version 16-06-2015
 * 
 * @see BasicTest
 * @see AbstractSeeker
 * @see Seeker
 * 
 * @author stefan boodt
 *
 */
public abstract class AbstractSeekerTest extends BasicTest {
    
    /**
     * The AbstractSeeker under test.
     */
    private AbstractSeeker seeker;
    
    /**
     * Default track used by the tests.
     */
    protected static final Track DEFAULT_TRACK = new Track(10, 1000);

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Returns the seeker under test.
     * @return the seeker under test.
     */
    public AbstractSeeker getSeeker() {
        return seeker;
    }

    /**
     * Sets the seeker under test.
     * @param seeker the new seeker.
     */
    public void setSeeker(final AbstractSeeker seeker) {
        setObjectUnderTest(seeker);
        this.seeker = seeker;
    }

    /**
     * Tests the {@link AbstractSeeker#getTrack()} method.
     */
    @Test
    public void testGetTrack() {
        assertEquals(DEFAULT_TRACK, getSeeker().getTrack());
    }

    /**
     * Tests the {@link AbstractSeeker#getTrack()} method.
     */
    @Test
    public void testGetTrackAfterSetting() {
        final Track expected = new Track(11, -1);
        getSeeker().setTrack(expected);
        assertEquals(expected, getSeeker().getTrack());
    }

    /**
     * Tests the {@link AbstractSeeker#getDecorate()} method.
     */
    @Test
    public void testGetDecorate() {
        final Seeker expected = new NullSeeker();
        getSeeker().setDecorate(expected);
        assertEquals(expected, getSeeker().getDecorate());
    }
}
