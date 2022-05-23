package tests;

import org.testng.annotations.Test;
import requests.PostRequests;

public class AddMovieTest {

    @Test
    public void AddMovie(){
        PostRequests postRequests = new PostRequests();
        postRequests.AddMovieToList(634649);//ID of the Movie to add
    }
}
