package org.cargain.ukc.profile.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Experience {
	
	private String title;
	private String company;
	private LocalDate startDate;
	private LocalDate endDate;
}
