package ma.s2m.nxp.repository;

import ma.s2m.nxp.domain.ContactInstitution;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ContactInstitution entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactInstitutionRepository extends JpaRepository<ContactInstitution, Long> {}
