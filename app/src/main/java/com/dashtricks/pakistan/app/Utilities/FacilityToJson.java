package com.dashtricks.pakistan.app.Utilities;

public abstract class FacilityToJson {
	public static final Gson g = new Gson();
	
	public static String FacilityToJson(Facility f) {
		return gson.toJson(f);
	}
}