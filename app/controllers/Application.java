package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.database.DatabaseConnector;
import models.json.Json;
import models.mix.MixSplitter;
import models.record.Track;
import models.utility.TrackList;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class is used to control the model and the view parts of the MVC. It
 * renders the view with the values processed by the model classes.
 */

public final class Application extends Controller {

    /**
     * The database object of the controller.
     */
    private static DatabaseConnector databaseConnector;

    /**
     * The ObjectMapper used to create JsonNode objects.
     */
    private static ObjectMapper mapper;

    /**
     * The index method is called when the application is started and no other
     * messages have been passed.
     *
     * @return an http ok response with the rendered page.
     */
    public static Result index() {
        String url = "w.soundcloud.com/tracks/67016624";
        return ok(index.render(url, 0));
    }

    /**
     * Method is used to receive Json objects containing track information, pass
     * it on the AlgorithmChooser and return back the new start time.
     *
     * @return An http ok response with the new rendered page.
     */
    public static Result trackRequest() {
        return Json.response((Json.getTrack(request().body().asJson())));
    }

    /**
     * Selects a random track from the database.
     *
     * @return A HTTP ok response with a random track id.
     */
    public static Result getRandomSong() {
        Track track = TrackList.get("SELECT DISTINCT track_id FROM tracks ORDER BY RAND() LIMIT 1").get(0);
        return Json.response(track);
    }

    /**
     * Method used to pass a JsonNode object with track waveform on to the
     * MixSplitter class.
     *
     * @return ok response with the start times for the mix.
     */
    public static Result splitWaveform() {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            int trackID = json.get("track").get("id").asInt();
            System.out.println("MIX ID: " + trackID);
            MixSplitter splitter = new MixSplitter(json.get("waveform"), trackID);
            List<Integer> list = splitter.split();
            Map<String, List<Integer>> map = new TreeMap<String, List<Integer>>();
            map.put("response", list);
            JsonNode response = mapper.valueToTree(map);
            return ok(response);
        }
    }

    /**
     * Method used to pass a Json object with track meta-data. This will be used
     * in the future to insert non-existing tracks.
     *
     * @return ok response with a
     */
    public static Result trackMetadata() {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            ObjectNode objNode = mapper.createObjectNode();
            JsonNode response = objNode.put("message", "File was transvered successfully");
            return ok(response);
        }
    }

    /**
     * Set the mode of the preview.
     *
     * @return A HTTP response
     */
    public static Result setPreviewMode() {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("Object is empty");
        } else if (json.get("mode") == null) {
            return badRequest("The expected message does not exist.");
        } else {
            AlgorithmSelector.setMode(json.get("mode").asText());
            return ok("");
        }
    }

    /**
     * Getter for the ObjectMapper Object of the controller.
     *
     * @return the ObjectMapper Object.
     */
    public static ObjectMapper getObjectMapper() {
        return mapper;
    }

    /**
     * Setter for the ObjectMapper object.
     *
     * @param om the new ObjectMapper object.
     */
    public static void setObjectMapper(final ObjectMapper om) {
        mapper = om;
    }

    /**
     * Getter for the DatabaseConnecter Object of the controller.
     *
     * @return the DatabaseConnecter Object.
     */
    public static DatabaseConnector getDatabaseConnector() {
        return databaseConnector;
    }

    /**
     * Setter for the DatabaseConnector object.
     *
     * @param dbc the new DatabaseConnector object.
     */
    public static void setDatabaseConnector(final DatabaseConnector dbc) {
        databaseConnector = dbc;
    }
}
