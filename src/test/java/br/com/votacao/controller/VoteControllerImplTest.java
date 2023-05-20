package br.com.votacao.controller;

import br.com.votacao.builder.VoteBuilder;
import br.com.votacao.controller.impl.SessionControllerImpl;
import br.com.votacao.controller.impl.VoteControllerImpl;
import br.com.votacao.model.Vote;
import br.com.votacao.service.SessionService;
import br.com.votacao.service.VoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.fasterxml.jackson.core.type.TypeReference;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(MockitoJUnitRunner.class)
public class VoteControllerImplTest {
    private MockMvc mockMvc;
    @Mock
    private VoteService voteService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ObjectMapper objectMapper = new ObjectMapper();
        VoteControllerImpl voteController = new VoteControllerImpl(voteService);
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(voteController)
                .build();
    }


    @Test
    public void findVotoBySessaoId_ValidId_ReturnsList() {
        // Arrange
        Long id = 1L;
        List<Vote> expectedVotes = new ArrayList<>();
        when(voteService.findVotosByPautaId(id)).thenReturn(expectedVotes);

        // Act
        List<Vote> result = voteService.findVotosByPautaId(id);

        // Assert
        verify(voteService, times(1)).findVotosByPautaId(id);
        assertEquals(expectedVotes, result);
    }

    @Test
    public void findVotoById_ValidId_ReturnsVote() {
        // Arrange
        Long id = 1L;
        Vote expectedVote = new Vote();
        when(voteService.findById(id)).thenReturn(expectedVote);

        // Act
        Vote result = voteService.findById(id);

        // Assert
        verify(voteService, times(1)).findById(id);
        assertEquals(expectedVote, result);
    }

    @Test
    public void createNewVote_ValidParameters_ReturnsVote() {
        // Arrange
        Long idPauta = 1L;
        Long idSessao = 2L;
        Vote voto = new Vote();
        Vote expectedVote = new Vote();
        when(voteService.createNewVote(idPauta, idSessao, voto)).thenReturn(expectedVote);

        // Act
        Vote result = voteService.createNewVote(idPauta, idSessao, voto);

        // Assert
        verify(voteService, times(1)).createNewVote(idPauta, idSessao, voto);
        assertEquals(expectedVote, result);
    }
}

