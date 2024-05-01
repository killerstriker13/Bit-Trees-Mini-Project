import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
/**
 * BrailleASCIITables is a class that includes methods for converting between ASCII characters, 
 * Braille bit strings, and Unicode Braille characters. It uses BitTree data structures to store
 * and get the necessary information.
 *
 * @author Shibam Mukhopadhyay
 */
public class BrailleASCIITables {
  public static String toBraille(char letter) {
    BitTree tree = new BitTree(1);
    try {
    InputStream inputStream = new FileInputStream("../lib/ASCIIToBraille.txt");
      tree.load(inputStream);
    } catch (FileNotFoundException e) {
      try {
        InputStream inputStream = new FileInputStream("lib/ASCIIToBraille.txt");
        tree.load(inputStream);
      } catch (FileNotFoundException ex) {
      }
    }
    int asciiValue = (int) letter;
    StringBuilder binaryValueBuilder = new StringBuilder(Integer.toBinaryString(asciiValue));
    if (binaryValueBuilder.length() < 8) {
      for (int i = binaryValueBuilder.length(); i < 8; i++) {
        binaryValueBuilder.insert(0, "0");
      }
    }
    String path = binaryValueBuilder.toString();
    try {
      return tree.get(path);
    } catch (Exception e) {
      return "letter not found ";
    }
  }// toBraile(char)

  public static String toASCII(String bits) {
    BitTree tree = new BitTree(1);
    try {
      InputStream inputStream = new FileInputStream("../lib/BrailleToASCII.txt");
      tree.load(inputStream);
    } catch (FileNotFoundException e) {
      try {
        InputStream inputStream = new FileInputStream("lib/BrailleToASCII.txt");
        tree.load(inputStream);
      } catch (FileNotFoundException ex) {
      }
    }
    try {
      return tree.get(bits);
    } catch (Exception e) {
      return "letter not found ";
    }
  }// toASCII(String)

  public static String toUnicode(String bits) {
    BitTree tree = new BitTree(1);
    try {
      InputStream inputStream = new FileInputStream("../lib/BrailleToUnicode.txt");
      tree.load(inputStream);
    } catch (FileNotFoundException e) {
      try {
        InputStream inputStream = new FileInputStream("lib/BrailleToUnicode.txt");
        tree.load(inputStream);
      } catch (FileNotFoundException ex) {
      }
    }
    try {
      return tree.get(bits);
    } catch (Exception e) {
      return "letter not found ";
    }
  }// toUnicode(String)
}// class BrailleASCIITables