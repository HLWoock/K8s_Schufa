package de.woock.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import de.woock.entity.Antwort;
import de.woock.entity.Bonitaet;

@Service
public class BonitaetService {

	private Random random = new Random();
	
	public Antwort berechneBonitaet(String name) {
		Bonitaet bonitaet =  switch (random.nextInt(10)) {	
        case 0, 1          -> Bonitaet.SEHR_GUT;
        case 2, 3, 4, 5, 6 -> Bonitaet.GUT;
        case 7, 8          -> Bonitaet.SCHLECHT;
        case 9             -> Bonitaet.SEHR_SCHLECHT;
	    default            -> throw new IllegalArgumentException("Unexpected value:");
	};
		return new Antwort(bonitaet, name);
	}
}
