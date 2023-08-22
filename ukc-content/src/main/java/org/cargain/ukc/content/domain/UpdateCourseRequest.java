package org.cargain.ukc.content.domain;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseRequest {

	private String title;
	@NotBlank
	private String description;
	private BigDecimal price;
}
