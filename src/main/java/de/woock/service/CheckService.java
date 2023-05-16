package de.woock.service;

import static java.util.concurrent.TimeUnit.MINUTES;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import de.woock.entity.Antwort;
import de.woock.repository.AuftragRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class CheckService {
	
	private final AuftragRepository auftragRepository;
	private final SchufaService     schufaService;
	private final BonitaetService   bonitaetService;
	
	@Scheduled(fixedRate = 1, timeUnit = MINUTES)
	private void bearbeiteAuftraege() {
		log.info("suche nach Auftraegen...");
		auftragRepository.findAll()
		                 .forEach(auftrag -> {
		                	 log.info("berechne Bonitaet für {}", auftrag.getName());
		                	 String name = auftrag.getName();
		                	 Antwort antwort = bonitaetService.berechneBonitaet(name);
		                	 log.info("Bonitaet für {}: {}", antwort.name(), antwort.bonitaet());
		                	 log.info("sende Anwort für {}", antwort.name());
		                	 schufaService.sendeAntwort(antwort);
		                	 log.info("loesche Mitglied {}", name);
		                	 auftragRepository.delete(auftrag);
		                 });
	}
}
