package ch.hsr.traildevils.viewer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import ch.hsr.traildevils.db.TrailProvider;
import ch.hsr.traildevils.domain.Trail;

public class TrailsController {

	private TrailProvider trailProvider = TrailProvider.getInstance();
	
	/**
	 * Gets called directly after instantiation
	 */
	@PostConstruct
	public void init(){
		fillModelWithData();
	}
	

	private void fillModelWithData() {
		List<Trail> trailList = trailProvider.findAll();
		if(trailList == null)
			trailList = new ArrayList<Trail>();
		
		getModel().setTrailList(trailList);
	}


	public TrailsModel getModel(){
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().evaluateExpressionGet(context, "#{trailsModel}", TrailsModel.class);
	}
	
	/**
	 * This method gets called by the GUI in order to update the domain model.
	 * @param event
	 */
	public void updateName(AjaxBehaviorEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		Trail trail = context.getApplication().evaluateExpressionGet(context, "#{trail}", Trail.class);
		trail.setTimestamp(new Date().getTime());
		
		trailProvider.store(trail);
	}
}
