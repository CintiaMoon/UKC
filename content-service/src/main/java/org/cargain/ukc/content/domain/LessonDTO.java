package org.cargain.ukc.content.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LessonDTO {
	@NotNull
	private Long moduleId;

    @NotBlank
    private String title;

    private String content;

    @Min(1)
    private int position;
}
