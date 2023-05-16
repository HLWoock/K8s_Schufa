package de.woock.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CONFLICT;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.QueueInformation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.woock.entity.Anfrage;
import de.woock.service.SchufaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SchufaRESTController {
	
	private final SchufaService schufaService;
	private final AmqpAdmin     amqpAdmin;
	
	@PostMapping("/anfrage")
	public ResponseEntity<Void> anfrage(@RequestBody Anfrage anfrage) {
		try {
			log.info("Anfrage für {} eingegangen", anfrage.getName());
			schufaService.bearbeite(anfrage);
			return new ResponseEntity<>(ACCEPTED);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(CONFLICT);
		}
	}
	
	@GetMapping("/queueinfo")
	public QueueInformation queueInformation() {
		return amqpAdmin.getQueueInfo("schufa");
	}
}
