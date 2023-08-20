package org.cargain.ukc.content.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseDTO {
	private long id;
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
}
