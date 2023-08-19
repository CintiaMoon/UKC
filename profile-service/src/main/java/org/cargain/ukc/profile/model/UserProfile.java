package org.cargain.ukc.profile.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "userProfiles")
public class UserProfile {

	@Id
	private String id;
	private String userId;
	private String bio;
	private List<Skill> skills;
	private List<Education> education;
	private List<Experience> experience;
}
