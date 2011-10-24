package ch.hsr.traildevils.synchronize;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import ch.hsr.traildevils.db.TrailProvider;
import ch.hsr.traildevils.domain.Trail;
import ch.hsr.traildevils.util.HttpHandler;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class SyncTrailController {

	// Local Test Server
	private static final String TRAILS_URL = "http://152.96.80.18:8080/api/trails";

	private TrailProvider trailProvider = TrailProvider.getInstance();
	private HttpHandler httpHandler = new HttpHandler();
	
	public SyncTrailController(){
		System.out.println("SyncTrailController() instantiated");
	}
	
	
	public void restoreTrailData(){
		List<Trail> trailList = downloadDataFromServer();

		trailProvider.deleteAll();
		trailProvider.store(trailList);
	}
	
	private List<Trail> downloadDataFromServer() {
		httpHandler.connectTo(TRAILS_URL);
		
		List<Trail> trails = new ArrayList<Trail>(500);

		JsonElement json = new JsonParser().parse(httpHandler.getReader());
		Iterator<JsonElement> iterator = json.getAsJsonArray().iterator();
		Gson gson = new Gson();
		while (iterator.hasNext()) {
			Trail trail = gson.fromJson((JsonElement) iterator.next(), Trail.class);
			trails.add(modifyTrail(trail));
		}
		httpHandler.resetStream();
		
		return trails;
	}

	/**
	 * Use this method if you need to do additional modifications to the trail. E.g. Add manually some
	 * fields if the production server doesn't provide them yet.
	 * 
	 * @param trail
	 * @return
	 */
	private Trail modifyTrail(Trail trail) {
		trail.setTimestamp(new Date().getTime());
		return trail;
	}
}
