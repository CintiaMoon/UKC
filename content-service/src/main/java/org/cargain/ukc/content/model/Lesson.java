package org.cargain.ukc.content.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = "module")
@EqualsAndHashCode(exclude = "module")
@Entity
@Table(name = "lesson")
public class Lesson {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String title;
	private String content;

	// The order in which the module appears within the module.
	private Long position;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module_id")
	private Module module;

	public void addModule(Module module) {
		this.module = module;
		module.getLessons().add(this);
	}

	public void removeCourse() {
		this.module.getLessons().remove(this);
		this.module = null;
	}
}
