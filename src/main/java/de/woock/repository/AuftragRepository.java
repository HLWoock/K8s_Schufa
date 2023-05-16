package de.woock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.woock.entity.Auftrag;

public interface AuftragRepository extends JpaRepository<Auftrag, Long>{

}
