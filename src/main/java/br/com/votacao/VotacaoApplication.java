package br.com.votacao;

import br.com.votacao.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories
public class VotacaoApplication {
    @Autowired
    public VotacaoApplication(VoteService voteService) {
        this.voteService = voteService;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    private final VoteService voteService;

    @Bean
    public void configureVoteService() {
        voteService.initialize();
    }

    public static void main(String[] args) {
        SpringApplication.run(VotacaoApplication.class, args);
    }
}
