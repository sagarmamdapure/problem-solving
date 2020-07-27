package com.org.twitter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

class Main {
  public static void main(String[] args) {
    PrintStream printStream = new PrintStream(System.out);
    TwitterData twitterData = new TwitterData(printStream);
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
      String strCurrentLine;
      while ((strCurrentLine = bufferedReader.readLine()) != null) {
        twitterData.extractHashTagFromTweet(strCurrentLine);
      }
    } catch (IOException exception) {
      printStream.println("Error while reading input from file: " + exception.getMessage());
    }
    twitterData.printTopTenTweets();
  }
}
