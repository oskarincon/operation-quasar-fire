package com.rebelalliance.operationquasarfire.utils.impl;
import com.rebelalliance.operationquasarfire.exceptions.InvalidDistanceException;
import com.rebelalliance.operationquasarfire.exceptions.InvalidMessageException;
import com.rebelalliance.operationquasarfire.models.Coordinate;
import com.rebelalliance.operationquasarfire.models.Position;
import com.rebelalliance.operationquasarfire.utils.ISatellitesUtilities;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.rebelalliance.operationquasarfire.constants.Satellites.*;

@Component
public class SatellitesUtilities implements ISatellitesUtilities {
    public Position getLocation(float... distances) throws InvalidDistanceException {
        validateDistances(distances);
        Coordinate kenobi = Coordinate.builder().x(KENOBI_COORDINATE[0]).y(KENOBI_COORDINATE[1]).distance(distances[0]).build();
        Coordinate skywalker = Coordinate.builder().x(SKYWALKER_COORDINATE[0]).y(SKYWALKER_COORDINATE[1]).distance(distances[1]).build();
        Coordinate sato = Coordinate.builder().x(SATO_COORDINATE[0]).y(SATO_COORDINATE[1]).distance(distances[2]).build();
        return getDistance(kenobi, skywalker, sato);
    }

    public String getMessage(String[]... messages) throws InvalidMessageException{
        validateMessages(messages);
        String mensajeFull = "";
        int aux = 0;
        String[] message1, message2, message3;
        message1 = messages[0];
        message2 = messages[1];
        message3 = messages[2];
        if (message1.length != message2.length || message1.length != message3.length || message2.length != message3.length) {
            String[][] messagesFormated =  deleteDesfase(message1, message2, message3);
            message1 = messagesFormated[0];
            message2 = messagesFormated[1];
            message3 = messagesFormated[2];
        }
        aux = message1.length;

        for (int i = 0;	i < aux; i++) {
            if (!message1[i].equals("")) {
                mensajeFull = validateWord(message1[i], mensajeFull);
            } else if (!message2[i].equals("")) {
                mensajeFull = validateWord(message2[i], mensajeFull);
            } else if (!message3[i].equals("")) {
                mensajeFull = validateWord(message3[i], mensajeFull);
            } else {
                throw new InvalidMessageException(MESSAGE_ERROR);
            }

        }
        return mensajeFull.replaceAll("\\s*$","");
    }

    private static String validateWord(String word, String message) {
        String[] msg = message.split("\\s+");
        for (int i = 0; i < msg.length; i++) {
            if(word.equals(msg[i])) {
                return message + " ";
            }
        }
        return message + word + " ";
    }

    private static String[][] deleteDesfase(String[] message1,String[] message2, String[] message3 ) {
        if(message1.length == message2.length) {
            if(message3.length < message2.length) {
                message2 = removeElementUsingCollection(message2, 1);
                message1 = removeElementUsingCollection(message1, 1);
            } else {
                message3 = removeElementUsingCollection(message3, 1);
            }
        } else if((message2.length == message3.length)){
            if(message1.length < message2.length) {
                message2 = removeElementUsingCollection(message2, 1);
                message3 = removeElementUsingCollection(message3, 1);
            } else {
                message2 = removeElementUsingCollection(message2, 1);
            }
        } else if((message1.length == message3.length)){
            if(message2.length < message3.length) {
                message3 = removeElementUsingCollection(message3, 1);
                message1 = removeElementUsingCollection(message1, 1);
            } else {
                message2 = removeElementUsingCollection(message2, 1);
            }
        }
        String[][] messages = new String[3][message1.length];
        messages[0] = message1;
        messages[1] = message2;
        messages[2] = message3;
        return messages;
    }

    private static void validateDistances(float... distances) throws InvalidDistanceException {
        for (float distance:distances) {
            if(distance <= 0 ) {
                throw new InvalidDistanceException(DISTANCE_ERROR);
            }
        }
    }

    private static Position getDistance(Coordinate orb1, Coordinate obr2, Coordinate obr3){
        float a, b, c, d, e, f, dX, dY;
        a=2*obr2.getX() - 2*orb1.getX();
        b=2*obr2.getY() - 2*orb1.getY();
        c=(float) (Math.pow(orb1.getDistance(),2) - Math.pow(obr2.getDistance(),2) - Math.pow(orb1.getX(),2) + Math.pow(obr2.getX(),2) - Math.pow(orb1.getY(),2) + Math.pow(obr2.getY(),2));
        d=2*obr3.getX() - 2*obr2.getX();
        e=2*obr3.getY() - 2*obr2.getY();
        f=(float) (Math.pow(obr2.getDistance(),2) - Math.pow(obr3.getDistance(), 2) - Math.pow(obr2.getX(),2) + Math.pow(obr3.getX(),2) - Math.pow(obr2.getY(),2) + Math.pow(obr3.getY(),2));
        dX = (c*e - f*b) / (e*a - b*d);
        dY = (c*d - a*f) / (b*d - a*e);
        return Position.builder().x(dX).y(dY).build();
    }

    private static void validateMessages(String[]... messages) throws InvalidMessageException {
            if(isEmptyMessage(messages[0]) || isEmptyMessage(messages[1]) || isEmptyMessage(messages[2])) {
                throw new InvalidMessageException(MESSAGE_ERROR);
            }
    }

    private static boolean isEmptyMessage(String[] arrays) {
        return arrays == null || arrays.length == 0;
    }

    public static String[] removeElementUsingCollection( String[] arr, int index ){
        List<String> tempList = new ArrayList<>(Arrays.asList(arr));
        tempList.remove(index);
        return tempList.toArray(new String[0]);
    };
}
