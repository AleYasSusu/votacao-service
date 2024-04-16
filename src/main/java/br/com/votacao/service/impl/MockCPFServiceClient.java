package br.com.votacao.service.impl;

import java.util.HashMap;
import java.util.Map;

public class MockCPFServiceClient {
    private static final Map<String, String> cpfStatusMap = new HashMap<>();

    static {
        // Configurar o status para os CPFs válidos
        cpfStatusMap.put("86130113064", "ABLE_TO_VOTE");
        cpfStatusMap.put("22226377085", "ABLE_TO_VOTE");
        cpfStatusMap.put("46575609058", "ABLE_TO_VOTE");
        cpfStatusMap.put("87273338095", "ABLE_TO_VOTE");
        cpfStatusMap.put("86702142021", "ABLE_TO_VOTE");
        cpfStatusMap.put("22210459044", "ABLE_TO_VOTE");
        cpfStatusMap.put("48105049058", "ABLE_TO_VOTE");
        cpfStatusMap.put("92997746094", "ABLE_TO_VOTE");

        // Configurar o status para os CPFs inválidos
        cpfStatusMap.put("24491147000", "UNABLE_TO_VOTE");
        cpfStatusMap.put("57146047091", "UNABLE_TO_VOTE");
        // Outros CPFs inválidos...
    }

    public static String checkVotingAbility(String cpf) {
        // Simular a resposta do serviço externo com base no CPF fornecido
        return cpfStatusMap.getOrDefault(cpf, "UNABLE_TO_VOTE");
    }
}
