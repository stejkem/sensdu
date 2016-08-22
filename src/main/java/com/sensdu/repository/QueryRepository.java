package com.sensdu.repository;

import org.springframework.data.jpa.repository.*;
import com.sensdu.domain.Query;

/**
 * Spring Data JPA repository for the Query entity.
 */
@SuppressWarnings("unused")
public interface QueryRepository extends JpaRepository<Query, Long> {
}
