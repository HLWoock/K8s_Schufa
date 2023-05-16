package de.woock.service;


import static de.woock.config.MessagingConfig.EXCHANGE_NAME;
import static de.woock.config.MessagingConfig.ROUTNG_KEY;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import de.woock.entity.Anfrage;
import de.woock.entity.Antwort;
import de.woock.entity.Auftrag;
import de.woock.repository.AuftragRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class SchufaService {
	
	private final RabbitTemplate    rabbitTemplate;
	private final AuftragRepository auftragRepository;

	
	public void bearbeite(Anfrage anfrage) {
		log.info("bearbeite Anfrage fuer {}", anfrage.getName());
		speicherAnfrage(anfrage);
	}
	
	public void sendeAntwort(Antwort antwort) {
		rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTNG_KEY, antwort);
	}
	
	public void speicherAnfrage(Anfrage anfrage) {
		log.info("speicher Anfrage für {}", anfrage.getName());
		auftragRepository.save(new Auftrag(anfrage.getName()));
	}
}

