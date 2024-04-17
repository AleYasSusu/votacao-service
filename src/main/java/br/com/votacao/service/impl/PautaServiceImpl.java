package br.com.votacao.service.impl;

import br.com.votacao.exception.PautaNotFoundException;
import br.com.votacao.repository.PautaRepository;
import br.com.votacao.model.Pauta;
import br.com.votacao.service.PautaService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {

    private  final PautaRepository pautaRepository;

    @Override
    public List<Pauta> findAllPautas() {
        return pautaRepository.findAll();
    }

    @Override
    public Pauta createNewPauta(final Pauta pauta) {
        return pautaRepository.save(pauta);
    }



    @Override
    public Pauta findById(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(PautaNotFoundException::new);
    }
}
