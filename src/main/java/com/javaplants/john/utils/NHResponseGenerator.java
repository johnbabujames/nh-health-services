package com.javaplants.john.utils;

import com.javaplants.john.model.NHData;
import com.javaplants.john.model.NHMetadata;
import com.javaplants.john.model.NHResponse;

import java.util.Map;

public class NHResponseGenerator {
	
	public static NHResponse getSuccessResponse(String statusCode, String responseDescription, Map<String, Object> responseData) {
		NHResponse response = new NHResponse();
		NHMetadata metaData = new NHMetadata();
		metaData.setStatus(statusCode);
		metaData.setDescription(responseDescription);
		metaData.setSuccess(true);
		NHData data = new NHData();
		if(responseData != null)
		{
			for(Map.Entry<String, Object> item : responseData.entrySet()) {
			data.setData(item.getKey(), item.getValue());
			}
		}
		response.setMetadata(metaData);
		response.setData(data);
		return response;
	}
	
	public static NHResponse getSuccessStatusResponse(String statusCode, String responseDescription) {
		NHResponse srvcMgmtResponse = new NHResponse();
		NHMetadata metaData = new NHMetadata();
		metaData.setStatus(statusCode);
		metaData.setDescription(responseDescription);
		metaData.setSuccess(true);
		srvcMgmtResponse.setMetadata(metaData);
		return srvcMgmtResponse;
	}
	
	public static NHResponse getFailureResponse(String statusCode, String responseDescription) {
		NHResponse response = new NHResponse();
		NHMetadata metaData = new NHMetadata();
		NHData data = new NHData();
		metaData.setStatus(statusCode);
		metaData.setDescription(responseDescription);
		metaData.setSuccess(false);
		response.setMetadata(metaData);
		response.setData(data);
		return response;
	}
}
