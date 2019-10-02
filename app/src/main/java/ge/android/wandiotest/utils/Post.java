package ge.android.wandiotest.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {

    @SerializedName("posts")
    @Expose
    private List<Post> posts;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("photoUrl")
    @Expose
    private String photoUrl;

    public Post(int id, String name, String message, String photoUrl) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.photoUrl = photoUrl;

    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }



}
