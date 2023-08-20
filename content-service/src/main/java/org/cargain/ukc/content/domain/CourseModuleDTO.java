package org.cargain.ukc.content.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseModuleDTO {

	private long id;
	
	@NotNull
	private Long courseId;

	@NotBlank
	private String title;

	private String description;

	@Min(1)
	private Integer position;
}
