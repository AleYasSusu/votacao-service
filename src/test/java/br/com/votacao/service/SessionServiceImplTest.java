package br.com.votacao.service;

import br.com.votacao.builder.PautaBuilder;
import br.com.votacao.model.Pauta;
import br.com.votacao.model.Session;
import br.com.votacao.repository.SessionRepository;
import br.com.votacao.service.impl.SessionServiceImpl;
import org.assertj.core.api.Assertions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import static br.com.votacao.builder.SessionBuilder.buildSessionDefault;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SessionServiceImplTest {

    @InjectMocks
    private SessionServiceImpl sessionService;

    @Mock
    private SessionRepository sessionRepository;


}