package faceoff.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import faceoff.competition.Competition;
import faceoff.competition.Competitor;

public class GUI {

	private final Competition competition;
	private JFrame frame_faceoff_main;
	private JPanel left_image_panel;
	private JPanel right_image_panel;

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public GUI(Competition competition) throws IOException {
		this.competition = competition;
		initialize();
		frame_faceoff_main.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame_faceoff_main = new JFrame();
		frame_faceoff_main.setMinimumSize(new Dimension(1000, 500));
		frame_faceoff_main.setTitle("Face Off");
		frame_faceoff_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_faceoff_main.getContentPane().setLayout(new BorderLayout(0, 0));
		frame_faceoff_main.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel images_panel = new JPanel();
		frame_faceoff_main.getContentPane().add(images_panel, BorderLayout.CENTER);
		GridBagLayout gbl_images_panel = new GridBagLayout();
		gbl_images_panel.columnWidths = new int[]{0, 0, 0};
		gbl_images_panel.rowHeights = new int[]{0, 0};
		gbl_images_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_images_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		images_panel.setLayout(gbl_images_panel);
		
		left_image_panel = new JPanel();
		GridBagConstraints gbc_left_image_panel = new GridBagConstraints();
		gbc_left_image_panel.insets = new Insets(0, 0, 0, 5);
		gbc_left_image_panel.weightx = 1.0;
		gbc_left_image_panel.fill = GridBagConstraints.BOTH;
		gbc_left_image_panel.gridx = 0;
		gbc_left_image_panel.gridy = 0;
		images_panel.add(left_image_panel, gbc_left_image_panel);
		left_image_panel.setLayout(new BoxLayout(left_image_panel, BoxLayout.Y_AXIS));
		
		JLabel lblLeftImage = new JLabel("Left Image");
		lblLeftImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblLeftImage.setHorizontalAlignment(SwingConstants.CENTER);
		left_image_panel.add(lblLeftImage);
		
		right_image_panel = new JPanel();
		GridBagConstraints gbc_right_image_panel = new GridBagConstraints();
		gbc_right_image_panel.weightx = 1.0;
		gbc_right_image_panel.fill = GridBagConstraints.BOTH;
		gbc_right_image_panel.gridx = 1;
		gbc_right_image_panel.gridy = 0;
		images_panel.add(right_image_panel, gbc_right_image_panel);
		right_image_panel.setLayout(new BoxLayout(right_image_panel, BoxLayout.Y_AXIS));
		
		JLabel lblRightImage = new JLabel("Right Image");
		lblRightImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblRightImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		right_image_panel.add(lblRightImage);
		
		JPanel controls_panel = new JPanel();
		controls_panel.setBackground(SystemColor.controlDkShadow);
		frame_faceoff_main.getContentPane().add(controls_panel, BorderLayout.SOUTH);
		GridBagLayout gbl_controls_panel = new GridBagLayout();
		gbl_controls_panel.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_controls_panel.rowHeights = new int[]{0, 0};
		gbl_controls_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_controls_panel.rowWeights = new double[]{0.0, 0.0};
		controls_panel.setLayout(gbl_controls_panel);
		
		JButton btnNewButton = new JButton("Embiggen");
		btnNewButton.setBackground(SystemColor.controlDkShadow);
		btnNewButton.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Resize-16.png")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		controls_panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnLeftWins = new JButton("Left Wins (A)");
		btnLeftWins.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				competition.leftWins();
			}
		});
		btnLeftWins.setBackground(SystemColor.controlDkShadow);
		btnLeftWins.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Circled Left-16.png")));
		GridBagConstraints gbcBtnLeftWins = new GridBagConstraints();
		gbcBtnLeftWins.insets = new Insets(5, 0, 5, 5);
		gbcBtnLeftWins.weightx = 1.0;
		gbcBtnLeftWins.fill = GridBagConstraints.BOTH;
		gbcBtnLeftWins.gridx = 1;
		gbcBtnLeftWins.gridy = 0;
		controls_panel.add(btnLeftWins, gbcBtnLeftWins);
		
		JButton btnDeleteLeft = new JButton("Delete");
		btnDeleteLeft.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				competition.delete_left();
			}
		});
		btnDeleteLeft.setBackground(SystemColor.controlDkShadow);
		btnDeleteLeft.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Delete-16.png")));
		GridBagConstraints gbcBtnDeleteLeft = new GridBagConstraints();
		gbcBtnDeleteLeft.fill = GridBagConstraints.BOTH;
		gbcBtnDeleteLeft.insets = new Insets(5, 0, 5, 5);
		gbcBtnDeleteLeft.gridx = 2;
		gbcBtnDeleteLeft.gridy = 0;
		controls_panel.add(btnDeleteLeft, gbcBtnDeleteLeft);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbcSeparator1 = new GridBagConstraints();
		gbcSeparator1.fill = GridBagConstraints.BOTH;
		gbcSeparator1.insets = new Insets(5, 0, 5, 5);
		gbcSeparator1.gridx = 3;
		gbcSeparator1.gridy = 0;
		controls_panel.add(separator_1, gbcSeparator1);
		
		JButton btnBothWin = new JButton("Both Win (Q)");
		btnBothWin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				competition.both_win();
			}
		});
		btnBothWin.setBackground(SystemColor.controlDkShadow);
		btnBothWin.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Checked-16.png")));
		GridBagConstraints gbcButtonBothWin = new GridBagConstraints();
		gbcButtonBothWin.fill = GridBagConstraints.BOTH;
		gbcButtonBothWin.insets = new Insets(5, 0, 5, 5);
		gbcButtonBothWin.gridx = 4;
		gbcButtonBothWin.gridy = 0;
		controls_panel.add(btnBothWin, gbcButtonBothWin);
		
		JButton btnSkip = new JButton("Skip (S)");
		btnSkip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				competition.skip();
			}
		});
		btnSkip.setBackground(SystemColor.controlDkShadow);
		btnSkip.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Refresh-16.png")));
		GridBagConstraints gbcBtnSkip = new GridBagConstraints();
		gbcBtnSkip.insets = new Insets(5, 0, 5, 5);
		gbcBtnSkip.fill = GridBagConstraints.BOTH;
		gbcBtnSkip.gridx = 5;
		gbcBtnSkip.gridy = 0;
		controls_panel.add(btnSkip, gbcBtnSkip);
		
		JButton btnBothLose = new JButton("Both Lose (E)");
		btnBothLose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				competition.both_lose();
			}
		});
		btnBothLose.setBackground(SystemColor.controlDkShadow);
		btnBothLose.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Cancel-16.png")));
		GridBagConstraints gbcBtnBothLose = new GridBagConstraints();
		gbcBtnBothLose.fill = GridBagConstraints.BOTH;
		gbcBtnBothLose.insets = new Insets(5, 0, 5, 5);
		gbcBtnBothLose.gridx = 6;
		gbcBtnBothLose.gridy = 0;
		controls_panel.add(btnBothLose, gbcBtnBothLose);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.fill = GridBagConstraints.BOTH;
		gbc_separator_3.insets = new Insets(5, 0, 5, 5);
		gbc_separator_3.gridx = 7;
		gbc_separator_3.gridy = 0;
		controls_panel.add(separator_3, gbc_separator_3);
		
		JButton btnDeleteRight = new JButton("Delete");
		btnDeleteRight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				competition.delete_right();
			}
		});
		btnDeleteRight.setBackground(SystemColor.controlDkShadow);
		btnDeleteRight.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Delete-16.png")));
		GridBagConstraints gbcBtnDeleteRight = new GridBagConstraints();
		gbcBtnDeleteRight.fill = GridBagConstraints.BOTH;
		gbcBtnDeleteRight.insets = new Insets(5, 0, 5, 5);
		gbcBtnDeleteRight.gridx = 8;
		gbcBtnDeleteRight.gridy = 0;
		controls_panel.add(btnDeleteRight, gbcBtnDeleteRight);
		
		JButton btnRightWins = new JButton("Right Wins (D)");
		btnRightWins.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				competition.right_wins();
			}
		});
		btnRightWins.setBackground(SystemColor.controlDkShadow);
		btnRightWins.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Circled Right-16.png")));
		GridBagConstraints gbcBtnRightWins = new GridBagConstraints();
		gbcBtnRightWins.fill = GridBagConstraints.BOTH;
		gbcBtnRightWins.weightx = 1.0;
		gbcBtnRightWins.insets = new Insets(5, 0, 5, 5);
		gbcBtnRightWins.gridx = 9;
		gbcBtnRightWins.gridy = 0;
		controls_panel.add(btnRightWins, gbcBtnRightWins);
		
		JButton btnEmbiggenRight = new JButton("Embiggen");
		btnEmbiggenRight.setBackground(SystemColor.controlDkShadow);
		btnEmbiggenRight.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Resize-16.png")));
		GridBagConstraints gbc_btnEmbiggenRight = new GridBagConstraints();
		gbc_btnEmbiggenRight.insets = new Insets(5, 0, 5, 5);
		gbc_btnEmbiggenRight.fill = GridBagConstraints.BOTH;
		gbc_btnEmbiggenRight.gridx = 10;
		gbc_btnEmbiggenRight.gridy = 0;
		controls_panel.add(btnEmbiggenRight, gbc_btnEmbiggenRight);
		
		JProgressBar progressBar_1 = new JProgressBar();
		GridBagConstraints gbc_progressBar_1 = new GridBagConstraints();
		gbc_progressBar_1.fill = GridBagConstraints.BOTH;
		gbc_progressBar_1.gridwidth = 11;
		gbc_progressBar_1.insets = new Insets(0, 5, 5, 5);
		gbc_progressBar_1.gridx = 0;
		gbc_progressBar_1.gridy = 1;
		controls_panel.add(progressBar_1, gbc_progressBar_1);
		
		JPanel progress_panel = new JPanel();
		progress_panel.setBackground(SystemColor.controlDkShadow);
		frame_faceoff_main.getContentPane().add(progress_panel, BorderLayout.NORTH);
		GridBagLayout gbl_progress_panel = new GridBagLayout();
		gbl_progress_panel.columnWidths = new int[]{0, 289, 0, 0, 0};
		gbl_progress_panel.rowHeights = new int[]{14, 0};
		gbl_progress_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_progress_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		progress_panel.setLayout(gbl_progress_panel);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBackground(SystemColor.controlDkShadow);
		btnReset.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Restart-16.png")));
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(5, 5, 5, 5);
		gbc_btnReset.gridx = 0;
		gbc_btnReset.gridy = 0;
		progress_panel.add(btnReset, gbc_btnReset);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(15);
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.insets = new Insets(6, 0, 5, 5);
		gbc_progressBar.fill = GridBagConstraints.BOTH;
		gbc_progressBar.weightx = 1.0;
		gbc_progressBar.anchor = GridBagConstraints.NORTHWEST;
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 0;
		progress_panel.add(progressBar, gbc_progressBar);
		
		JButton btnNewButton_2 = new JButton("Commit");
		btnNewButton_2.setBackground(SystemColor.controlDkShadow);
		btnNewButton_2.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Save-16.png")));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 0;
		progress_panel.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(SystemColor.controlDkShadow);
		btnCancel.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Cancel-16.png")));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 3;
		gbc_btnCancel.gridy = 0;
		progress_panel.add(btnCancel, gbc_btnCancel);
		
		JPanel losers_panel = new JPanel();
		frame_faceoff_main.getContentPane().add(losers_panel, BorderLayout.WEST);
		GridBagLayout gbl_losers_panel = new GridBagLayout();
		gbl_losers_panel.columnWidths = new int[]{150, 0};
		gbl_losers_panel.rowHeights = new int[]{0, 0};
		gbl_losers_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_losers_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		losers_panel.setLayout(gbl_losers_panel);
		
		JScrollPane losing_images_scroll_pane = new JScrollPane();
		GridBagConstraints gbc_losing_images_scroll_pane = new GridBagConstraints();
		gbc_losing_images_scroll_pane.fill = GridBagConstraints.BOTH;
		gbc_losing_images_scroll_pane.gridx = 0;
		gbc_losing_images_scroll_pane.gridy = 0;
		losers_panel.add(losing_images_scroll_pane, gbc_losing_images_scroll_pane);
		
		JLabel lblLosingImages = new JLabel("Losing Images");
		lblLosingImages.setHorizontalAlignment(SwingConstants.CENTER);
		losing_images_scroll_pane.setColumnHeaderView(lblLosingImages);
		
		JPanel losing_images_panel = new JPanel();
		losing_images_scroll_pane.setViewportView(losing_images_panel);
		losing_images_panel.setLayout(new BoxLayout(losing_images_panel, BoxLayout.Y_AXIS));
		
		JPanel winners_panel = new JPanel();
		frame_faceoff_main.getContentPane().add(winners_panel, BorderLayout.EAST);
		GridBagLayout gbl_winners_panel = new GridBagLayout();
		gbl_winners_panel.columnWidths = new int[]{150, 0};
		gbl_winners_panel.rowHeights = new int[]{0, 0};
		gbl_winners_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_winners_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		winners_panel.setLayout(gbl_winners_panel);
		
		JScrollPane winning_images_scroll_pane = new JScrollPane();
		GridBagConstraints gbc_winning_images_scroll_pane = new GridBagConstraints();
		gbc_winning_images_scroll_pane.weighty = 1.0;
		gbc_winning_images_scroll_pane.fill = GridBagConstraints.BOTH;
		gbc_winning_images_scroll_pane.gridx = 0;
		gbc_winning_images_scroll_pane.gridy = 0;
		winners_panel.add(winning_images_scroll_pane, gbc_winning_images_scroll_pane);

		
		JLabel lblWinningImages = new JLabel("Winning Images");
		lblWinningImages.setHorizontalAlignment(SwingConstants.CENTER);
		winning_images_scroll_pane.setColumnHeaderView(lblWinningImages);
		
		JPanel winning_images_panel = new JPanel();
		winning_images_scroll_pane.setViewportView(winning_images_panel);
		winning_images_panel.setLayout(new BoxLayout(winning_images_panel, BoxLayout.Y_AXIS));
	}

	public void set_left(Competitor left){
		
	}
	
	public void set_right(Competitor right){
		
	}
}
