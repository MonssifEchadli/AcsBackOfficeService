package ma.s2m.nxp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("ma.s2m.nxp");

        noClasses()
            .that()
            .resideInAnyPackage("ma.s2m.nxp.service..")
            .or()
            .resideInAnyPackage("ma.s2m.nxp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..ma.s2m.nxp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
