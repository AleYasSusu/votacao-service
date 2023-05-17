package br.com.votacao.repository;

import br.com.votacao.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByCpf(String cpf);

    Optional<List<Vote>> findByPautaId(Long id);

    Optional<Vote> findByCpfAndPautaId(String cpf, Long id);
}
