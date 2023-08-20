package org.cargain.ukc.content.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;
    
    @NotBlank
    @Size(max = 1000)
    private String description;
    private Long authorId;
    private BigDecimal price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime creationDate;

}
