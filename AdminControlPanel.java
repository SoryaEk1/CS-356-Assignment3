import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class AdminControlPanel extends JPanel implements ObserverListenter {

	/*
	 * Singleton Pattern There is always only one object Admin Control Panel
	 */
	private static AdminControlPanel panel = null;

	/**
	 * Get an Admin Control Panel object
	 */
	public static AdminControlPanel getInstance(JFrame frame) {
		// If there is no Admin Control Panel Object, create new
		if (panel == null) {
			panel = new AdminControlPanel(frame);
		}
		return panel;
	}

	private JTree tree;

	private JTextField txtUserId;
	private JTextField txtGroupId;
	private JButton btnAddUser;
	private JButton btnAddGroup;
	private JButton btnOpenUserView;
	private JButton btnShowUserTotal;
	private JButton btnShowGroupTotal;
	private JButton btnShowMessagesTotal;
	private JButton btnShowPositivePercentage;
	private JButton btnFindLastUpdate;

	private Root root;
	private JFrame frame;

	/**
	 * Private constructor for Singleton Pattern
	 */
	private AdminControlPanel(JFrame frame) {

		super(new BorderLayout(5, 5));

		this.frame = frame;

		root = new Root("Root");
		tree = new JTree(root);
		tree.setMinimumSize(new Dimension(200, 600));
		tree.setPreferredSize(new Dimension(200, 600));
		add(tree, BorderLayout.WEST);

		JPanel pnlCenter = new JPanel(new BorderLayout());

		JPanel pnlCenterNorth = new JPanel(new GridBagLayout());

		pnlCenter.add(pnlCenterNorth, BorderLayout.NORTH);

		add(pnlCenter, BorderLayout.CENTER);

		txtUserId = new JTextField();
		txtGroupId = new JTextField();
		btnAddUser = new JButton("Add User");
		btnAddGroup = new JButton("Add Group");
		btnOpenUserView = new JButton("Open User View");
		btnFindLastUpdate = new JButton("Find last updated user");

		pnlCenterNorth.add(txtUserId, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		pnlCenterNorth.add(btnAddUser, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		pnlCenterNorth.add(txtGroupId, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		pnlCenterNorth.add(btnAddGroup, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		pnlCenterNorth.add(btnOpenUserView, new GridBagConstraints(0, 2, 4, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		pnlCenterNorth.add(btnFindLastUpdate, new GridBagConstraints(0, 3, 4, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		btnOpenUserView.addActionListener(this);
		btnAddUser.addActionListener(this);
		btnAddGroup.addActionListener(this);
		btnFindLastUpdate.addActionListener(this);

		JPanel pnlCenterSouth = new JPanel(new GridBagLayout());
		pnlCenter.add(pnlCenterSouth, BorderLayout.SOUTH);

		btnShowUserTotal = new JButton("Show User Total");
		btnShowGroupTotal = new JButton("Show Group Total");
		btnShowMessagesTotal = new JButton("Show Messages Total");
		btnShowPositivePercentage = new JButton("Show Positive Percentage");

		btnShowUserTotal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, String.format("%d user(s)", root.numOfUsers()));

			}
		});

		btnShowGroupTotal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, String.format("%d group(s)", root.numOfGroups()));

			}
		});

		btnShowMessagesTotal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, String.format("%d message(s)", root.numfOfMessages()));

			}
		});

		btnShowPositivePercentage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, String.format("Positive percentage: %10.2f%%", root.percentage()));

			}
		});

		pnlCenterSouth.add(btnShowUserTotal, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		pnlCenterSouth.add(btnShowGroupTotal, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		pnlCenterSouth.add(btnShowMessagesTotal, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		pnlCenterSouth.add(btnShowPositivePercentage, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

	}

	@Override
	public void addUser(String userId) {

		if ("".equalsIgnoreCase(userId.trim())) {
			JOptionPane.showMessageDialog(this, "Please input user id");
			return;
		}

		if (tree.getLastSelectedPathComponent() == null) {
			JOptionPane.showMessageDialog(this, "Please select parent node");
			return;
		}

		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

		if (selectedNode instanceof User) {
			JOptionPane.showMessageDialog(this, "User cannot have a child");
			return;
		}

		validateID(userId);

		User user = new User(userId);

		if (root.hasElement(user)) {
			JOptionPane.showMessageDialog(this, "This user id is already exists");
			return;
		}

		if (selectedNode.equals(root)) {
			selectedNode.add(user);
		} else {
			((UserGroup) selectedNode).addChild(user);
		}

		root.add(user);

		expandAllNodes(tree, 0, tree.getRowCount());

		txtUserId.setText("");

		((DefaultTreeModel) tree.getModel()).reload();

	}

	@Override
	public void addGroup(String groupId) {
		if ("".equalsIgnoreCase(groupId.trim())) {
			JOptionPane.showMessageDialog(this, "Please input groud id");
			return;
		}

		if (tree.getLastSelectedPathComponent() == null) {
			JOptionPane.showMessageDialog(this, "Please select parent node");
			return;
		}
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

		if (selectedNode instanceof User) {
			JOptionPane.showMessageDialog(this, "User cannot have a child");
			return;
		}

		validateID(groupId);

		UserGroup group = new UserGroup(groupId);

		if (root.hasElement(group)) {
			JOptionPane.showMessageDialog(this, "This groupd id is already exists");
			return;
		}

		if (selectedNode.equals(root)) {
			selectedNode.add(group);
		} else {
			((UserGroup) selectedNode).addChild(group);
		}

		root.add(group);

		expandAllNodes(tree, 0, tree.getRowCount());

		txtGroupId.setText("");

		((DefaultTreeModel) tree.getModel()).reload();

	}

	private void expandAllNodes(JTree tree, int startingIndex, int rowCount) {
		for (int i = startingIndex; i < rowCount; ++i) {
			tree.expandRow(i);
		}

		if (tree.getRowCount() != rowCount) {
			expandAllNodes(tree, rowCount, tree.getRowCount());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnAddUser)) {
			addUser(txtUserId.getText());
		}

		if (e.getSource().equals(btnAddGroup)) {
			addGroup(txtGroupId.getText());
		}

		if (e.getSource().equals(btnOpenUserView)) {
			viewUser();
		}

		if (e.getSource().equals(btnFindLastUpdate)) {
			showLastUpdatedUser();
		}

	}

	private void showLastUpdatedUser() {
		User u = root.getLastUpdatedUser();
		if (u == null) {
			JOptionPane.showMessageDialog(this, "No user found!");
			return;
		}
		JOptionPane.showMessageDialog(this,
				String.format("User Id: %s, last updated time: %d", u.getId(), u.getLastUpdateTime()));
	}

	@Override
	public void viewUser() {
		if (tree.getLastSelectedPathComponent() == null) {
			JOptionPane.showMessageDialog(this, "Please select an user node");
			return;
		}
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

		if (!(selectedNode instanceof User)) {
			JOptionPane.showMessageDialog(this, "Selected node is not an user");
			return;
		}

		((User) selectedNode).accept(this.frame);

	}

	private void validateID(String id) {
		if (id == null) {
			JOptionPane.showMessageDialog(this, "Id is null");
			return;
		}

		if (id.contains(" ")) {
			JOptionPane.showMessageDialog(this, "ID is invalid (contains space)");
			return;
		}
	}

}
