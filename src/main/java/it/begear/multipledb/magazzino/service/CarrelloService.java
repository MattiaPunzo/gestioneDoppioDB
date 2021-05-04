package it.begear.multipledb.magazzino.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import it.begear.multipledb.magazzino.entity.Carrello;
import it.begear.multipledb.magazzino.repository.CarrelloRepository;

@Service
public class CarrelloService {

	@Autowired
	private CarrelloRepository carrelloRepository;

	public Carrello creazioneCarrello(Boolean fatturato) {
		Carrello carrello = new Carrello(null, fatturato);
		return carrelloRepository.save(carrello);
	}

	public List<Carrello> getListCarrello(Boolean fatturato) {
		Carrello carrello = new Carrello(null, fatturato);
		Example<Carrello> carrelloExample = Example.of(carrello);
		List<Carrello> listCarrello = carrelloRepository.findAll(carrelloExample);
		return listCarrello;
	}

	public Carrello chiusuraCarrello(Integer id_carrello) {
		Optional<Carrello> carrelloOp = carrelloRepository.findById(id_carrello);
		Boolean fatturato = false;
		if (carrelloOp.isPresent()) {
			Carrello carrello = carrelloOp.get();
			carrello.setFatturato(fatturato);
			return carrelloRepository.save(carrello);
		}
		return null;
	}
}
