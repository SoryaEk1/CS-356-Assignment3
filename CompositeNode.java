import javax.swing.tree.DefaultMutableTreeNode;

public abstract class CompositeNode extends DefaultMutableTreeNode {

	long creationTime;

	public abstract void addChild(NodeVisitor visitor);

	public long getCreationTime() {
		return creationTime;
	}

}
