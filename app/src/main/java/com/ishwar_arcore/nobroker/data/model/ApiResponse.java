package com.ishwar_arcore.nobroker.data.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ApiResponse {

	@SerializedName("Response")
	private List<ResponseItem> response;

	public List<ResponseItem> getResponse(){
		return response;
	}
}