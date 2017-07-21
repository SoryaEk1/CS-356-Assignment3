import javax.swing.JFrame;

/**
 * Visitor Pattern
 *
 */
public interface NodeVisitor {
	void accept(JFrame visitor);
}
