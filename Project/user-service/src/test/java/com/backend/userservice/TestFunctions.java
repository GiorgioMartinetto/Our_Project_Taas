package com.backend.userservice;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.junit.jupiter.api.Test;


public class TestFunctions {
  
  private static boolean  validateEmail(String email){
    String EMAIL_PATTERN = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  @Test
  private static void test_correct_format_Email() {

    ArrayList<String> emails = new ArrayList<>();
    emails.add("user@domain.com");
    emails.add("user@domain.co.in");
    emails.add("user.name@domain.com");
    emails.add("user_name@domain.com");
    emails.add("username@yahoo.corporate.in");

    System.out.println("right emails:");

    for(String email : emails){
        
        System.out.println(email+" result test= "+validateEmail(email));
    }
  }

  @Test
  private static void test_wrong_format_Email(){
    ArrayList<String> emails = new ArrayList<>();
    emails.add(".username@yahoo.com");
    emails.add("username@yahoo.com.");
    emails.add("username@yahoo..com");
    emails.add("username@yahoo.c");
    emails.add("username@yahoo.corporate");

    System.out.println("wrong emails:");
    for(String email : emails){
        
        System.out.println(email+" result test= "+!validateEmail(email));
    }
}

}

