package br.com.votacao.repository;

import br.com.votacao.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByIdAndPautaId(Long id, Long pautaId);

    Long countByPautaId(Long id);

    @Modifying
    @Query("DELETE FROM Session s WHERE s.pauta.id = :pautaId")
    void deleteByPautaId(Long pautaId);

    Optional<List<Session>> findByPautaId(Long id);
}
