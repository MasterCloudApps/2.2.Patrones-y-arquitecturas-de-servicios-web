package es.codeurjc.books;

@ApplicationModulesTest
public class ApplicationModulesTest {
    
    @Test
    void verifyModules() {
        var modules = ApplicationModules.of(Application.class).verify();

    }

}
