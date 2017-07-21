import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.tree.MutableTreeNode;

public class UserGroup extends CompositeNode implements NodeVisitor {

	private String id;
	private List<NodeVisitor> users;

	public UserGroup(String id) {
		users = new ArrayList<NodeVisitor>();
		this.id = id;
		this.creationTime = System.currentTimeMillis();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void accept(JFrame visitor) {
		if (users != null) {
			for (NodeVisitor user : users) {
				user.accept(visitor);
			}
		}

	}

	@Override
	public void addChild(NodeVisitor visitor) {
		users.add(visitor);
		super.add((MutableTreeNode) visitor);
	}

	@Override
	public String toString() {
		return id;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof UserGroup))
			return false;
		UserGroup u = (UserGroup) obj;
		return u.id.equals(this.id);
	}
	@Override
	public int hashCode() {	
		return id.hashCode();
	}
}
