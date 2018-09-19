package br.com.usp.lafieco.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import br.com.usp.lafieco.model.Sucest;
import br.com.usp.lafieco.model.SucestSequence;
import br.com.usp.lafieco.repository.BlastResultRepository;
import br.com.usp.lafieco.repository.SucestRepository;
import br.com.usp.lafieco.repository.SucestSequenceRepository;
import br.com.usp.lafieco.service.interfaces.IRefineService;
import br.com.usp.lafieco.vo.RefineResultVO;

@Component
public class RefineService implements IRefineService {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BlastResultRepository blastRepository;

	@Autowired
	private SucestRepository sucestRepository;
	
	@Autowired
	private SucestSequenceRepository sucestSequenceRepository ;
	
	public RefineResultVO refineSequence(String sequence, String email) {
		
		RefineResultVO refineResult = null;
		
		SucestSequence sucestSequence = sucestSequenceRepository.findBySequence(sequence);
		
		if(sucestSequence != null) {
			
			Sucest sucest = sucestRepository.findByGene(sucestSequence.getSucest().getGene());
			
			refineResult = new RefineResultVO();
			
			refineResult.setSucests(new ArrayList<Sucest>());
			
			refineResult.getSucests().add(sucest);
		}
		
		return refineResult;
	}

}
