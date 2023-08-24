package org.cargain.ukc.content.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateModuleRequest {
	@NotBlank
	private String title;
	private String description;
}
