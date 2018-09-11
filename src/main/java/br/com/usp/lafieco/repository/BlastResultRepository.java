package br.com.usp.lafieco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.usp.lafieco.model.BlastResult;


@Repository
public interface BlastResultRepository extends JpaRepository<BlastResult, Long> {

}
