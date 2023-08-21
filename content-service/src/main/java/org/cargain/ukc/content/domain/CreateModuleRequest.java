package org.cargain.ukc.content.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateModuleRequest {

	@NotBlank
	private String title;

	private String description;

	@Min(1)
	private Integer position;
}
