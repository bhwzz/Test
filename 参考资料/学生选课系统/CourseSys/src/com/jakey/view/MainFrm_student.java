package com.jakey.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author  __USER__
 */
public class MainFrm_student extends javax.swing.JFrame {

	/** Creates new form MainFrm */
	public MainFrm_student() {
		initComponents();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		table = new javax.swing.JDesktopPane();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenu3 = new javax.swing.JMenu();
		jmiSelectCourse = new javax.swing.JMenuItem();
		jmiSelectedView = new javax.swing.JMenuItem();
		jMenu4 = new javax.swing.JMenu();
		jmiPasswordModify = new javax.swing.JMenuItem();
		jmiSelfInfo = new javax.swing.JMenuItem();
		jmiExit = new javax.swing.JMenuItem();
		jMenu2 = new javax.swing.JMenu();
		jMenuItem6 = new javax.swing.JMenuItem();
		jMenuItem7 = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("\u5b66\u751f\u9009\u8bfe\u7cfb\u7edf\u4e3b\u754c\u9762_\u5b66\u751f\u7aef");

		jMenu1
				.setIcon(new javax.swing.ImageIcon(
						"D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\base.png")); // NOI18N
		jMenu1.setText("\u57fa\u672c\u64cd\u4f5c");

		jMenu3
				.setIcon(new javax.swing.ImageIcon(
						"D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\course.png")); // NOI18N
		jMenu3.setText("\u9009\u8bfe");

		jmiSelectCourse
				.setIcon(new javax.swing.ImageIcon(
						"D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\course.png")); // NOI18N
		jmiSelectCourse.setText("\u9009\u62e9\u672a\u9009\u8bfe\u7a0b");
		jmiSelectCourse.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiSelectCourseActionPerformed(evt);
			}
		});
		jMenu3.add(jmiSelectCourse);

		jmiSelectedView
				.setIcon(new javax.swing.ImageIcon(
						"D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\course.png")); // NOI18N
		jmiSelectedView.setText("\u67e5\u770b\u5df2\u9009\u8bfe\u7a0b");
		jmiSelectedView.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiSelectedViewActionPerformed(evt);
			}
		});
		jMenu3.add(jmiSelectedView);

		jMenu1.add(jMenu3);

		jMenu4
				.setIcon(new javax.swing.ImageIcon(
						"D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\userName.png")); // NOI18N
		jMenu4.setText("\u4e2a\u4eba\u4fe1\u606f");

		jmiPasswordModify
				.setIcon(new javax.swing.ImageIcon(
						"D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\password.png")); // NOI18N
		jmiPasswordModify.setText("\u4fee\u6539\u5bc6\u7801");
		jmiPasswordModify
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jmiPasswordModifyActionPerformed(evt);
					}
				});
		jMenu4.add(jmiPasswordModify);

		jmiSelfInfo
				.setIcon(new javax.swing.ImageIcon(
						"D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\student.png")); // NOI18N
		jmiSelfInfo.setText("\u5b66\u7c4d\u4fe1\u606f");
		jmiSelfInfo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiSelfInfoActionPerformed(evt);
			}
		});
		jMenu4.add(jmiSelfInfo);

		jMenu1.add(jMenu4);

		jmiExit
				.setIcon(new javax.swing.ImageIcon(
						"D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\exit.png")); // NOI18N
		jmiExit.setText("\u9000\u51fa\u7cfb\u7edf");
		jmiExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiExitActionPerformed(evt);
			}
		});
		jMenu1.add(jmiExit);

		jMenuBar1.add(jMenu1);

		jMenu2
				.setIcon(new javax.swing.ImageIcon(
						"D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\help.png")); // NOI18N
		jMenu2.setText("\u5e2e\u52a9");

		jMenuItem6
				.setIcon(new javax.swing.ImageIcon(
						"D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\help.png")); // NOI18N
		jMenuItem6.setText("\u64cd\u4f5c\u6307\u5357");
		jMenu2.add(jMenuItem6);

		jMenuItem7
				.setIcon(new javax.swing.ImageIcon(
						"D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\phone.png")); // NOI18N
		jMenuItem7.setText("\u8054\u7cfb\u7ba1\u7406\u5458");
		jMenu2.add(jMenuItem7);

		jMenuBar1.add(jMenu2);

		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(table,
				javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(table,
				javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jmiSelfInfoActionPerformed(java.awt.event.ActionEvent evt) {
		SelfInfoInterFrm selfInfoInterFrm = new SelfInfoInterFrm();
		selfInfoInterFrm.setVisible(true);
		this.table.add(selfInfoInterFrm);
	}

	private void jmiPasswordModifyActionPerformed(java.awt.event.ActionEvent evt) {
		PasswordModifyInterFrm passwordModifyInterFrm = new PasswordModifyInterFrm();
		passwordModifyInterFrm.setVisible(true);
		this.table.add(passwordModifyInterFrm);
	}

	private void jmiSelectedViewActionPerformed(java.awt.event.ActionEvent evt) {
		SelectedViewInterFrm selectedViewInterFrm = new SelectedViewInterFrm();
		selectedViewInterFrm.setVisible(true);
		this.table.add(selectedViewInterFrm);
	}

	private void jmiSelectCourseActionPerformed(java.awt.event.ActionEvent evt) {
		SelectCourseInterFrm selectCourseInterFrm = new SelectCourseInterFrm();
		selectCourseInterFrm.setVisible(true);
		this.table.add(selectCourseInterFrm);
	}

	private void jmiExitActionPerformed(java.awt.event.ActionEvent evt) {
		int result = JOptionPane.showConfirmDialog(this, "�Ƿ��˳�ϵͳ?");
		if (result == 0) {
			this.dispose();
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrm_student().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenu jMenu3;
	private javax.swing.JMenu jMenu4;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem jMenuItem6;
	private javax.swing.JMenuItem jMenuItem7;
	private javax.swing.JMenuItem jmiExit;
	private javax.swing.JMenuItem jmiPasswordModify;
	private javax.swing.JMenuItem jmiSelectCourse;
	private javax.swing.JMenuItem jmiSelectedView;
	private javax.swing.JMenuItem jmiSelfInfo;
	private javax.swing.JDesktopPane table;
	// End of variables declaration//GEN-END:variables

}