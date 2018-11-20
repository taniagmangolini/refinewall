package br.com.usp.lafieco.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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


    /**
     * Search sucest gene or unique identifier on refine database
     *
     * @param id  id to be searched
     * @param email user's email
     * @return refineResult object that contains a sucest list with the blast results associated.
     */
	public RefineResultVO refineById(String id, String email) {

		RefineResultVO refineResult = new RefineResultVO();

		refineResult.setSucests(new ArrayList<Sucest>());

		refineResult.setBlastResults(new ArrayList<BlastResult>());

		Sucest sucest = sucestRepository.findByGene(id);

		if (sucest != null) {

			List<BlastResult> sucestBlasts = blastRepository.findBySucestBusca(sucest.getGene());

			if (sucestBlasts != null && !sucestBlasts.isEmpty()) {

				sucest.setBlastResults(sucestBlasts);

			}

			refineResult.getSucests().add(sucest);

		} else {

			List<BlastResult> blastResult = blastRepository.findByUniqueIdentifier(id);

			if (blastResult != null && !blastResult.isEmpty()) {

				for (BlastResult result : blastResult) {

					refineResult.getBlastResults().add(result);

					sucest = sucestRepository.findByGene(result.getSucestBusca());

					if (sucest != null) {

						sucest.setBlastResults(new ArrayList<BlastResult>());

						sucest.getBlastResults().add(result);

						refineResult.getSucests().add(sucest);
					}

				}
			}
		}

		return refineResult;
	}
	
    /**
     * Search a specific sucest gene protein sequence  on refine database.
     * If the sucest is not stored on the refine database the system will perform a blast
     * to return the proteins associated to the sequence, and after that it will
     * find sucest genes related to the top 25 first blast results. The other results will appear
     * without any sucest gene associated and the user will have to search individually each unique identifier 
     * that he or she wants to see the sucests related.
     * @param sequence  sequence to be searched
     * @param email user's email. A valid email is necessary to perform a blast on ncbi.
     * @return refineResult 
     */
	public RefineResultVO refineSequence(String sequence, String email) {
		return refineSequence( sequence,  email,  null) ;
	}


    /**
     * Search a specific sucest gene protein sequence  on refine database.
     * If the sucest is not stored on the refine database and a jobId is not provided the system will perform a blast
     * to return the proteins associated to the sequence. After that it will
     * find sucest genes related to the top 20 first blast results. The other results will appear
     * without any sucest gene associated and the user will have to search individually each unique identifier 
     * that he or she wants to see the sucests related.
     * @param sequence  sequence to be searched
     * @param jobId  jobId of the blast
     * @param email user's email. A valid email is necessary to perform a blast on ncbi.
     * @return refineResult 
     */
	public RefineResultVO refineSequence(String sequence, String email, String jobId) {

		RefineResultVO refineResult = new RefineResultVO();
		
		Sucest genericSucest = null;

		refineResult.setSucests(new ArrayList<Sucest>());

		SucestSequence sucestSequence = sucestSequenceRepository.findBySequence(sequence);

		if (sucestSequence != null) {

			Sucest sucest = sucestRepository.findByGene(sucestSequence.getSucest().getGene());

			List<BlastResult> sucestBlasts = blastRepository.findBySucestBusca(sucest.getGene());

			if (sucestBlasts != null && !sucestBlasts.isEmpty()) {

				sucest.setBlastResults(sucestBlasts);
			}

			refineResult.getSucests().add(sucest);

		} else {

			// perform a blast
			List<BlastResult> blastResults = blastService.runBlastSequence(sequence, email, jobId);

			Map<String, List<BlastResult>> blastResultsOnDatabase = null;

			if (blastResults != null && !blastResults.isEmpty()) {

				blastResultsOnDatabase = new HashMap<String, List<BlastResult>>();

				Integer count = 0;

				BigDecimal limitEvalue = new BigDecimal("1e-15").setScale(20, BigDecimal.ROUND_HALF_EVEN);

				Integer limitSize = 20;

				for (BlastResult result : blastResults) {

					List<BlastResult> listBlastExistentOnDatabase = null;

					BigDecimal evalue = null;

					try {
						evalue = new BigDecimal(result.getEvalue()).setScale(20, BigDecimal.ROUND_HALF_EVEN);
					} catch (RuntimeException e) {

					}

					if (evalue != null && evalue.compareTo(limitEvalue) < 1 && count <= limitSize
							&& result.getUniqueIdentifier() != null
							&& !result.getUniqueIdentifier().equalsIgnoreCase("")) {

						listBlastExistentOnDatabase = blastRepository
								.findByUniqueIdentifier(result.getUniqueIdentifier());

					}

					// iterate over the blast registers on the the refine database
					if (listBlastExistentOnDatabase != null && !listBlastExistentOnDatabase.isEmpty()) {

						count = count + 1;

						for (BlastResult register : listBlastExistentOnDatabase) {

							if (blastResultsOnDatabase.get(register.getSucestBusca()) == null) {

								blastResultsOnDatabase.put(register.getSucestBusca(), new ArrayList<BlastResult>());
							}

							blastResultsOnDatabase.get(register.getSucestBusca()).add(result);
						}

					} else {

						if (genericSucest == null) {
							genericSucest = new Sucest();
							genericSucest.setId((long) 0);
							genericSucest.setGene("NOT_FOUND");
							genericSucest.setDomainsString("NA");
							genericSucest.setDescription("-");
							genericSucest.setBlastResults(new ArrayList<BlastResult>());
						}

						// Also include results without any sucest related.
						if(result.getUniqueIdentifier() != null) {
							genericSucest.getBlastResults().add(result);
						}
					}
				}	

			}

			if (blastResultsOnDatabase != null && !blastResultsOnDatabase.isEmpty()) {

				for (Map.Entry<String, List<BlastResult>> sucestAndBlastRelated : blastResultsOnDatabase.entrySet()) {

					Sucest sucest = sucestRepository.findByGene(sucestAndBlastRelated.getKey());

					sucest.setBlastResults(new ArrayList<BlastResult>());

					for (BlastResult sucestBlast : sucestAndBlastRelated.getValue()) {

						Boolean exists = false;
						for (BlastResult blastR : sucest.getBlastResults()) {
							if (blastR.getUniqueIdentifier().equalsIgnoreCase(sucestBlast.getUniqueIdentifier())) {
								exists = true;
								break;
							}
						}
						
						if (!exists) {
							
							BlastResult blastCopy = new BlastResult();
							blastCopy.setEntryName(sucestBlast.getEntryName());
							blastCopy.setEvalue(sucestBlast.getEvalue());
							blastCopy.setScore(sucestBlast.getScore());
							blastCopy.setProteinName(sucestBlast.getProteinName());
							blastCopy.setSucestBusca(sucest.getGene());
							blastCopy.setUniqueIdentifier(sucestBlast.getUniqueIdentifier());
							
							sucest.getBlastResults().add(blastCopy);

						}
					}

					refineResult.getSucests().add(sucest);

				}
			}
			
			if (genericSucest != null ) {

				refineResult.getSucests().add(genericSucest);
			}

		}
		
		System.out.println("refine by seq completed");

		return refineResult;
	}

}
