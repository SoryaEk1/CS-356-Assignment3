import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
/**
 * Create set constructors
 */
public class User extends CompositeNode implements Element, NodeVisitor {

	private String id;
	private List<String> followers;
	private List<String> followings;
	private List<String> messages;
	private long lastUpdateTime;

	public User(String id) {
		super();
		this.id = id;
		this.followers = new ArrayList<String>();
		this.followings = new ArrayList<String>();
		this.messages = new ArrayList<String>();
		this.creationTime = System.currentTimeMillis();
		this.lastUpdateTime = this.creationTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getFollowers() {
		return followers;
	}

	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}

	public List<String> getFollowings() {
		return followings;
	}

	public void setFollowings(List<String> followings) {
		this.followings = followings;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	@Override
	public void accept(JFrame visitor) {
		new UserViewDialog(this).setVisible(true);

	}

	@Override
	public String toString() {
		return id;
	}

	@Override
	public void addChild(NodeVisitor user) {
		return;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof User))
			return false;
		User u = (User) obj;
		return u.id.equals(this.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
