import java.io.PrintWriter;

/**
 * BrailleASCII is a utility program for converting between ASCII, Unicode and Braille.
 * It has methods to convert from ASCII strings to Braille bit strings, Braille bit 
 * strings to ASCII strings, and ASCII strings to Unicode Braille characters.
 *
 * @author Shibam Mukhopadhyay
 */
public class BrailleASCII {
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    if (args.length != 2) {
      pen.println("Please enter 'braille', 'ascii', or 'unicode' with a string to convert");
    } else if (args[0].equalsIgnoreCase("braille")) {
      StringBuilder retStringBuilder = new StringBuilder();
      for (int i = 0; i < args[1].length(); i++) {
      retStringBuilder.append(BrailleASCIITables.toBraille(args[1].charAt(i)));
      }
      pen.println(retStringBuilder.toString());
    } else if (args[0].equalsIgnoreCase("ascii")) {
      for (int i = 0; i < args[1].length(); i += 6) {
        pen.print(BrailleASCIITables.toASCII(args[1].substring(i, i + 6)).toLowerCase());
      }
      pen.println("");
    } else if (args[0].equalsIgnoreCase("unicode")) {
      StringBuilder retStringBuilder = new StringBuilder();
      for (int i = 0; i < args[1].length(); i++) {
      retStringBuilder.append(BrailleASCIITables.toBraille(args[1].charAt(i)));
      }
      String braille = retStringBuilder.toString();
      for (int i = 0; i < braille.length(); i += 6) {
        String unicode = "";
        unicode += BrailleASCIITables.toUnicode(braille.substring(i, i + 6));
        int j = Integer.decode("0x" + unicode);
        pen.print(Character.toChars(j));
      }
      pen.println("");
    }
  }
}
