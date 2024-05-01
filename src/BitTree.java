import java.io.PrintWriter;
import java.io.InputStream;
import java.util.Scanner;

/**
 * BitTree is a binary tree data structure. :)
 *
 * @author Shibam Mukhopadhyay
 */
public class BitTree {

  // ---------------------------------------------------
  // | Fields |
  // ----------
  private BitTreeNode root;

  // ---------------------------------------------------
  // | Constructor |
  // ---------------
  public BitTree(int n) {
      this.root = new BitTreeInteriorNode();
  } // BitTree(int)

  // ---------------------------------------------------
  // | Methods |
  // -----------
  public void dump(PrintWriter pen) {
      pen.println(dumpHelper(root, "")); // calls dump helper
  } // dump(PrintWriter)

  public void set(String bits, String value) {
      setHelper(bits, value, root); // calls set helper method
  } // set

  public String get(String bits) throws Exception {
      return getHelper(bits, root); // calls get helper method
  } // get(String)

  public void load(InputStream inputStream) {
      Scanner scanner = new Scanner(inputStream);
      while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          String bitString = line.substring(0, line.indexOf(','));
          String valueString = line.substring(line.indexOf(',') + 1);
          set(bitString, valueString);
      } // while
      scanner.close();
  } // load(InputStream)

  // ---------------------------------------------------
  // | Helper Methods |
  // ------------------
  private String dumpHelper(BitTreeNode node, String prevString) {
      String stringBuilder = "";
      if (node instanceof BitTreeLeaf) {
          return stringBuilder + prevString + "," + ((BitTreeLeaf) node).value + "\n";
      } else if (node instanceof BitTreeInteriorNode) {
          if (((BitTreeInteriorNode) node).hasChildZero()) {
              stringBuilder = stringBuilder + dumpHelper(((BitTreeInteriorNode) node).childZero, prevString + "0");
          } // for left child
          if (((BitTreeInteriorNode) node).hasChildOne()) {
              stringBuilder = stringBuilder + dumpHelper(((BitTreeInteriorNode) node).childOne, prevString + "1");
          } // for right child
          return stringBuilder;
      } // if
      return "";
  } // dumpHelper(BitTreeNode)

  private void setHelper(String bits, String value, BitTreeNode node) {
      if (bits.length() == 1) {
          if (bits.charAt(0) == '0') {
              ((BitTreeInteriorNode) node).childZero = new BitTreeLeaf(value);
          } else {
              ((BitTreeInteriorNode) node).childOne = new BitTreeLeaf(value);
          } // if there is one element left in the bits String
      } else if (bits.charAt(0) == '0') {
          if ((node instanceof BitTreeInteriorNode) && ((BitTreeInteriorNode) node).hasChildZero()) {
              setHelper(bits.substring(1), value, ((BitTreeInteriorNode) node).childZero);
          } else {
              ((BitTreeInteriorNode) node).childZero = new BitTreeInteriorNode();
              setHelper(bits.substring(1), value, ((BitTreeInteriorNode) node).childZero);
          } // if child -> set, if no child -> make one then set
      } else if (bits.charAt(0) == '1') {
          if ((node instanceof BitTreeInteriorNode) && ((BitTreeInteriorNode) node).hasChildOne()) {
              setHelper(bits.substring(1), value, ((BitTreeInteriorNode) node).childOne);
          } else {
              ((BitTreeInteriorNode) node).childOne = new BitTreeInteriorNode();
              setHelper(bits.substring(1), value, ((BitTreeInteriorNode) node).childOne);
          } // if child -> set, if no child -> make one then set
      } // if
  } // setHelper(String, String, BitTreeNode)

  private String getHelper(String bits, BitTreeNode node) throws Exception {
      if (node instanceof BitTreeLeaf) {
          if (bits.length() == 0) {
              return ((BitTreeLeaf) node).value;
          }
          throw new Exception("Bit String too long");
      } else {
          if (bits.length() == 0) {
              throw new Exception("No such element");
          }
          if (bits.charAt(0) == '0') {
              return getHelper(bits.substring(1), ((BitTreeInteriorNode) node).childZero);
          } else if (bits.charAt(0) == '1') {
              return getHelper(bits.substring(1), ((BitTreeInteriorNode) node).childOne);
          } else {
              throw new Exception("No such element");
          } // if
      } // if
  } // getHelper(String, BitTreeNode)
} // class BitTree
