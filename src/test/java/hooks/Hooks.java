package hooks;

import io.cucumber.java.Before;

import static base_Urls.MedunnaBaseUrl.setUp;

public class Hooks {


    @Before ("@api")    // @Before ("@api") seklinde yaparsak sadece @api taglilerde onceden calisir, digerlerinde bu metot calismaz
    public void beforeApi(){

        setUp();

    }
}
