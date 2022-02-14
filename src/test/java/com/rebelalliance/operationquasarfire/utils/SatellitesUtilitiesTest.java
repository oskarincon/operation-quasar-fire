package com.rebelalliance.operationquasarfire.utils;
import com.rebelalliance.operationquasarfire.models.Position;
import com.rebelalliance.operationquasarfire.utils.impl.SatellitesUtilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class SatellitesUtilitiesTest {
    @InjectMocks
    private SatellitesUtilities satellitesUtilities;

    @Test
    public void getMessageTest(){
        String esperado = "este es un mensaje";
        String resultado ="";
        String [] sato = new String [] {"","este","es","un","mensaje"} ;
        String [] skywalker = new String [] {"", "este","","un","mensaje"} ;
        String [] kenobi = new String [] {"","","es","","mensaje"} ;
        try {
            resultado = satellitesUtilities.getMessage(kenobi, skywalker, sato);
            assertEquals(esperado,resultado);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @Test
    public void getLocationTest(){
        float [] distances = new float [] {400.0f ,315.5f, 684.1f} ;
        DecimalFormat formato = new DecimalFormat("#.00");
        try {
            Position resultado = satellitesUtilities.getLocation(distances);
            assertEquals(formato.format(resultado.getX()), "-181.64");
            assertEquals(formato.format(resultado.getY()), "42.15");
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
