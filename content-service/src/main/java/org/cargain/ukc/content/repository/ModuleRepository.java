package org.cargain.ukc.content.repository;

import java.util.Optional;

import org.cargain.ukc.content.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    Optional<Module> findByIdAndCourseId(Long id, Long courseId);
}
