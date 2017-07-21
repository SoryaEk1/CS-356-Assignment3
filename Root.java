import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;
/**
 * Create root tree
 */
public class Root extends DefaultMutableTreeNode {
	private Set<UserGroup> groups;
	private Set<User> users;

	private final List<String> positiveWords;

	public Root(String name) {
		super(name);
		this.groups = new HashSet<UserGroup>();
		this.users = new HashSet<User>();
		this.positiveWords = new ArrayList<String>();
		this.positiveWords.add("good");
		this.positiveWords.add("great");
		this.positiveWords.add("excellent");
	}

	public boolean hasElement(User id) {
		return users.contains(id);
	}

	public boolean hasElement(UserGroup id) {
		return groups.contains(id);
	}

	public void add(User id) {
		this.users.add(id);
	}

	public void add(UserGroup id) {
		this.groups.add(id);
	}

	public int numOfGroups() {
		return this.groups.size();
	}

	public int numOfUsers() {
		return this.users.size();
	}

	public int numfOfMessages() {
		int cnt = 0;
		for (User u : users) {
			cnt += u.getMessages().size();
		}
		return cnt;
	}

	public float percentage() {
		int totalMsg = numfOfMessages();
		if (totalMsg == 0)
			return 0;
		int cnt = 0;
		for (User u : users) {
			for (String m : u.getMessages()) {
				for (String p : positiveWords) {
					if (m.contains(p)) {
						cnt++;
						break;
					}
				}
			}
		}
		return cnt / (float) totalMsg * 100;
	}

	public User getLastUpdatedUser() {
		if (users == null || users.size() <= 0)
			return null;
		User ret = null;
		for (User u : users) {
			if (ret == null) {
				ret = u;
				continue;
			}

			if (ret.getLastUpdateTime() < u.getLastUpdateTime())
				ret = u;
		}
		return ret;
	}

}
