package br.com.usp.lafieco.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.usp.lafieco.model.SearchHistory;

@Repository
public interface SearchHistoryRepository extends CrudRepository<SearchHistory, Long>  {


}
