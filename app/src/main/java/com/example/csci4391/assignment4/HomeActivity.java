package com.example.csci4391.assignment4;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

class Grades {
    Double classParticipationGrade;
    Double assignment1Grade;
    Double assignment2Grade;
    Double assignment3Grade;
    Double assignment4Grade;
    Double assignment5Grade;
    Double assignment6Grade;
    Double quiz1Grade;
    Double quiz2Grade;
    Double finalExamGrade;
    Double gradeTotal;
    String letterGrade;
    boolean check1;
    boolean check2;
    boolean check3;
    boolean check4;
}

public class HomeActivity extends AppCompatActivity
        implements WelcomeFragment.OnFragmentInteractionListener, AssignmentsFragment.OnFragmentInteractionListener, ClassParticipationFragment.OnFragmentInteractionListener, FinalExamFragment.OnFragmentInteractionListener, QuizzesFragment.OnFragmentInteractionListener{

        Grades newGrades = new Grades();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        WelcomeFragment welcomeFragment = new WelcomeFragment();
        Bundle myBundle = getIntent().getExtras();
        welcomeFragment.signedUsername = myBundle.getString("username");
        this.pushFragment(welcomeFragment, false);
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

    boolean gradeCalculator(){
        if(newGrades.check1 == true && newGrades.check2 == true && newGrades.check3 == true && newGrades.check4 == true) {
            Double classParticipationAverage = newGrades.classParticipationGrade * .05;
            Double programmingAssignments = (newGrades.assignment1Grade + newGrades.assignment2Grade + newGrades.assignment3Grade + newGrades.assignment4Grade + newGrades.assignment5Grade + newGrades.assignment6Grade) / 6;
            programmingAssignments = programmingAssignments * .3;
            Double quizzesAverage = (newGrades.quiz1Grade + newGrades.quiz2Grade) / 2;
            quizzesAverage = quizzesAverage * .3;
            Double finalExamAverage = newGrades.finalExamGrade * .35;
            newGrades.gradeTotal = classParticipationAverage + programmingAssignments + quizzesAverage + finalExamAverage;
            if (newGrades.gradeTotal >= 91){
                newGrades.letterGrade = "A";
            }
            else if (newGrades.gradeTotal >= 89.0) {
                newGrades.letterGrade = "A-";
            }
            else if (newGrades.gradeTotal >= 86.0) {
                newGrades.letterGrade = "B+";
            }
            else if (newGrades.gradeTotal >= 82.0){
                newGrades.letterGrade = "B";
            }
            else if (newGrades.gradeTotal >= 79.0){
                newGrades.letterGrade = "B-";
            }
            else if (newGrades.gradeTotal >= 76.0){
                newGrades.letterGrade = "C+";
            }
            else if (newGrades.gradeTotal >= 72.0){
                newGrades.letterGrade = "C";
            }
            else if (newGrades.gradeTotal >= 70.0){
                newGrades.letterGrade = "C-";
            }
            else if (newGrades.gradeTotal >= 60.0){
                newGrades.letterGrade = "D";
            }
            else {
                newGrades.letterGrade = "F";
            }
            return true;
        }
        return false;
    }

    public String getLetterGrade(){
        if(gradeCalculator()){
            return newGrades.letterGrade;
        }
        else {
            return "Letter grade not calculated yet";
        }
    }

    public String getFinalScore(){
        if(gradeCalculator()){
            return newGrades.gradeTotal.toString();
        }
        else {
            return "Final score not calculated yet";
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onAssignmentsPressed(Double a1, Double a2, Double a3, Double a4, Double a5, Double a6) {
        newGrades.assignment1Grade = a1;
        newGrades.assignment2Grade = a2;
        newGrades.assignment3Grade = a3;
        newGrades.assignment4Grade = a4;
        newGrades.assignment5Grade = a5;
        newGrades.assignment6Grade = a6;
        newGrades.check1 = true;
    }

    public void onClassParticipationPressed(Double classParticipation) {
        newGrades.classParticipationGrade = classParticipation;
        newGrades.check2 = true;
    }

    public void onQuizzesPressed(Double q1, Double q2) {
        newGrades.quiz1Grade = q1;
        newGrades.quiz2Grade = q2;
        newGrades.check3 = true;
    }

    public void onFinalExamPressed(Double fe) {
        newGrades.finalExamGrade = fe;
        newGrades.check4 = true;
    }


}
