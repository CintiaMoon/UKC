package org.cargain.ukc.content.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Module")
@ToString
public class Module {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column
	private String title;
	
	private String description;

	@Min(1)
	private Integer position;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // One Module can have many Lessons
	@JoinColumn(name = "module_id") // "module_id" column will be created in "lesson" Table
	private Set<Lesson> lessons = new HashSet<>();

}
