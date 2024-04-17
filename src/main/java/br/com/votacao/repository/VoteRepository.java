package br.com.votacao.repository;

import br.com.votacao.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    boolean existsByCpfAndSessionId(String cpf, Long sessionId);

    List<Vote> findBySessionId(Long sessionId);
}
