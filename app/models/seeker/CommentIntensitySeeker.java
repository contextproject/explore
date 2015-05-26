package models.seeker;

import models.snippet.Comment;
import models.snippet.TimedSnippet;

import java.util.Set;
import java.util.TreeSet;

/**
 * This class returns the start time for a snippet,
 * based on the comment intensity of that song.
 */
public class CommentIntensitySeeker implements Seeker {

    /**
     * Generates a start time for a snippet.
     *
     * @param coms     The set of comments of a given song
     * @param duration The duration of that given song
     * @return a start time
     */
    protected static int getStartTime(final Set<Comment> coms, final int duration) {
        int start = -1;
        int maxcount = 0;
        Set<Integer> passed = new TreeSet<Integer>();
        for (Comment c : coms) {
            int count = 0;
            if (!passed.contains(c.getTime())) {
                for (Comment c2 : coms) {
                    if (c2.getTime() >= c.getTime() && c2.getTime() <= (c.getTime() + duration)) {
                        count++;
                    }
                }
                if (count > maxcount) {
                    maxcount = count;
                    start = c.getTime();
                }
                passed.add(c.getTime());
            }
        }
        return start;
    }

    /**
     * Seeks the Snippet to be used of a given song with a unknown duration.
     *
     * @return A TimedSnippet object
     */
    @Override
    public TimedSnippet seek() {
        return new TimedSnippet(0);
    }

    /**
     * Seeks the snippet to be used of a given song with the duration known.
     *
     * @param coms     The set of comments to use
     * @param duration The duration of the song
     * @return A TimedSnippet object
     */
    public TimedSnippet seekDuration(final Set<Comment> coms, final int duration) {
        return new TimedSnippet(getStartTime(coms, duration), duration);
    }
}
