package ma.s2m.nxp.repository;

import ma.s2m.nxp.domain.Institution;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

/**
 * Spring Data SQL repository for the Institution entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstitutionRepository extends PagingAndSortingRepository<Institution, Long> {
}
