package br.com.usp.lafieco.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.usp.lafieco.model.BlastResult;

@Repository
public interface BlastResultRepository extends CrudRepository<BlastResult, Long>  {

	  BlastResult findByUniqueIdentifierAndSucestBusca(String uniqueIdentifier, String sucestBusca);  
	  
	  List<BlastResult> findByUniqueIdentifier(String uniqueIdentifier);  
	  
	  List<BlastResult> findByEntryName(String entryName);  

	  List<BlastResult> findByProteinName(String proteinName);  

	  List<BlastResult> findByGeneName(String geneName);  
	  
}
