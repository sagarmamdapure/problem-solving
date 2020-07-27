package com.org.twitter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TwitterDataIntegrationTest {

  @Mock PrintStream printStream;

  @BeforeEach
  void setup() {
    new TwitterData(printStream);
  }

  @Test
  void shouldReturnTopHashTag() {
    OutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    String[] filePath = {"src/test/resources/input-data.txt"};
    Main.main(filePath);
    String expectedResult =
        "coronavirus\n"
            + "Coronavirus\n"
            + "COVID19\n"
            + "TDS\n"
            + "COVID19.\n"
            + "Wuhan\n"
            + "COVID2019\n"
            + "新型肺炎\n"
            + "SummitCountyUT\n"
            + "ParkCity\n";
    assertEquals(expectedResult, outputStream.toString());
  }

  @Test
  void shouldShowErrorMessageWhenErrorOccurredReadingFileInput() throws IOException {
    OutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    String[] filePath = {"non_existent_file.txt"};
    Main.main(filePath);
    assertEquals(
        String.format(
            "Error while reading input from file: %s (No such file or directory)\n", filePath[0]),
        outputStream.toString());
  }
}
