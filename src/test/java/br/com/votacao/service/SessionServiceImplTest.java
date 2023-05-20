package br.com.votacao.service;

import br.com.votacao.builder.PautaBuilder;
import br.com.votacao.builder.SessionBuilder;
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
import static java.util.Optional.of;;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SessionServiceImplTest {

    @InjectMocks
    private SessionServiceImpl sessionService;

    @Mock
    private SessionRepository sessionRepository;


    @Test
    public void shouldFindAllSession() {
        //Data
        List<Session> allSession = buildSessionDefault().buildList();
        when(sessionRepository.findAll()).thenReturn(allSession);
        //Action
        List<Session> response = sessionService.findAll();

        //Result
        Assertions.assertThat(response).isEqualTo(allSession);
        verify(sessionRepository, times(1)).findAll();
    }

    @Test
    public void shouldFindSessionById() {
        //Data
        Session session = buildSessionDefault().build();
        when(sessionRepository.findById(10L)).thenReturn(of(session));

        //Action
        Session response = sessionService.findById(10L);

        //Result
        Assertions.assertThat(response).isEqualTo(session);
        verify(sessionRepository, times(1)).findById(10L);
    }


    @Test
    public void shouldDeleteSession() {
        // Data
        Session session = buildSessionDefault().build();

        when(sessionRepository.existsById(1L)).thenReturn(true);

        // Action
        sessionService.delete(1L);

        // Result
        verify(sessionRepository, times(1)).deleteById(1L);
    }


    @Test
    public void testCreateSession() {
        // Dados de entrada
        Pauta pauta = PautaBuilder.buildPautaDefault().build();

        // Mock do repositório
        when(sessionRepository.save(Mockito.any(Session.class))).thenAnswer(invocation -> {
            Session session = invocation.getArgument(0);
            session.setId(1L); // Simulando a atribuição do ID no salvamento
            return session;
        });

        // Chamar o método a ser testado
        Session save = sessionService.createSession(pauta, 1L);

        // Verificar se o método save do repositório foi chamado corretamente
        verify(sessionRepository, times(1)).save(Mockito.argThat(session ->
                session.getDataInicio() != null &&
                        session.getMinutosValidade() != null &&
                        session.getPauta() != null &&
                        session.getPauta().equals(pauta)
        ));

        // Verificar o resultado esperado
        assertNotNull(save);
        assertNotNull(save.getId());
        assertEquals(pauta, save.getPauta());
    }
}