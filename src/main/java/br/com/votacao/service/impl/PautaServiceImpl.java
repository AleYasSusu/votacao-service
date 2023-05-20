package br.com.votacao.service.impl;

import br.com.votacao.exception.PautaNotFoundException;
import br.com.votacao.model.Pauta;
import br.com.votacao.repository.PautaRepository;
import br.com.votacao.service.PautaService;
import br.com.votacao.service.SessionService;
import br.com.votacao.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PautaServiceImpl implements PautaService {

    private  PautaRepository pautaRepository;
    private SessionService sessaoService;
    private VoteService voteService;

    @Autowired
    public PautaServiceImpl(PautaRepository pautaRepository,
                            @Lazy SessionService sessaoService,
                            @Lazy VoteService voteService) {
        this.pautaRepository = pautaRepository;
        this.sessaoService = sessaoService;
        this.voteService = voteService;
    }



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
        var staffById = pautaRepository.findById(id)
                .orElseThrow(PautaNotFoundException::new);
        pautaRepository.delete(staffById);
    }

    @Override
    public Pauta findById(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(PautaNotFoundException::new);
    }
}
