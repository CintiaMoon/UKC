package org.cargain.ukc.profile.domain;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserProfileDTO {

	private String userId;
	
	@Schema(description = "A brief description or biography of the user")
	private String bio;
	
	@Schema(description = "Collection of Skill entities associated with the profile")
	private List<SkillDTO> skills;
	
	@Schema(description = "Collection of Education entities associated with the profile")
	private List<EducationDTO> education;
	
	@Schema(description = "Collection of Experience entities associated with the profile")
	private List<ExperienceDTO> experience;
}
