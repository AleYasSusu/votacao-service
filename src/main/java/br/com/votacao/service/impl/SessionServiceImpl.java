package br.com.votacao.service.impl;

import br.com.votacao.exception.PautaNotFoundException;
import br.com.votacao.exception.SessaoNotFoundException;
import br.com.votacao.model.Pauta;
import br.com.votacao.model.Session;
import br.com.votacao.repository.SessaoRepository;
import br.com.votacao.service.PautaService;
import br.com.votacao.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessaoRepository sessaoRepository;
    private final PautaService pautaService;

    @Override
    public List<Session> findAll() {
        return sessaoRepository.findAll();
    }

    @Override
    public Session createSession(Long id, Session session) {
        Pauta pauta = pautaService.findById(id);
        if (pauta == null) {
            throw new PautaNotFoundException();
        }
        session.setPauta(pauta);
        return save(session);
    }

    private Session save(final Session session) {
        session.setDataInicio(session.getDataInicio() == null ? LocalDateTime.now() : session.getDataInicio());
        session.setMinutosValidade(session.getMinutosValidade() == null ? 1L : session.getMinutosValidade());
        return sessaoRepository.save(session);
    }

    @Override
    public void delete(Long id) {
        if (!sessaoRepository.existsById(id)) {
            throw new SessaoNotFoundException();
        }
        sessaoRepository.deleteById(id);
    }
    @Override
    public void deleteByPautaId(Long id) {
        sessaoRepository.deleteByPautaId(id);
    }

    @Override
    public Session findById(Long id) {
        return sessaoRepository.findById(id)
                .orElseThrow(SessaoNotFoundException::new);
    }

    @Override
    public Session findByIdAndPautaId(Long idSessao, Long pautaId) {
        return sessaoRepository.findByIdAndPautaId(idSessao, pautaId)
                .orElseThrow(SessaoNotFoundException::new);
    }

    @Override
    public Long countSessoesByPautaId(Long pautaId) {
        return sessaoRepository.countByPautaId(pautaId);
    }
}
