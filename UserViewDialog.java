import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UserViewDialog extends JDialog {

	private JTextField txtUserId;

	private JButton btnFollowUser;

	private JList<String> lstFollowing;

	private JTextField txtTweetMessage;
	private JButton btnPostTweet;
	private JLabel lblCreatedTime;

	private JList<String> lstNewFeeds;

	private User user;

	public UserViewDialog(User user) {
		this.user = user;

		setTitle("User View");

		setSize(400, 300);

		setModal(true);
		setLayout(new GridBagLayout());

		txtUserId = new JTextField();

		btnFollowUser = new JButton("Follow User");

		btnFollowUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addFollower();
			}
		});

		lstFollowing = new JList<String>();

		txtTweetMessage = new JTextField();
		btnPostTweet = new JButton("Post Tweet");
		btnPostTweet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				postMessage();

			}
		});
		lstNewFeeds = new JList<String>();
		lblCreatedTime =  new JLabel("");

		add(txtUserId, new GridBagConstraints(0, 0, 1, 1, 1.0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		add(btnFollowUser, new GridBagConstraints(1, 0, 1, 1, 1.0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		add(lstFollowing, new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

		add(txtTweetMessage, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		add(btnPostTweet, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		add(lstNewFeeds, new GridBagConstraints(0, 3, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		add(lblCreatedTime, new GridBagConstraints(0, 4, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		bindData();

	}

	protected void postMessage() {
		if ("".equals(txtTweetMessage.getText().trim())) {
			JOptionPane.showMessageDialog(this, "Please input message");
			return;
		}

		user.getMessages().add(txtTweetMessage.getText().trim());

		bindData();
		user.setLastUpdateTime(System.currentTimeMillis());

	}

	protected void addFollower() {

		if ("".equals(txtUserId.getText().trim())) {
			JOptionPane.showMessageDialog(this, "Please input user id");
			return;
		}

		user.getFollowings().add(txtUserId.getText().trim());
		user.setLastUpdateTime(System.currentTimeMillis());

		bindData();
	}

	private void bindData() {

		DefaultListModel<String> followingModel = new DefaultListModel<String>();

		for (String f : user.getFollowings()) {
			followingModel.addElement(f);
		}

		lstFollowing.setModel(followingModel);

		DefaultListModel<String> messageModel = new DefaultListModel<String>();

		for (String f : user.getMessages()) {
			messageModel.addElement(f);
		}

		lstNewFeeds.setModel(messageModel);

		txtUserId.setText("");
		txtTweetMessage.setText("");
		
		lblCreatedTime.setText(String.format("Creation Time: %d", user.getCreationTime()));
	}
}
