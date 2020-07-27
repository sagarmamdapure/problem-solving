package com.org.twitter;

import java.io.PrintStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwitterData {
  private final PrintStream printStream;
  private HashMap<String, Integer> tweetMap = new HashMap<>();

  public TwitterData(PrintStream printStream) {
    this.printStream = printStream;
  }

  private static HashMap<String, Integer> sortHashMap(HashMap<String, Integer> tweetMap) {
    List<Map.Entry<String, Integer>> list = new LinkedList<>(tweetMap.entrySet());
    list.sort((value1, value2) -> (value2.getValue()).compareTo(value1.getValue()));
    HashMap<String, Integer> sortedMap = new LinkedHashMap<>();
    for (Map.Entry<String, Integer> aa : list) {
      sortedMap.put(aa.getKey(), aa.getValue());
    }
    return sortedMap;
  }

  public void printTopTenTweets() {
    tweetMap.keySet().stream().limit(10).forEach(printStream::println);
  }

  public void extractHashTagFromTweet(String tweet) {
    Pattern pattern = Pattern.compile("#(\\S+)");
    Matcher matcher = pattern.matcher(tweet);
    while (matcher.find()) {
      tweetMap.put(matcher.group(1), tweetMap.getOrDefault(matcher.group(1), 0) + 1);
    }
    tweetMap = sortHashMap(tweetMap);
  }
}
