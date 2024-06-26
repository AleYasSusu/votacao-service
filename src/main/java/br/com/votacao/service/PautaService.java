package br.com.votacao.service;

import br.com.votacao.model.Pauta;

import java.util.List;

public interface PautaService {
    List<Pauta> findAllPautas();

    Pauta createNewPauta(Pauta pauta);


    Pauta findById(Long id);
}
