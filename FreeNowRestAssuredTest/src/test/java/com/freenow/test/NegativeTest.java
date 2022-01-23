package com.freenow.test;

import com.freenow.utils.HelperMethods;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


public class NegativeTest {

    private static final String username = "Samantha";

    /*
        Test01 - Verify user cannot be added i.e. POST Method is not allowed for /users endpoint
     */
    @Test
    public void T01_postUser() throws MalformedURLException, NullPointerException{
             HelperMethods.postUser(username);
     }

    /*
      Test02 - Verify user cannot be updated i.e. PUT Method is not allowed for /users endpoint
   */
    @Test
    public void T02_putUser() throws MalformedURLException, NullPointerException{
        HelperMethods.putUser(username);
    }

    /*
       Test03 - Verify user cannot be deleted i.e. DELETE Method is not allowed for /users endpoint
    */
    @Test
    public void T03_deleteUser() throws MalformedURLException, NullPointerException{
        HelperMethods.deleteUser(username);
    }

    /*
      Test04 - Verify post cannot be deleted i.e. DELETE Method is not allowed for /posts endpoint
   */
    @Test
    public void T04_deletePost() throws MalformedURLException, NullPointerException{
        HelperMethods.deletePost("30");
    }

    /*
        Test04 - Verify comments cannot be deleted i.e. DELETE Method is not allowed for /comments endpoint
    */
    @Test
    public void T05_deleteComment() throws MalformedURLException, NullPointerException{
        HelperMethods.deleteComment("30");
    }

    /*
      Test01 - Verify post cannot be added i.e. POST Method is not allowed for /posts endpoint
   */
    @Test
    public void T06_AddPost() throws MalformedURLException, NullPointerException{
        HelperMethods.addPost("30");
    }


}
