package com.freenow.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;


public class HelperMethods {

        private static final String BaseUrl= "https://jsonplaceholder.typicode.com";


        public static Integer searchUser(String searchUserName)  {
            /*
             * Get the UserId for a particular user given the userName as input argument
             * from the '/users' API response content
             *
             */

            //Make the RestAssured call
            Response response =

                    given()
                            .queryParam("username",searchUserName)
                            .baseUri(BaseUrl).
                    when()
                            .get("/users").
                    then()
                            .extract().response();

            Assert.assertTrue(response.getStatusCode()==200);


            JsonPath jsonPath = new JsonPath(response.getBody().asString());
            List<Integer> getUserId = jsonPath.getList("id");
            List<Object> getUserName = jsonPath.getList("username");

            Assert.assertTrue(getUserName.size()!=0,"User not found");

                Assert.assertEquals(getUserName.get(0).toString(), searchUserName);
                return getUserId.get(0);
        }


        public static ArrayList<Integer> getPosts(int searchUserId) {
            /*
             * Get the PostId for a particular user given the userName as input argument
             * from the '/posts' API response content
             */



            //Make the RestAssured call
            Response response =

                    given()
                            .queryParam("userId",searchUserId)
                            .baseUri(BaseUrl).
                    when()
                            .get("/posts").
                    then()
                            .extract().response();

            Assert.assertTrue(response.getStatusCode()==200);


            JsonPath jsonPath = new JsonPath(response.getBody().asString());
            List<Integer> getPostId = jsonPath.getList("id");
            Assert.assertFalse(getPostId.equals(""),"PostId not foud");

            ArrayList<Integer> emailList = new ArrayList<>();
            for (int getId : getPostId)
            {
               emailList.add(getId);
            }


            return emailList;

        }


    public static ArrayList<String> getComments(ArrayList<Integer> searchPosts) throws MalformedURLException {
        /*
         * Get the List of Comments for a particular user's posts given their postIds as
         * input argument from the '/comments' API response content
         */
        ArrayList<String> emailList = new ArrayList<>();

        for (int i = 0;i<searchPosts.size();i++) {
            //Make the RestAssured call
            Response response =

                    given()
                            .queryParam("postId", searchPosts.get(i))
                            .baseUri(BaseUrl).
                    when()
                            .get("/comments").
                    then()
                            .extract().response();

            Assert.assertTrue(response.getStatusCode() == 200);


            JsonPath jsonPath = new JsonPath(response.getBody().asString());
            List<String> getComments = jsonPath.getList("body");

            for (String getId : getComments) {
                emailList.add(i,getId);
                System.out.println("*******Comment for post Id= "+searchPosts.get(i) +" is :" + emailList.get(i)+"\n");
            }
        }

        return emailList;

    }

    public static ArrayList<String> verifyEmail(ArrayList<Integer> searchPosts) throws MalformedURLException {
        /*
         * Get the List of Email Addresses for a particular user's posts given their
         * postIds as input argument from the '/comments' API response content and verify it against the standard email format
         */
        ArrayList<String> emailList = new ArrayList<>();

        for (int i = 0;i<searchPosts.size();i++) {
            //Make the RestAssured call
            Response response =

                    given()
                            .queryParam("postId", searchPosts.get(i))
                            .baseUri(BaseUrl).
                    when()
                            .get("/comments").
                    then()
                            .extract().response();

            Assert.assertTrue(response.getStatusCode() == 200);


            JsonPath jsonPath = new JsonPath(response.getBody().asString());
            List<String> getComments = jsonPath.getList("email");


            for (String getId : getComments) {
                emailList.add(i,getId);
                Pattern VALID_EMAIL_ADDRESS_REGEX =
                        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

                    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailList.get(i));
                    Assert.assertTrue(matcher.find());
                    System.out.println("*******EmailId "+emailList.get(i)+" for post Id= "+searchPosts.get(i) +" is validated"  );
                }
            }


        return emailList;

    }


    public static void postUser(String searchUserName) throws MalformedURLException {
        /*
            users apiMethod status code is not Equal to 200 for a post method
         */

        //Make the RestAssured call
        Response response =

                given()
                        .queryParam("username",searchUserName)
                        .baseUri(BaseUrl).
                when()
                        .post("/users").
                then()
                        .extract().response();

        Assert.assertTrue(response.getStatusCode()!=200);
        System.out.println("POST Method not allowed for /users endpoint");

    }
    public static void putUser(String searchUserName) throws MalformedURLException {
        /*
            users apiMethod status code is not Equal to 200 for a put method
         */

        //Make the RestAssured call
        Response response =

                given()
                        .queryParam("username",searchUserName)
                        .baseUri(BaseUrl).
                when()
                        .put("/users").
                then()
                        .extract().response();

        Assert.assertTrue(response.getStatusCode()!=200);
        System.out.println("PUT Method not allowed for /users endpoint");

    }

    public static void deleteUser(String searchUserName) throws MalformedURLException {
         /*
            users apiMethod status code is not Equal to 200 for a delete method
         */

        //Make the RestAssured call
        Response response =

                given()
                        .queryParam("username",searchUserName)
                        .baseUri(BaseUrl).
                when()
                        .delete("/users").
                then()
                        .extract().response();

        Assert.assertTrue(response.getStatusCode()!=200);
        System.out.println("DELETE Method not allowed for /users endpoint");

    }

    public static void deletePost(String searchUserId) throws MalformedURLException {
          /*
            posts apiMethod status code is not Equal to 200 for a delete method
         */

        //Make the RestAssured call
        Response response =

                given()
                        .queryParam("userId",searchUserId)
                        .baseUri(BaseUrl).
                when()
                        .delete("/posts").
                then()
                        .extract().response();

        Assert.assertTrue(response.getStatusCode()!=200);
        System.out.println(" DELETE Method not allowed for /posts endpoint");

    }

    public static void deleteComment(String searchPostId) throws MalformedURLException {
         /*
            comments apiMethod status code is not Equal to 200 for a delete method
         */

        //Make the RestAssured call
        Response response =

                given()
                        .queryParam("postId",searchPostId)
                        .baseUri(BaseUrl).
                when()
                        .delete("/comments").
                then()
                        .extract().response();

        Assert.assertTrue(response.getStatusCode()!=200);
        System.out.println("DELETE Method not allowed for /comments endpoint");

    }

    public static void addPost(String searchUserId) throws MalformedURLException {
         /*
            posts apiMethod status code is not Equal to 200 for a post method
         */

        //Make the RestAssured call
        Response response =

                given()
                        .queryParam("userId",searchUserId)
                        .baseUri(BaseUrl).
                when()
                        .post("/posts").
                then()
                        .extract().response();

        Assert.assertTrue(response.getStatusCode()!=200);
        System.out.println("POST Method not allowed for /posts endpoint");

    }



}
