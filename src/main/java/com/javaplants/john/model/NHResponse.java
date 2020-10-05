package com.javaplants.john.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "metadata", "data" })
public class NHResponse {

	@JsonProperty("metadata")
	private NHMetadata metadata;

	@JsonProperty("data")
	private NHData data;

	@JsonProperty("metadata")
	public NHMetadata getMetadata() {
		return metadata;
	}

	@JsonProperty("metadata")
	public void setMetadata(NHMetadata metadata) {
		this.metadata = metadata;
	}

	@JsonProperty("data")
	public NHData getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(NHData data) {
		this.data = data;
	}
}
