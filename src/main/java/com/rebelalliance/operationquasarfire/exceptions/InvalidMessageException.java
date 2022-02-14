package com.rebelalliance.operationquasarfire.exceptions;

public class InvalidMessageException extends Exception{
	private static final long serialVersionUID = -1422856915076647444L;

	public InvalidMessageException(String msg) {
		super(msg);
	}
}
