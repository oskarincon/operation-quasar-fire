package com.rebelalliance.operationquasarfire.services;

import com.rebelalliance.operationquasarfire.exceptions.InvalidDistanceException;
import com.rebelalliance.operationquasarfire.models.Response;
import com.rebelalliance.operationquasarfire.models.Satellite;

import java.util.List;

public interface ITopSecretService {

    Response findPositionMessage(List<Satellite> satellite) throws InvalidDistanceException;
}
