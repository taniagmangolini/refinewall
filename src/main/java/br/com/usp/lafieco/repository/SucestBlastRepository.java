package br.com.usp.lafieco.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.usp.lafieco.model.Sucest;
import br.com.usp.lafieco.model.SucestBlast;

@Repository
public interface SucestBlastRepository extends CrudRepository<SucestBlast, Long>  {
	
	  List<SucestBlast> findBySucest(Sucest sucest);
	  
	  List<SucestBlast> findByKeywordsContaining(String keyword);
	  
}
