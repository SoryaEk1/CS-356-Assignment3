import java.awt.event.ActionListener;

/**
 * 
 * Observer Pattern
 *
 */
public interface ObserverListenter extends ActionListener {

	public void addUser(String user);

	public void addGroup(String group);

	public void viewUser();
}
