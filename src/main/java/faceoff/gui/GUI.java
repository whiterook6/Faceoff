package faceoff.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.imgscalr.Scalr;

import faceoff.competition.Competition;
import faceoff.competition.Competitor;

public class GUI {

	private final Competition competition;
	public final JFrame mainFrame;
	private JLabel leftImageLabel;
	private JLabel rightImageLabel;
	private JProgressBar mainProgressBar;
	private List<JButton> buttons;
	private JProgressBar secondaryProgressBar;
	private JPanel imagesPanel;

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
		
		Container contentPane = mainFrame.getContentPane();
		contentPane.setBackground(Colors.DARK_BG);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		imagesPanel = new JPanel();
		imagesPanel.setBackground(Colors.DARK_BG);
		contentPane.add(imagesPanel, BorderLayout.CENTER);
		GridBagLayout gblImagesPanel = new GridBagLayout();
		gblImagesPanel.columnWidths = new int[]{0, 0, 0};
		gblImagesPanel.rowHeights = new int[]{0, 0};
		gblImagesPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gblImagesPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		imagesPanel.setLayout(gblImagesPanel);
		
		leftImageLabel = new JLabel();
		leftImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbcLeftImageLabel = new GridBagConstraints();
		gbcLeftImageLabel.fill = GridBagConstraints.BOTH;
		gbcLeftImageLabel.gridx = 0;
		gbcLeftImageLabel.gridy = 0;
		gbcLeftImageLabel.weightx = 1.0;
		imagesPanel.add(leftImageLabel, gbcLeftImageLabel);
		
		rightImageLabel = new JLabel();
		rightImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbcRightImageLabel = new GridBagConstraints();
		gbcRightImageLabel.fill = GridBagConstraints.BOTH;
		gbcRightImageLabel.gridx = 1;
		gbcRightImageLabel.gridy = 0;
		gbcRightImageLabel.weightx = 1.0;
		imagesPanel.add(rightImageLabel, gbcRightImageLabel);
		
		JPanel controlsPanel = new JPanel();
		controlsPanel.setBackground(Colors.DARK_BG);
		contentPane.add(controlsPanel, BorderLayout.SOUTH);
		GridBagLayout gblControlsPanel = new GridBagLayout();
		gblControlsPanel.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gblControlsPanel.rowHeights = new int[]{0, 0};
		gblControlsPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gblControlsPanel.rowWeights = new double[]{0.0, 0.0};
		controlsPanel.setLayout(gblControlsPanel);
		
		JButton btnEmbiggenLeft = new JButton("Embiggen");
		styleButton(btnEmbiggenLeft);
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
		progressPanel.setBackground(Colors.DARK_BG);
		contentPane.add(progressPanel, BorderLayout.NORTH);
		GridBagLayout gbl_progress_panel = new GridBagLayout();
		gbl_progress_panel.columnWidths = new int[]{0, 289, 0, 0, 0};
		gbl_progress_panel.rowHeights = new int[]{14, 0};
		gbl_progress_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_progress_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		progressPanel.setLayout(gbl_progress_panel);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Restart-16.png")));
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(5, 5, 5, 5);
		gbc_btnReset.gridx = 0;
		gbc_btnReset.gridy = 0;
		progressPanel.add(btnReset, gbc_btnReset);
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				competition.startCompetition();
			}
		});
		
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
		btnCommit.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Save-16.png")));
		GridBagConstraints gbcBtnCommit = new GridBagConstraints();
		gbcBtnCommit.insets = new Insets(0, 0, 0, 5);
		gbcBtnCommit.gridx = 2;
		gbcBtnCommit.gridy = 0;
		progressPanel.add(btnCommit, gbcBtnCommit);
		btnCommit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				competition.commit();
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon(GUI.class.getResource("/faceoff/gui/icons/Cancel-16.png")));
		GridBagConstraints gbcBtnCancel = new GridBagConstraints();
		gbcBtnCancel.gridx = 3;
		gbcBtnCancel.gridy = 0;
		progressPanel.add(btnCancel, gbcBtnCancel);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				competition.cancel();
			}
		});
	}
	
	public void battle(Competitor left, Competitor right) throws IOException{
		BufferedImage leftImage = left.lazyLoadImage(),
			rightImage = right.lazyLoadImage(),
			leftImageScaled,
			rightImageScaled;

		double widthViewport = imagesPanel.getWidth(),
			heightViewport = imagesPanel.getHeight(),
		
			widthLeft = left.getWidth(),
			heightLeft = left.getHeight(),
			
			widthRight = right.getWidth(),
			heightRight = right.getHeight();
		
		double ratioLeft = (heightRight * widthViewport) / (heightLeft * widthRight + heightRight * widthLeft),
			ratioRight = (heightLeft * ratioLeft) / heightRight;
		
		if (ratioLeft >= 1.0){
			leftImageScaled = leftImage;
		} else {
			ratioLeft = Math.min(ratioLeft, heightViewport / heightLeft);
			int widthLeftScaled = (int)(widthLeft * ratioLeft),
				heightLeftScaled = (int)(heightLeft * ratioLeft);
			leftImageScaled = Scalr.resize(leftImage, widthLeftScaled, heightLeftScaled);
		}
		
		if (ratioRight >= 1.0){
			rightImageScaled = rightImage;
		} else {
			ratioRight = Math.min(ratioRight,  heightViewport / heightRight);
			
			int widthRightScaled = (int)(widthRight * ratioRight),
				heightRightScaled = (int)(heightRight * ratioRight);
			rightImageScaled = Scalr.resize(rightImage, widthRightScaled, heightRightScaled);
		}

		leftImageLabel.setIcon(new ImageIcon(leftImageScaled));
		rightImageLabel.setIcon(new ImageIcon(rightImageScaled));
	}
	
	private void styleButton(JButton button){
        button.setOpaque(true);
		button.setBackground(Colors.BTN);
		button.setForeground(Colors.LIGHT_FG);
		button.setBorderPainted(false);
	}

	public void setMainProgress(int progress){
		mainProgressBar.setValue(progress);
	}
	
	public void incrementMainProgress(){
		mainProgressBar.setValue(mainProgressBar.getValue() + 1);
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
	
	public void setSecondaryProgress(int progress){
		secondaryProgressBar.setValue(progress);
	}
}
