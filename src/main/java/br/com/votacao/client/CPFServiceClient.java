package br.com.votacao.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cpf-service", url = "https://user-info.herokuapp.com")
public interface CPFServiceClient {

    @GetMapping("/users/{cpf}")
    String checkVotingAbility(@PathVariable("cpf") String cpf);
}
