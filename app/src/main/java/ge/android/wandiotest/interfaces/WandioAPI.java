package ge.android.wandiotest.interfaces;

import java.util.List;

import ge.android.wandiotest.utils.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface WandioAPI {

    String URL = "http://jsonstub.com/";
    String USER_KEY = "JsonStub-User-Key: a6504fb9-780f-483e-9021-19135c3cfc97";
    String PROJECT_KEY = "JsonStub-Project-Key: 58df32c7-1d41-4932-8f00-d2d4a61ae791";
    String CONTENT_TYPE = "Content-Type: application/json";
    String FEED = "feed";

    @GET(FEED)
    @Headers({
            USER_KEY,
            PROJECT_KEY,
            CONTENT_TYPE
    })

    Call<Post> getPosts();
}
