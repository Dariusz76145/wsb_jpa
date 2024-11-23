package com.jpacourse.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "MEDICAL_TREATMENT")
public class MedicalTreatmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VISIT_ID", nullable = false)
	// Jednostronna relacja: MedicalTreatmentEntity jako dziecko
	private VisitEntity visitEntity;

	// Gettery i settery
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public VisitEntity getVisitEntity() {
		return visitEntity;
	}

	public void setVisitEntity(VisitEntity visitEntity) {
		this.visitEntity = visitEntity;
	}
}
