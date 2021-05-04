package it.begear.multipledb.commerciale.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fattura")
public class Fattura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_fattura", unique = true, nullable = false)
	private Integer idFattura;
	@Column(name = "prezzo_totale")
	private Double prezzo_totale;
	@Column(name = "data_fatturazione")
	private Date data_fatturazione;
	@Column(name = "id_carrello")
	private Integer id_carrello;
}
