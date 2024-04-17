package br.com.votacao.service.impl;


import br.com.votacao.service.MockCpfService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MockCPFServiceImpl implements MockCpfService {
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


        //cpfs invalidos para voto
        cpfStatusMap.put("69750936019", "UNABLE_TO_VOTE");
        cpfStatusMap.put("27721533040", "UNABLE_TO_VOTE");
        cpfStatusMap.put("29794690090", "UNABLE_TO_VOTE");
        cpfStatusMap.put("45852616052", "UNABLE_TO_VOTE");
        cpfStatusMap.put("88115408018", "UNABLE_TO_VOTE");
        cpfStatusMap.put("86100647001", "UNABLE_TO_VOTE");

    }

    @Override
    public String checkVotingAbility(String cpf) {
        return cpfStatusMap.getOrDefault(cpf, "UNABLE_TO_VOTE");
    }
}
