package br.com.usp.lafieco.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.usp.lafieco.model.BlastResult;
import br.com.usp.lafieco.model.Sucest;
import br.com.usp.lafieco.model.SucestSequence;

@Repository
public interface SucestRepository extends CrudRepository<Sucest, String>  {

	Sucest findByGene(String gene);  


}
