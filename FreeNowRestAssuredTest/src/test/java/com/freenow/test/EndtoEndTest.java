package com.freenow.test;

import com.freenow.utils.HelperMethods;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;

    public class EndtoEndTest {

        private static final String username = "Samantha";//Modify according to test
        int userId;

        /*
            Test01 - This test will get the userId based on the username provided if the user exits in the system.
                     If the username is invalid or not available in DB, this test will fail and following test will be skipped
         */
        @Test(priority = 0)
        public void T01_getUserId() throws MalformedURLException, NullPointerException{

                userId = HelperMethods.searchUser(username);
                Assert.assertNotNull(userId);
        }



        /*
            Test02 - This test will get the comments based on the userId fetched from the username provided if the user exits in the system.
                      If the username is invalid or not available in DB, this test will be skipped
         */
        @Test(dependsOnMethods = "T01_getUserId", priority = 1)
        public void T02_fetchCommentsByUserId() throws MalformedURLException {

            ArrayList<Integer> postId = HelperMethods.getPosts(userId);
            Assert.assertNotNull(HelperMethods.getComments(postId), "Comments are Listed Out for the User");
        }



        /*
           Test03 - This test will verify the emailId format for each post based on the userId fetched from the username provided if the user exits in the system.
                     If the username is invalid or not available in DB, this test will be skipped
        */
        @Test(dependsOnMethods = "T01_getUserId",priority = 2)
        public void T03_verifyEmailsByPostIds() throws MalformedURLException {

            ArrayList<Integer> postId = HelperMethods.getPosts(userId);
            Assert.assertNotNull(HelperMethods.verifyEmail(postId), "Email Addresses are Listed Out for the User");
        }


        /*
          Test04 - This test will get the comments and validates the emailId format based on the username provided if the user exits in the system.
                    If the username is invalid or not available in DB, this test will fail
       */
        @Test(priority = 4)
        public void T04_fetchCommentsByUsernameandVerifyEmail() throws MalformedURLException {
            int userId = HelperMethods.searchUser("Samantha");
            ArrayList<Integer> postId = HelperMethods.getPosts(userId);
            Assert.assertNotNull(HelperMethods.getComments(postId), "Comments are Listed Out for the User");
            Assert.assertNotNull(HelperMethods.verifyEmail(postId), "Email Addresses are Listed Out for the User");
        }


    }
