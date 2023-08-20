package org.cargain.ukc.commons.events.content;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.cargain.ukc.commons.events.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreatedEvent implements Event{

    private Long id;

    private String title;    

    private String description;
    private Long authorId;
    private BigDecimal price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime creationDate;
}
