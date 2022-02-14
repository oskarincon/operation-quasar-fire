package com.rebelalliance.operationquasarfire.utils;

import com.rebelalliance.operationquasarfire.exceptions.InvalidDistanceException;
import com.rebelalliance.operationquasarfire.exceptions.InvalidMessageException;
import com.rebelalliance.operationquasarfire.models.Position;


public interface ISatellitesUtilities {
     Position getLocation(float ...distances) throws InvalidDistanceException;
     String getMessage(String[] ...messages) throws InvalidMessageException;
}
