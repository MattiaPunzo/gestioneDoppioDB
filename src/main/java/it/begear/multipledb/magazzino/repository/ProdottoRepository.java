package it.begear.multipledb.magazzino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.begear.multipledb.magazzino.entity.Prodotto;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {

}
