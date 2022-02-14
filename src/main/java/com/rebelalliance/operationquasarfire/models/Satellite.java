package com.rebelalliance.operationquasarfire.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Satellite {
	private String name;
    private float distance;
    private String[] message;

	@Override
	public String toString() {
		return "satellites [name=" + name + ", distance=" + distance + ", message=" + message + "]";
	}
}