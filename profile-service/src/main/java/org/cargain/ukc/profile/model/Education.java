package org.cargain.ukc.profile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Education {
	
	private String degree;
	private String institution;
	private int graduationYear;
}
