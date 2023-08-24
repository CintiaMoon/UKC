package org.cargain.ukc.content.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Course")
@ToString
public class Course {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
	
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	private Long authorId;
	private BigDecimal price;
	@NotNull
	private LocalDateTime startDate;
	@NotNull
	private LocalDateTime endDate;
	private LocalDateTime creationDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "courseId")
	private Set<Module> modules = new HashSet<>();

}
