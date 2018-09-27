package br.com.usp.lafieco.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import br.com.usp.lafieco.model.BlastResult;
import br.com.usp.lafieco.model.Sucest;
import br.com.usp.lafieco.model.SucestSequence;
import br.com.usp.lafieco.repository.BlastResultRepository;
import br.com.usp.lafieco.repository.SucestRepository;
import br.com.usp.lafieco.repository.SucestSequenceRepository;
import br.com.usp.lafieco.service.interfaces.IBlastService;
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
	private SucestSequenceRepository sucestSequenceRepository;

	@Autowired
	private IBlastService blastService;

	public RefineResultVO refineById(String id, String email) {

		RefineResultVO refineResult = new RefineResultVO();

		refineResult.setSucests(new ArrayList<Sucest>());

		Sucest sucest = sucestRepository.findByGene(id);
		
		if(sucest != null) {
			
			refineResult.getSucests().add(sucest);

		} else {
			
			List<BlastResult> blastResult = null;
			
			
			 blastResult = blastRepository.findByUniqueIdentifier(id);
			 
			 if(blastResult == null || blastResult.isEmpty()) {
				 
				 blastResult = blastRepository.findByGeneName(id);
			 }
			 
			 if(blastResult == null || blastResult.isEmpty()) {
				 
				 blastResult = blastRepository.findByEntryName(id);
			 }
			 
			 if(blastResult == null || blastResult.isEmpty()) {
				 
				 blastResult = blastRepository.findByProteinName(id);
			 }
			 
			 
			 if (blastResult != null && !blastResult.isEmpty()) {
					
				 Map<String, BlastResult> blastResultsWithoutRepeats = new HashMap<String, BlastResult>();

					for (BlastResult result : blastResult) {

						if (blastResultsWithoutRepeats.get(result.getSucestBusca()) == null) {

							blastResultsWithoutRepeats.put(result.getSucestBusca(), result);

							sucest = sucestRepository.findByGene(result.getSucestBusca());

							refineResult.getSucests().add(sucest);
						}
					}
				}
		}

		return refineResult;
	}

	public RefineResultVO refineSequence(String sequence, String email) {

		RefineResultVO refineResult = new RefineResultVO();

		refineResult.setSucests(new ArrayList<Sucest>());

		SucestSequence sucestSequence = sucestSequenceRepository.findBySequence(sequence);

		if (sucestSequence != null) {

			Sucest sucest = sucestRepository.findByGene(sucestSequence.getSucest().getGene());

			refineResult.getSucests().add(sucest);

		} else {

			// perform a blast
			List<BlastResult> blastResults = blastService.runBlastSequence(sequence, email);

			Map<String, BlastResult> blastResultsWithoutRepeats = new HashMap<String, BlastResult>();

			if (blastResults != null && !blastResults.isEmpty()) {

				for (BlastResult result : blastResults) {

					if (blastResultsWithoutRepeats.get(result.getSucestBusca()) == null) {

						blastResultsWithoutRepeats.put(result.getSucestBusca(), result);

						Sucest sucest = sucestRepository.findByGene(result.getSucestBusca());

						refineResult.getSucests().add(sucest);
					}
				}
			}

		}

		return refineResult;
	}

}
