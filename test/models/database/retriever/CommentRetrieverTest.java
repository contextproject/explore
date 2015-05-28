package models.database.retriever;

import controllers.Application;
import models.database.DatabaseConnector;
import models.utility.CommentList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for the CommentRetriever class.
 */
public class CommentRetrieverTest {

    /**
     * DatabaseConnector object.
     */
    private DatabaseConnector databaseConnector;

    /**
     * Setting up the database connection.
     */
    @Before
    public void setUp() {
        databaseConnector = new DatabaseConnector();
        databaseConnector.loadDrivers();
        databaseConnector.makeConnection("jdbc:mysql://188.166.78.36/contextbase", "context", "password");
        Application.setDatabaseConnector(databaseConnector);
    }

    /**
     * Test for the constructor
     *
     * @throws Exception Exception
     */
    @Test
    public void testCommentRetriever() {
        CommentRetriever commentRetriever = new CommentRetriever(100005416);
        assertNotNull(commentRetriever.getComments());
    }

    /**
     * Test for the getComments() method.
     *
     * @throws Exception Exception
     */
    @Test
    public void testGetComments() throws Exception {
        // Track with one comment and highest playback count
        CommentRetriever commentRetriever = new CommentRetriever(62683860);
        assertEquals(1, commentRetriever.getNoComments());

        CommentList comments = commentRetriever.getComments();
        assertEquals(1, comments.size());
    }

    /**
     * Test for the getNoComments() method
     *
     * @throws Exception Exception
     */
    @Test
    public void testNoGetComments() throws Exception {
        CommentRetriever commentRetriever = new CommentRetriever(100005416);
        assertEquals(47, commentRetriever.getNoComments());
    }
}