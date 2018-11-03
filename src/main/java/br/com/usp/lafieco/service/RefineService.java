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

		if (sucest != null) {
			System.out.println("###sucest found! ");
		} else {
			System.out.println("###sucest not found! ");
		}

		if (sucest != null) {

			refineResult.getSucests().add(sucest);

		} else {

			List<BlastResult> blastResult = null;

			blastResult = blastRepository.findByUniqueIdentifier(id);

			if (blastResult == null || blastResult.isEmpty()) {

				blastResult = blastRepository.findByGeneName(id);
			}

			if (blastResult == null || blastResult.isEmpty()) {

				blastResult = blastRepository.findByEntryName(id);
			}

			if (blastResult == null || blastResult.isEmpty()) {

				blastResult = blastRepository.findByProteinName(id);
			}

			System.out.println("####blastResult " + blastResult);

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

			Map<String, List<BlastResult>> blastResultsOnDatabase = new HashMap<String, List<BlastResult>>();

			if (blastResults != null && !blastResults.isEmpty()) {

				Sucest genericSucest = null;

				for (BlastResult result : blastResults) {
					
					//get all blast results stored on the database by the unique id
					List<BlastResult> listBlastExistentOnDatabase = blastRepository
							.findByUniqueIdentifier(result.getUniqueIdentifier());

					//if exist some register on the refine database so the system will use this register
					if (listBlastExistentOnDatabase != null && !listBlastExistentOnDatabase.isEmpty()) {

						//iterate over register list to get the each  sucest related to the blast result from ncbi
						for (BlastResult blastOnDatabase : listBlastExistentOnDatabase) {
							
							//insert the sucest gene in the map and create a list to put the blast results related to it
							if (blastResultsOnDatabase.get(blastOnDatabase.getSucestBusca()) == null) {

								blastResultsOnDatabase.put(blastOnDatabase.getSucestBusca(),
										new ArrayList<BlastResult>());
							}
							
							//update the values according to the search on ncbi
							blastOnDatabase.setEvalue(result.getEvalue());
							blastOnDatabase.setGaps(result.getGaps());
							blastOnDatabase.setIdentities(result.getIdentities());
							blastOnDatabase.setPositives(result.getSequenceVersion());
							blastOnDatabase.setGeneName(result.getGeneName());
							blastOnDatabase.setOrganismIdentifier(result.getOrganismIdentifier());
							blastOnDatabase.setOrganismName(result.getOrganismName());
							blastOnDatabase.setScore(result.getScore());
							blastOnDatabase.setProteinName(result.getProteinName());
							blastOnDatabase.setProteinExistence(result.getProteinExistence());

							blastResultsOnDatabase.get(blastOnDatabase.getSucestBusca()).add(blastOnDatabase);
						}

					} else {

						if (genericSucest == null) {
							genericSucest = new Sucest();
							genericSucest.setGene("NOT_FOUND");
							genericSucest.setBlastResults(new ArrayList<BlastResult>());
						}

						// include results without any sucest related. The field sucestBusca is going to
						// be null
						genericSucest.getBlastResults().add(result);
					}
				}

				if (genericSucest != null) {
					refineResult.getSucests().add(genericSucest);
				}
			}

			if (blastResultsOnDatabase != null && !blastResultsOnDatabase.isEmpty()) {
				
				for (Map.Entry<String, List<BlastResult>> sucestAndBlastRelated : blastResultsOnDatabase.entrySet()) {
					
					Sucest sucest = sucestRepository.findByGene(sucestAndBlastRelated.getKey());	
					
					sucest.setBlastResults(new ArrayList<BlastResult>());
					
					for(BlastResult sucestBlast : sucestAndBlastRelated.getValue()) {
						sucest.getBlastResults().add(sucestBlast);
					}
					
					refineResult.getSucests().add(sucest);
				}
			}
		}

		return refineResult;
	}

}
