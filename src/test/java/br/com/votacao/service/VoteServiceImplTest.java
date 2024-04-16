package br.com.votacao.service;

import br.com.votacao.builder.SessionBuilder;
import br.com.votacao.builder.VoteBuilder;
import br.com.votacao.domain.CpfValidationDto;
import br.com.votacao.domain.VotingDto;
import br.com.votacao.exception.InvalidCpfException;
import br.com.votacao.exception.SessionTimeOutException;
import br.com.votacao.exception.UnableCpfException;
import br.com.votacao.exception.VoteAlreadyExistsException;
import br.com.votacao.model.Pauta;
import br.com.votacao.model.Session;
import br.com.votacao.model.Vote;
import br.com.votacao.repository.VoteRepository;
import br.com.votacao.service.impl.VoteServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceImplTest {



}