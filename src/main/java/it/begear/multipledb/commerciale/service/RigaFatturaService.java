package it.begear.multipledb.commerciale.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.begear.multipledb.commerciale.entity.Fattura;
import it.begear.multipledb.commerciale.entity.RigaFattura;
import it.begear.multipledb.commerciale.repository.FatturaRepository;
import it.begear.multipledb.commerciale.repository.RigaFatturaRepository;
import it.begear.multipledb.magazzino.entity.ProdottoCarrello;
import it.begear.multipledb.magazzino.repository.ProdottoCarrelloRepository;

@Service
public class RigaFatturaService {

	@Autowired
	private RigaFatturaRepository rigaFatturaRepository;

	@Autowired
	private ProdottoCarrelloRepository prodCarRepository;

	@Autowired
	private FatturaRepository fatturaRepository;

	public RigaFattura aggiungiRigaFattura(Integer id_carrello, Integer id_fattura, Double prezzo, Integer quantity,
			Integer id_prodottocarrello) {
		Optional<Fattura> fatturaOp = fatturaRepository.findById(id_fattura);
		Optional<ProdottoCarrello> prodCarOp = prodCarRepository.findById(id_prodottocarrello);
		RigaFattura rigafattura = new RigaFattura();
		if (fatturaOp.isPresent() && prodCarOp.isPresent()) {
			Fattura fattura = fatturaOp.get();
			ProdottoCarrello prodCar = prodCarOp.get();
			Double prezzo_rigafattura = prezzo * prodCar.getQuantity();
			rigafattura.setMarca(prodCar.getProdotto().getMarca());
			rigafattura.setModello(prodCar.getProdotto().getModello());
			rigafattura.setFattura(fattura);
			rigafattura.setPrezzo(prezzo_rigafattura);
			rigafattura.setQuantity(prodCar.getQuantity());
			rigaFatturaRepository.save(rigafattura);
		}

		return rigafattura;

	}

}
