package com.rebelalliance.operationquasarfire.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Response {
    private String message;
    private Position position;
}
