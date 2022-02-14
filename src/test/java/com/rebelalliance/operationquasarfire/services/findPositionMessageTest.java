package com.rebelalliance.operationquasarfire.services;
import com.rebelalliance.operationquasarfire.models.Position;
import com.rebelalliance.operationquasarfire.models.Response;
import com.rebelalliance.operationquasarfire.models.Satellite;
import com.rebelalliance.operationquasarfire.services.impl.TopSecretService;
import com.rebelalliance.operationquasarfire.utils.impl.SatellitesUtilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static com.rebelalliance.operationquasarfire.constants.Satellites.*;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class findPositionMessageTest {
    @InjectMocks
    private TopSecretService topSecretService;
    @Mock
    private SatellitesUtilities satellitesUtilities;


    @Test
    public void getFindPositionMessage(){
        String[] msgeKenobi = {"este", "", "", "mensaje", ""}, msgSkywalker = {"", "es", "", "", "secreto"}, msgSato = {"este", "", "un", "", ""};
        Satellite satellite0 = Satellite.builder().name("skywalker").distance(115.5f).message(msgSkywalker).build();
        Satellite satellite1 = Satellite.builder().name("kenobi").distance(100.0f).message(msgeKenobi).build();
        Satellite satellite2 = Satellite.builder().name("sato").distance(142.7f).message(msgSato).build();
        List<Satellite> data = new ArrayList<>();
        data.add(satellite0);
        data.add(satellite1);
        data.add(satellite2);

        try {
            Position positionMock = Position.builder().x(3).y(4).build();
            when(satellitesUtilities.getMessage(any())).thenReturn("este es el mensaje");
            when(satellitesUtilities.getLocation(any())).thenReturn(positionMock);
            Response resultado = topSecretService.findPositionMessage(data);
            System.out.println(resultado);
            assertEquals(resultado.getMessage(),"este es el mensaje");
            assertEquals(resultado.getPosition().getX(),3);
            assertEquals(resultado.getPosition().getY(),4);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @Test
    public void getFindPositionMessageError(){
        String[] msgeKenobi = {"este", "", "", "mensaje", ""}, msgSkywalker = {"", "es", "", "", "secreto"}, msgSato = {"este", "", "un", "", ""};
        Satellite satellite0 = Satellite.builder().name("skywalker").distance(115.5f).message(msgSkywalker).build();
        Satellite satellite1 = Satellite.builder().name("kenobi").distance(100.0f).message(msgeKenobi).build();
        List<Satellite> data = new ArrayList<>();
        data.add(satellite0);
        data.add(satellite1);
        try {
            Response resultado = topSecretService.findPositionMessage(data);
            System.out.println(resultado);
            assertEquals(resultado.getMessage(),"este es el mensaje");
            assertEquals(resultado.getPosition().getX(),3);
            assertEquals(resultado.getPosition().getY(),4);
        } catch (Exception e){
            String errorMsg = e.getMessage();
            assertEquals(errorMsg, DISTANCES_SATELLITES_ERROR);
        }
    }
}