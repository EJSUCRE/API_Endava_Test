package Test;

import Requests.DeleteRequests;
import Requests.GetRequests;
import Requests.PostRequests;
import org.testng.annotations.Test;

public class MainClassTest {
    @Test (priority = 0)
    public void Token(){
        GetRequests getRequests = new GetRequests();
        getRequests.getToken();
    }

    @Test (priority = 1)
    public void LoginSession(){
        PostRequests postRequests = new PostRequests();
        postRequests.getLoginWithLogin();
    }

    @Test (priority = 2)
    public void CreateSession(){
        PostRequests postRequests = new PostRequests();
        postRequests.createSession();
    }

    @Test (priority = 3)
    public void CreateList(){
    PostRequests postRequests = new PostRequests();
    postRequests.createList();
    }

    @Test (priority = 4)
    public void DeleteList(){
        DeleteRequests deleteRequests = new DeleteRequests();
        deleteRequests.deleteList();
    }
}