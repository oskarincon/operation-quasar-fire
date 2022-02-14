package com.rebelalliance.operationquasarfire.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebelalliance.operationquasarfire.models.Position;
import com.rebelalliance.operationquasarfire.models.Request;
import com.rebelalliance.operationquasarfire.models.Response;
import com.rebelalliance.operationquasarfire.models.Satellite;
import com.rebelalliance.operationquasarfire.services.impl.TopSecretService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static com.rebelalliance.operationquasarfire.constants.Satellites.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(SatelliteController.class)
@WithMockUser
public class SatelliteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopSecretService topSecretService;


    @BeforeEach
    void setup() {
    }

    @Test
    public void topSecretTest() throws Exception {
        final String bodyRequestExpect = "{\"message\":\"test\",\"position\":{\"x\":1.0,\"y\":2.0}}";
        List<Satellite> satellites = new ArrayList<>();
        String[] msgeKenobi = {"este", "", "", "mensaje", ""}, msgSkywalker = {"", "es", "", "", "secreto"}, msgSato = {"este", "", "un", "", ""};
        Satellite kenobi = Satellite.builder().name(KENOBI).distance(123).message(msgeKenobi).build();
        Satellite skywalker = Satellite.builder().name(SKYWALKER).distance(123).message(msgSkywalker).build();
        Satellite sato = Satellite.builder().name(SATO).distance(123).message(msgSato).build();
        satellites.add(kenobi);
        satellites.add(skywalker);
        satellites.add(sato);
        Request em = new Request();
        em.setSatellites(satellites);

        Position posMock = Position.builder().x(1).y(2).build();
        Response respMock = Response.builder().message("test").position(posMock).build();
        when(topSecretService.findPositionMessage(any())).thenReturn(respMock);
        ObjectMapper mapper = new ObjectMapper();
        String requestJson= mapper.writeValueAsString(em);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(PATH_ROOT + PATH_TOP_SECRET)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result);
        JSONAssert.assertEquals(bodyRequestExpect, result.getResponse()
                .getContentAsString(), true);
    }
}
