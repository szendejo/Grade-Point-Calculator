package com.example.csci4391.assignment4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;

class User {
    String username;
    String password;
    Grades grades;
}

public class LoginActivity extends AppCompatActivity
        implements SigninFragment.OnFragmentInteractionListener,
        SignupFragment.OnFragmentInteractionListener {

    User[] usersArray = new User[50];
    int numActiveUsers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.pushFragment(new SigninFragment(), false);


    }

    void pushFragment(Fragment newFragment, boolean addToStack) {
        // Create a FragmentTransaction from FragmentManager via activity
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment
        transaction.replace(R.id.loginFragmentContainer, newFragment);
        if (addToStack) {
            // Add the transaction to the back stack
            transaction.addToBackStack(null);
        }
        //Commit the transaction
        transaction.commit();
    }

    boolean isUserPresent(String username, String password) {
        for (int i=0; i<numActiveUsers; i++) {
            User u = usersArray[i];
            if(u.username.equals(username) && u.password.equals(password)) {
                return  true;
            }
        }
        return  false;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onNewSignupPressed(String newUsername, String newPassword) {
        User newUser = new User();
        newUser.username = newUsername;
        newUser.password = newPassword;

        usersArray[numActiveUsers] = newUser;
        numActiveUsers++;


    }

    @Override
    public void onSignInPressed(String username, String password) {
        if(isUserPresent(username, password)) {
            Intent myIntent = new Intent(this, HomeActivity.class);
            myIntent.putExtra("username", username);
            startActivity(myIntent);
        }
    }


}
