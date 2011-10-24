package ch.hsr.traildevils.db;

import java.util.List;

import ch.hsr.traildevils.domain.Trail;

import com.db4o.ObjectSet;

public class TrailProvider extends Db4oHelper {

	private static TrailProvider provider;
	
	public static TrailProvider getInstance() {
		if(provider == null)
			provider = new TrailProvider();
		
		return provider;
	}

	public void store(Trail trail) {
		db().store(trail);
	}
	
	public void store(List<Trail> trails){
		for(Trail trail : trails){
			store(trail);
		}
	}

	public void delete(Trail trail) {
		db().delete(trail);
	}

	public List<Trail> findAll() {
		db().query(Trail.class);
		db().query(Trail.class);
		return db().query(Trail.class);
	}

	public void deleteAll() {
		ObjectSet<Trail> trails =  (ObjectSet<Trail>) findAll();
		while(trails.hasNext())
			db().delete(trails.next());
	}
}
