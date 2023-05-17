package br.com.votacao.service.impl;

import br.com.votacao.exception.PautaNotFoundException;
import br.com.votacao.model.Pauta;
import br.com.votacao.repository.PautaRepository;
import br.com.votacao.service.PautaService;
import br.com.votacao.service.SessionService;
import br.com.votacao.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;

    @Autowired
    public PautaServiceImpl(PautaRepository pautaRepository,
                            SessionService sessaoService,
                            VoteService voteServiceImpl) {
        this.pautaRepository = pautaRepository;
        this.sessaoService = sessaoService;
        this.voteServiceImpl = voteServiceImpl;
    }

    private final SessionService sessaoService;
    private final VoteService voteServiceImpl;

    @Override
    public List<Pauta> findAllPautas() {
        return pautaRepository.findAll();
    }

    @Override
    public Pauta createNewPauta(final Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    @Override
    public void delete(Long id) {
        Optional<Pauta> pautaById = pautaRepository.findById(id);
        if (!pautaById.isPresent()) {
            throw new PautaNotFoundException();
        }
        pautaRepository.delete(pautaById.get());
        sessaoService.deleteByPautaId(id);
        voteServiceImpl.deleteByPautaId(id);
    }

    @Override
    public Pauta findById(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(PautaNotFoundException::new);
    }
}
