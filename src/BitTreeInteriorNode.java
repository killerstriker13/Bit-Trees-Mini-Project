
/**
 * BitTreeInteriorNode is the interior node of a BitTree with
 * two child nodes but no values
 *
 * @author Shibam Mukhopadhyay
 */
public class BitTreeInteriorNode implements BitTreeNode {
  BitTreeNode childZero;
  BitTreeNode childOne;

  public BitTreeInteriorNode() {
    this.childZero = null;
    this.childOne = null;
  } // BitTreeInteriorNode()

  public boolean hasChildZero() {
    return this.childZero != null;
  } // hasChildZero()

  public boolean hasChildOne() {
    return this.childOne != null;
  } // hasChildOne()
} // class BitTreeInteriorNode