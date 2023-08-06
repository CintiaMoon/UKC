package org.cargain.ukc.profile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EducationDTO {
	@Schema(description = "The degree or qualification obtained")
	private String degree;
	
	@Schema(description = "The educational institution where the degree was earned")
	private String institution;
	
	@Schema(description = "The year of graduation")
	private int graduationYear;
}
