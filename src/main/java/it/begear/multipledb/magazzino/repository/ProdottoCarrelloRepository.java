package it.begear.multipledb.magazzino.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.begear.multipledb.magazzino.entity.ProdottoCarrello;

@Repository
public interface ProdottoCarrelloRepository extends JpaRepository<ProdottoCarrello, Integer> {
	
	public List<ProdottoCarrello> findByCarrelloIdCarrello(Integer id_carrello);

}
