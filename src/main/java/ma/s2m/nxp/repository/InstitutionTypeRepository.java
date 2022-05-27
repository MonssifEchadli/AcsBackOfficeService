package ma.s2m.nxp.repository;

import ma.s2m.nxp.domain.InstitutionType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the InstitutionType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstitutionTypeRepository extends JpaRepository<InstitutionType, Long> {}
