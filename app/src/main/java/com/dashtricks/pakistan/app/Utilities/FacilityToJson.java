package com.dashtricks.pakistan.app.Utilities;

import com.dashtricks.pakistan.app.General.Facility;
import com.google.gson.Gson;


public abstract class FacilityToJson {
	public static final Gson g = new Gson();
	
	public static String FacilityToJson(Facility f) {
		return g.toJson(f);
	}
}