package apiTestUsingRestAssured;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class SocialNetworkingComment {
    @Test
    public void getCommentForSocialNetworking() {
        given().contentType(ContentType.JSON).log().all().
                when().get("https://jsonplaceholder.typicode.com/comments/5").
                then().log().all().statusCode(200).
                body("name", is("vero eaque aliquid doloribus et culpa")).
                body("email", is("Hayden@althea.biz")).
                body("body", is("harum non quasi et ratione\ntempore iure ex voluptates in ratione\nharum architecto fugit inventore cupiditate\nvoluptates magni quo et")).log().all();
    }

    @Test
    public void postAComment() {
        HashMap<String, String> postBody = new HashMap<>();
        postBody.put("postId", "1");
        postBody.put("name", "My first comment");
        postBody.put("email", "lateef.abdulsalam@sydney.com");
        postBody.put("body", "I like the post make by my best friend");


        given().contentType(ContentType.JSON).log().all().
                with().body(postBody).
                when().post("https://jsonplaceholder.typicode.com/comments").
                then().log().all().statusCode(201).
                body("name", is("My first comment")).
                body("email", is("lateef.abdulsalam@sydney.com")).
                body("id", is(501));
    }


}

