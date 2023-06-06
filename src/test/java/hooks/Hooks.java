package hooks;

import io.cucumber.java.Before;

import static base_Urls.MedunnaBaseUrl.setUp;

public class Hooks {


    @Before
    public void beforeApi(){

        setUp();

    }
}
