package ch.hsr.traildevils.viewer;

import java.util.List;

import ch.hsr.traildevils.domain.Trail;

public class TrailsModel {

	private List<Trail> trailList;

	public void setTrailList(List<Trail> trailList) {
		this.trailList = trailList;
	}
	
	public List<Trail> getTrailList(){
		return trailList;
	}
}
