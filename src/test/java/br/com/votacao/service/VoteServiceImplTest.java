package br.com.votacao.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class VoteServiceImplTest {
/**
	private VoteServiceImpl voteServiceImpl;
	@Mock
	private VotoRepository votoRepository;
	@Mock
	private RestTemplate restTemplate;
	@Mock
	private KafkaSender kafkaSender;
	@Mock
	private VotacaoService votacaoService;
	@Mock
	private SessaoService sessaoService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		/**	voteServiceImpl = new VoteServiceImpl(restTemplate, votoRepository, kafkaSender, sessaoService, votacaoService);
	}

	/**@Test(expected = SessaoTimeOutException.class)
	public void verifyVotoTest() {
		Sessao sessao = new Sessao();
		sessao.setDataInicio(LocalDateTime.now());
		sessao.setMinutosValidade(-1L);

		Voto voto = new Voto();
		Pauta pauta = new Pauta();
		pauta.setId(1L);
		voto.setPauta(pauta);

		when(votacaoService.buildVotacaoPauta(anyLong())).thenReturn(VotacaoDto.builder().build());

		voteServiceImpl.verifyVoto(sessao, voto);
	}

	@Test(expected = InvalidCpfException.class)
	public void cpfAbleToVoteTest() {
		Voto voto = new Voto();
		voto.setCpf("1234");

		CpfValidationDto cpf = new CpfValidationDto();
		cpf.setStatus("TESTE");

		ResponseEntity<CpfValidationDto> response = new ResponseEntity<>(cpf, HttpStatus.NOT_FOUND);

		when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(CpfValidationDto.class)))
				.thenReturn(response);

		voteServiceImpl.cpfAbleToVote(voto);
	}

	@Test(expected = UnableCpfException.class)
	public void cpfAbleToVote2Test() {
		Voto voto = new Voto();
		voto.setCpf("1234");

		CpfValidationDto cpf = new CpfValidationDto();
		cpf.setStatus("UNABLE_TO_VOTE");

		ResponseEntity<CpfValidationDto> response = new ResponseEntity<>(cpf, HttpStatus.OK);

		when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(CpfValidationDto.class)))
				.thenReturn(response);

		voteServiceImpl.cpfAbleToVote(voto);
	}

	@Test
	public void cpfAbleToVote3Test() {
		Voto voto = new Voto();
		voto.setCpf("1234");

		CpfValidationDto cpf = new CpfValidationDto();
		cpf.setStatus("ABLE_TO_VOTE");

		ResponseEntity<CpfValidationDto> response = new ResponseEntity<>(cpf, HttpStatus.OK);

		when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(CpfValidationDto.class)))
				.thenReturn(response);

		voteServiceImpl.cpfAbleToVote(voto);
	}

	@Test(expected = VotoAlreadyExistsException.class)
	public void votoAlreadyExistsTest() {
		Voto voto = new Voto();
		voto.setCpf("1234");
		Pauta pauta = new Pauta();
		pauta.setId(1L);
		voto.setPauta(pauta );
		when(votoRepository.findByCpfAndPautaId(anyString(), anyLong())).thenReturn(Optional.of(new Voto()));
		voteServiceImpl.votoAlreadyExists(voto);
	}
	
	@Test
	public void votoAlreadyExistssTest() {
		Voto voto = new Voto();
		voto.setCpf("1234");
		Pauta pauta = new Pauta();
		pauta.setId(1L);
		voto.setPauta(pauta );
		
		when(votoRepository.findByCpfAndPautaId(anyString(), anyLong())).thenReturn(Optional.empty());
		voteServiceImpl.votoAlreadyExists(voto);
	}
		 **/
}
