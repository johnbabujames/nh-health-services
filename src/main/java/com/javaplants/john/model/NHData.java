package com.javaplants.john.model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({})
public class NHData {

		@JsonIgnore
		private Map<String, Object> data = new HashMap<>();

		@JsonAnyGetter
		public Map<String, Object> getData() {
			return data;
		}

		@JsonAnySetter
		public void setData(String name, Object value) {
			this.data.put(name, value);
		}
}
