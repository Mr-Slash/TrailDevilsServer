package ch.hsr.traildevils.db;


import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Db4oHelper {

	private static ObjectContainer oc = null;
	private String dbLocation;

	public Db4oHelper() {
		this.dbLocation = System.getProperty("user.home");
	}

	/**
	 * Create, open and close the database
	 */
	public ObjectContainer db() {

		try {
			if (oc == null || oc.ext().isClosed()) {
				oc = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), db4oDBFullPath());

				// We first load the initial data from the database
				// ExercisesLoader.load(context, oc);
			}
			return oc;
		} catch (Exception ie) {
			ie.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns the path for the database location
	 */
	private String db4oDBFullPath() {
		String fileSeparator = System.getProperty("file.separator");
		return dbLocation + fileSeparator + "traildevils.db4o";
	}

	/**
	 * Closes the database
	 */
	public void close() {
		if (oc != null) {
			oc.close();
		}
	}
}
