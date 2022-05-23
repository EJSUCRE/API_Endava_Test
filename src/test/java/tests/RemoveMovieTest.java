package tests;

import org.testng.annotations.Test;
import requests.PostRequests;

public class RemoveMovieTest {
    @Test
    public void RemoveMovie(){
        PostRequests postRequests = new PostRequests();
        postRequests.RemoveMovieToList(634649);//ID of the Movie to remove
    }
}
