package br.com.usp.lafieco.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.usp.lafieco.model.BlastResult;
import br.com.usp.lafieco.model.SucestSequence;

@Repository
public interface SucestSequenceRepository extends CrudRepository<SucestSequence, String>  {

	SucestSequence findBySequence(String sequence);  

}
