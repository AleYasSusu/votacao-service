package br.com.votacao.service.impl;

import br.com.votacao.exception.PautaNotFoundException;
import br.com.votacao.model.Pauta;
import br.com.votacao.repository.PautaRepository;
import br.com.votacao.service.PautaService;
import br.com.votacao.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;
    private final SessionServiceImpl sessaoServiceImpl;
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
        sessaoServiceImpl.deleteByPautaId(id);
        voteServiceImpl.deleteByPautaId(id);
    }

    @Override
    public Pauta findById(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(PautaNotFoundException::new);
    }
}
