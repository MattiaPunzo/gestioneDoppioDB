package it.begear.multipledb.commerciale.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.begear.multipledb.commerciale.entity.Fattura;
import it.begear.multipledb.commerciale.repository.FatturaRepository;
import it.begear.multipledb.magazzino.entity.Carrello;
import it.begear.multipledb.magazzino.repository.CarrelloRepository;
import it.begear.multipledb.magazzino.service.CarrelloService;
import it.begear.multipledb.magazzino.service.ProdottoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FatturaService {

	@Autowired
	private CarrelloRepository carrelloRepository;

	@Autowired
	private FatturaRepository fatturaRepository;

	@Autowired
	private ProdottoService prodottoService;

	@Autowired
	private CarrelloService carrelloService;

	public Fattura aggiungiFattura(Integer id_carrello) {
		Optional<Carrello> carrelloOp = carrelloRepository.findById(id_carrello);
		Double prezzo_totale = prodottoService.getPrezzoTotaleCarrello(id_carrello);
		Fattura fattura = new Fattura();
		if (carrelloOp.isPresent()) {
			Carrello carrello = carrelloOp.get();
			carrello = carrelloService.chiusuraCarrello(id_carrello);
			fattura.setId_carrello(carrello.getIdCarrello());
			fattura.setData_fatturazione(new Date());
			fattura.setPrezzo_totale(prezzo_totale);
			fatturaRepository.save(fattura);
			log.info("salvataggio effettuato " + fattura.getId_carrello());
		}
		return fattura;
	}
}
