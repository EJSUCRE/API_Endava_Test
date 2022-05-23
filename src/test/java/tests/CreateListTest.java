package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import requests.GetRequests;
import requests.PostRequests;

public class CreateListTest {

    @BeforeTest
    public void SetUp(){//Prepare all the variables for the execution
        GetRequests getRequests = new GetRequests();
        getRequests.getToken();
        PostRequests postRequests = new PostRequests();
        postRequests.getLoginWithLogin();
        postRequests.createSession();
    }

    @Test(priority = 0)
    public void CreateList(){
        PostRequests postRequests = new PostRequests();
        postRequests.createList("TestAUT List last project ","TestAUT List Description","en");//Name, Description and Language of the List to create
    }
}
