package it.begear.multipledb.commerciale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.begear.multipledb.commerciale.entity.Fattura;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Integer> {

}
