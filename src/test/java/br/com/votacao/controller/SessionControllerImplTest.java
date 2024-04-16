package br.com.votacao.controller;

import br.com.votacao.controller.impl.SessionControllerImpl;
import br.com.votacao.model.Pauta;
import br.com.votacao.model.Session;
import br.com.votacao.service.SessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SessionControllerImplTest {

    @Mock
    private SessionService sessionService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ObjectMapper objectMapper = new ObjectMapper();
        SessionControllerImpl sessionController = new SessionControllerImpl(sessionService);
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(sessionController)
                .build();
    }

}
