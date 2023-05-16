package de.woock.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@MappedSuperclass
public abstract class DomainObject implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Version
	private int version;
	
	@Temporal(TemporalType.DATE)
	@Column(updatable=false)
	private Date created;

	public DomainObject() {
		super();
		created=new Date();
	}
}
