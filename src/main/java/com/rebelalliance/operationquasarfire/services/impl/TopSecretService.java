package com.rebelalliance.operationquasarfire.services.impl;

import com.rebelalliance.operationquasarfire.exceptions.InvalidDistanceException;
import com.rebelalliance.operationquasarfire.exceptions.InvalidMessageException;
import com.rebelalliance.operationquasarfire.models.Position;
import com.rebelalliance.operationquasarfire.models.Response;
import com.rebelalliance.operationquasarfire.models.Satellite;
import com.rebelalliance.operationquasarfire.services.ITopSecretService;
import com.rebelalliance.operationquasarfire.utils.impl.SatellitesUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rebelalliance.operationquasarfire.constants.Satellites.*;


@Service
public class TopSecretService implements ITopSecretService {

    @Autowired()
    private SatellitesUtilities satellitesUtilities;

    @Override
    public Response findPositionMessage(List<Satellite> satellites) throws InvalidDistanceException {
        String[][] messages = new String[satellites.size()][];
        float[] distances = new float[satellites.size()];
        float distanceKenobi = 0, distanceSkywalker = 0, distanceSato = 0;
        String[] messagesKenobi = {}, messagesSkywalker = {}, messagesSato = {};

        for (Satellite satellite:satellites) {
            if(satellite.getName().equals(KENOBI)) {
                distanceKenobi = satellite.getDistance();
                messagesKenobi = satellite.getMessage();
            }
            if(satellite.getName().equals(SKYWALKER)) {
                distanceSkywalker = satellite.getDistance();
                messagesSkywalker = satellite.getMessage();
            }
            if(satellite.getName().equals(SATO)) {
                distanceSato = satellite.getDistance();
                messagesSato = satellite.getMessage();
            }
        }
        if(distanceKenobi == 0.0f  || distanceSkywalker == 0.0f || distanceSato == 0.0f ) {
            throw new InvalidDistanceException(DISTANCES_SATELLITES_ERROR);
        }
        if(messagesKenobi.length <=0  || messagesSkywalker.length <=0 || messagesSato.length <=0 ) {
            throw new InvalidDistanceException(DISTANCES_SATELLITES_ERROR);
        }
        distances[0] = distanceKenobi;
        messages[0] = messagesKenobi;
        distances[1] = distanceSkywalker;
        messages[1] = messagesSkywalker;
        distances[2] = distanceSato;
        messages[2] = messagesSato;
        try {
            String messagesResponse = satellitesUtilities.getMessage(messages);
            Position positionResponse = satellitesUtilities.getLocation(distances);
            return Response.builder().position(positionResponse).message(messagesResponse).build();
        }catch (InvalidDistanceException e1) {
            throw new InvalidDistanceException(e1.getMessage());
        } catch (InvalidMessageException e2){
            throw new InvalidDistanceException(e2.getMessage());
        }
    }
}
