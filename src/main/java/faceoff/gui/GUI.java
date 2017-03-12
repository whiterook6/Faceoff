package faceoff.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
	public final JFrame mainFrame;
	private JPanel leftImagePanel;
	private JPanel rightImagePanel;
	private JProgressBar mainProgressBar;
	private List<JButton> buttons;
	private JProgressBar secondaryProgressBar;

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public GUI(Competition competition) throws IOException {
		this.buttons = new LinkedList<JButton>();
		this.competition = competition;
		mainFrame = new JFrame();
		initialize();
		mainFrame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame.setMinimumSize(new Dimension(1000, 500));
		mainFrame.setTitle("Face Off");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel imagesPanel = new JPanel();
		mainFrame.getContentPane().add(imagesPanel, BorderLayout.CENTER);
		GridBagLayout gblImagesPanel = new GridBagLayout();
		gblImagesPanel.columnWidths = new int[]{0, 0, 0};
		gblImagesPanel.rowHeights = new int[]{0, 0};
		gblImagesPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gblImagesPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		imagesPanel.setLayout(gblImagesPanel);
		
		leftImagePanel = new JPanel();
		GridBagConstraints gbcLeftImagePanel = new GridBagConstraints();
		gbcLeftImagePanel.insets = new Insets(0, 0, 0, 5);
		gbcLeftImagePanel.weightx = 1.0;
		gbcLeftImagePanel.fill = GridBagConstraints.BOTH;
		gbcLeftImagePanel.gridx = 0;
		gbcLeftImagePanel.gridy = 0;
		imagesPanel.add(leftImagePanel, gbcLeftImagePanel);
		leftImagePanel.setLayout(new BoxLayout(leftImagePanel, BoxLayout.Y_AXIS));
		
		rightImagePanel = new JPanel();
		GridBagConstraints gbcRightImagePanel = new GridBagConstraints();
		gbcRightImagePanel.weightx = 1.0;
		gbcRightImagePanel.fill = GridBagConstraints.BOTH;
		gbcRightImagePanel.gridx = 1;
		gbcRightImagePanel.gridy = 0;
		imagesPanel.add(rightImagePanel, gbcRightImagePanel);
		rightImagePanel.setLayout(new BoxLayout(rightImagePanel, BoxLayout.Y_AXIS));
		
		JPanel controlsPanel = new JPanel();
		controlsPanel.setBackground(SystemColor.controlDkShadow);
		mainFrame.getContentPane().add(controlsPanel, BorderLayout.SOUTH);
		GridBagLayout gblControlsPanel = new GridBagLayout();
		gblControlsPanel.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gblControlsPanel.rowHeights = new int[]{0, 0};
		gblControlsPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gblControlsPanel.rowWeights = new double[]{0.0, 0.0};
		controlsPanel.setLayout(gblControlsPanel);
		
		JButton btnEmbiggenLeft = new JButton("Embiggen");
		btnEmbiggenLeft.setBackground(SystemColor.controlDkShadow);
		btnEmbiggenLeft.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Resize-16.png")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		controlsPanel.add(btnEmbiggenLeft, gbc_btnNewButton);
		buttons.add(btnEmbiggenLeft);
		
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
		controlsPanel.add(btnLeftWins, gbcBtnLeftWins);
		buttons.add(btnLeftWins);
		
		JButton btnDeleteLeft = new JButton("Delete");
		btnDeleteLeft.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				competition.deleteLeft();
			}
		});
		btnDeleteLeft.setBackground(SystemColor.controlDkShadow);
		btnDeleteLeft.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Delete-16.png")));
		GridBagConstraints gbcBtnDeleteLeft = new GridBagConstraints();
		gbcBtnDeleteLeft.fill = GridBagConstraints.BOTH;
		gbcBtnDeleteLeft.insets = new Insets(5, 0, 5, 5);
		gbcBtnDeleteLeft.gridx = 2;
		gbcBtnDeleteLeft.gridy = 0;
		controlsPanel.add(btnDeleteLeft, gbcBtnDeleteLeft);
		buttons.add(btnDeleteLeft);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbcSeparator1 = new GridBagConstraints();
		gbcSeparator1.fill = GridBagConstraints.BOTH;
		gbcSeparator1.insets = new Insets(5, 0, 5, 5);
		gbcSeparator1.gridx = 3;
		gbcSeparator1.gridy = 0;
		controlsPanel.add(separator_1, gbcSeparator1);
		
		JButton btnBothWin = new JButton("Both Win (Q)");
		btnBothWin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				competition.bothWin();
			}
		});
		btnBothWin.setBackground(SystemColor.controlDkShadow);
		btnBothWin.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Checked-16.png")));
		GridBagConstraints gbcButtonBothWin = new GridBagConstraints();
		gbcButtonBothWin.fill = GridBagConstraints.BOTH;
		gbcButtonBothWin.insets = new Insets(5, 0, 5, 5);
		gbcButtonBothWin.gridx = 4;
		gbcButtonBothWin.gridy = 0;
		controlsPanel.add(btnBothWin, gbcButtonBothWin);
		buttons.add(btnBothWin);
		
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
		controlsPanel.add(btnSkip, gbcBtnSkip);
		buttons.add(btnSkip);
		
		JButton btnBothLose = new JButton("Both Lose (E)");
		btnBothLose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				competition.bothLose();
			}
		});
		btnBothLose.setBackground(SystemColor.controlDkShadow);
		btnBothLose.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Cancel-16.png")));
		GridBagConstraints gbcBtnBothLose = new GridBagConstraints();
		gbcBtnBothLose.fill = GridBagConstraints.BOTH;
		gbcBtnBothLose.insets = new Insets(5, 0, 5, 5);
		gbcBtnBothLose.gridx = 6;
		gbcBtnBothLose.gridy = 0;
		controlsPanel.add(btnBothLose, gbcBtnBothLose);
		buttons.add(btnBothLose);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.fill = GridBagConstraints.BOTH;
		gbc_separator_3.insets = new Insets(5, 0, 5, 5);
		gbc_separator_3.gridx = 7;
		gbc_separator_3.gridy = 0;
		controlsPanel.add(separator_3, gbc_separator_3);
		
		JButton btnDeleteRight = new JButton("Delete");
		btnDeleteRight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				competition.deleteRight();
			}
		});
		btnDeleteRight.setBackground(SystemColor.controlDkShadow);
		btnDeleteRight.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Delete-16.png")));
		GridBagConstraints gbcBtnDeleteRight = new GridBagConstraints();
		gbcBtnDeleteRight.fill = GridBagConstraints.BOTH;
		gbcBtnDeleteRight.insets = new Insets(5, 0, 5, 5);
		gbcBtnDeleteRight.gridx = 8;
		gbcBtnDeleteRight.gridy = 0;
		controlsPanel.add(btnDeleteRight, gbcBtnDeleteRight);
		buttons.add(btnDeleteRight);
		
		JButton btnRightWins = new JButton("Right Wins (D)");
		btnRightWins.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				competition.rightWins();
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
		controlsPanel.add(btnRightWins, gbcBtnRightWins);
		buttons.add(btnRightWins);
		
		JButton btnEmbiggenRight = new JButton("Embiggen");
		btnEmbiggenRight.setBackground(SystemColor.controlDkShadow);
		btnEmbiggenRight.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Resize-16.png")));
		GridBagConstraints gbc_btnEmbiggenRight = new GridBagConstraints();
		gbc_btnEmbiggenRight.insets = new Insets(5, 0, 5, 5);
		gbc_btnEmbiggenRight.fill = GridBagConstraints.BOTH;
		gbc_btnEmbiggenRight.gridx = 10;
		gbc_btnEmbiggenRight.gridy = 0;
		controlsPanel.add(btnEmbiggenRight, gbc_btnEmbiggenRight);
		buttons.add(btnEmbiggenRight);
		
		secondaryProgressBar = new JProgressBar();
		GridBagConstraints gbcSecondaryProgressBar = new GridBagConstraints();
		gbcSecondaryProgressBar.fill = GridBagConstraints.BOTH;
		gbcSecondaryProgressBar.gridwidth = 11;
		gbcSecondaryProgressBar.insets = new Insets(0, 5, 5, 5);
		gbcSecondaryProgressBar.gridx = 0;
		gbcSecondaryProgressBar.gridy = 1;
		controlsPanel.add(secondaryProgressBar, gbcSecondaryProgressBar);
		
		JPanel progressPanel = new JPanel();
		progressPanel.setBackground(SystemColor.controlDkShadow);
		mainFrame.getContentPane().add(progressPanel, BorderLayout.NORTH);
		GridBagLayout gbl_progress_panel = new GridBagLayout();
		gbl_progress_panel.columnWidths = new int[]{0, 289, 0, 0, 0};
		gbl_progress_panel.rowHeights = new int[]{14, 0};
		gbl_progress_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_progress_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		progressPanel.setLayout(gbl_progress_panel);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBackground(SystemColor.controlDkShadow);
		btnReset.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Restart-16.png")));
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(5, 5, 5, 5);
		gbc_btnReset.gridx = 0;
		gbc_btnReset.gridy = 0;
		progressPanel.add(btnReset, gbc_btnReset);
		
		mainProgressBar = new JProgressBar();
		mainProgressBar.setMinimum(0);
		mainProgressBar.setMaximum(100);
		GridBagConstraints gbcMainProgressBar = new GridBagConstraints();
		gbcMainProgressBar.insets = new Insets(6, 0, 5, 5);
		gbcMainProgressBar.fill = GridBagConstraints.BOTH;
		gbcMainProgressBar.weightx = 1.0;
		gbcMainProgressBar.anchor = GridBagConstraints.NORTHWEST;
		gbcMainProgressBar.gridx = 1;
		gbcMainProgressBar.gridy = 0;
		progressPanel.add(mainProgressBar, gbcMainProgressBar);
		
		JButton btnCommit = new JButton("Commit");
		btnCommit.setBackground(SystemColor.controlDkShadow);
		btnCommit.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Save-16.png")));
		GridBagConstraints gbcBtnCommit = new GridBagConstraints();
		gbcBtnCommit.insets = new Insets(0, 0, 0, 5);
		gbcBtnCommit.gridx = 2;
		gbcBtnCommit.gridy = 0;
		progressPanel.add(btnCommit, gbcBtnCommit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(SystemColor.controlDkShadow);
		btnCancel.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Cancel-16.png")));
		GridBagConstraints gbcBtnCancel = new GridBagConstraints();
		gbcBtnCancel.gridx = 3;
		gbcBtnCancel.gridy = 0;
		progressPanel.add(btnCancel, gbcBtnCancel);
		
		JPanel losersPanel = new JPanel();
		mainFrame.getContentPane().add(losersPanel, BorderLayout.WEST);
		GridBagLayout glbLosersPanel = new GridBagLayout();
		glbLosersPanel.columnWidths = new int[]{150, 0};
		glbLosersPanel.rowHeights = new int[]{0, 0};
		glbLosersPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		glbLosersPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		losersPanel.setLayout(glbLosersPanel);
		
		JScrollPane losingImagesScrollPane = new JScrollPane();
		GridBagConstraints gbcLosingImagesScollPane = new GridBagConstraints();
		gbcLosingImagesScollPane.fill = GridBagConstraints.BOTH;
		gbcLosingImagesScollPane.gridx = 0;
		gbcLosingImagesScollPane.gridy = 0;
		losersPanel.add(losingImagesScrollPane, gbcLosingImagesScollPane);
		
		JLabel lblLosingImages = new JLabel("Losing Images");
		lblLosingImages.setHorizontalAlignment(SwingConstants.CENTER);
		losingImagesScrollPane.setColumnHeaderView(lblLosingImages);
		
		JPanel losing_images_panel = new JPanel();
		losingImagesScrollPane.setViewportView(losing_images_panel);
		losing_images_panel.setLayout(new BoxLayout(losing_images_panel, BoxLayout.Y_AXIS));
		
		JPanel winnersPanel = new JPanel();
		mainFrame.getContentPane().add(winnersPanel, BorderLayout.EAST);
		GridBagLayout gbl_winners_panel = new GridBagLayout();
		gbl_winners_panel.columnWidths = new int[]{150, 0};
		gbl_winners_panel.rowHeights = new int[]{0, 0};
		gbl_winners_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_winners_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		winnersPanel.setLayout(gbl_winners_panel);
		
		JScrollPane winningImagesScrollPane = new JScrollPane();
		GridBagConstraints gbc_winning_images_scroll_pane = new GridBagConstraints();
		gbc_winning_images_scroll_pane.weighty = 1.0;
		gbc_winning_images_scroll_pane.fill = GridBagConstraints.BOTH;
		gbc_winning_images_scroll_pane.gridx = 0;
		gbc_winning_images_scroll_pane.gridy = 0;
		winnersPanel.add(winningImagesScrollPane, gbc_winning_images_scroll_pane);

		
		JLabel lblWinningImages = new JLabel("Winning Images");
		lblWinningImages.setHorizontalAlignment(SwingConstants.CENTER);
		winningImagesScrollPane.setColumnHeaderView(lblWinningImages);
		
		JPanel winningImagesPanel = new JPanel();
		winningImagesScrollPane.setViewportView(winningImagesPanel);
		winningImagesPanel.setLayout(new BoxLayout(winningImagesPanel, BoxLayout.Y_AXIS));
	}

	public void setLeft(Competitor left){
		
	}
	
	public void setMainProgress(int progress){
		mainProgressBar.setValue(progress);
	}
	
	public void setMainProgressMaximum(int maximum){
		mainProgressBar.setMaximum(maximum);
	}
	
	public void setPause(boolean is_paused){
		mainProgressBar.setIndeterminate(is_paused);
		for (JButton button : buttons) {
			button.setEnabled(!is_paused);
		}
	}
	
	public void setRight(Competitor right){
		
	}
	
	public void setSecondaryProgress(int progress){
		secondaryProgressBar.setValue(progress);
	}
}
