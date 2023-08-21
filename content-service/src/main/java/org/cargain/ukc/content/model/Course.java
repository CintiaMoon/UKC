package org.cargain.ukc.content.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = "modules")
@EqualsAndHashCode(exclude = "modules")
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    
    //relationships
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Module> modules =  new ArrayList<>();
}
