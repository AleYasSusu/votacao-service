package br.com.votacao;

import br.com.votacao.service.VoteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableKafka
@EnableJpaRepositories
public class VotacaoApplication {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	private VoteService voteService;

	@Bean
	public void configureVoteService() {
		voteService.initialize();
	}

	public static void main(String[] args) {
		SpringApplication.run(VotacaoApplication.class, args);
	}
}
