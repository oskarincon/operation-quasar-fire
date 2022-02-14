package com.rebelalliance.operationquasarfire.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Coordinate {
	float x;
	float y;
	private float distance;
}
