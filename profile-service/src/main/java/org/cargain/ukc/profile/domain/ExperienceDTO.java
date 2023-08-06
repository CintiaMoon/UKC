package org.cargain.ukc.profile.domain;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ExperienceDTO {

	@Schema(description = "The job title or position held.")
	private String title;
	
	@Schema(description = "The company or organization where the user gained the experience.")
    private String company;
	
	@Schema(description = "The start date of the experience")
    private LocalDate startDate;
	
	@Schema(description = "The end date of the experience")
    private LocalDate endDate;
}
