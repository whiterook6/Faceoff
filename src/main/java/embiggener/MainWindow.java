package embiggener;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.Dimension;
import javax.swing.JScrollBar;
import java.awt.SystemColor;

public class MainWindow {

	private JFrame frame_faceoff_main;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame_faceoff_main.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame_faceoff_main = new JFrame();
		frame_faceoff_main.setMinimumSize(new Dimension(1000, 500));
		frame_faceoff_main.setTitle("Face Off");
		frame_faceoff_main.setBounds(100, 100, 939, 502);
		frame_faceoff_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_faceoff_main.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel images_panel = new JPanel();
		frame_faceoff_main.getContentPane().add(images_panel, BorderLayout.CENTER);
		GridBagLayout gbl_images_panel = new GridBagLayout();
		gbl_images_panel.columnWidths = new int[]{0, 0, 0};
		gbl_images_panel.rowHeights = new int[]{0, 0};
		gbl_images_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_images_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		images_panel.setLayout(gbl_images_panel);
		
		JPanel left_image_panel = new JPanel();
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
		
		JPanel right_image_panel = new JPanel();
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
		btnNewButton.setIcon(new ImageIcon(MainWindow.class.getResource("/embiggener/Resize-16.png")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		controls_panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnLeftWinsa = new JButton("Left Wins (A)");
		btnLeftWinsa.setBackground(SystemColor.controlDkShadow);
		btnLeftWinsa.setIcon(new ImageIcon(MainWindow.class.getResource("/embiggener/Circled Left-16.png")));
		btnLeftWinsa.setSelectedIcon(new ImageIcon(MainWindow.class.getResource("/embiggener/Circled Left-64.png")));
		GridBagConstraints gbc_btnLeftWinsa = new GridBagConstraints();
		gbc_btnLeftWinsa.insets = new Insets(5, 0, 5, 5);
		gbc_btnLeftWinsa.weightx = 1.0;
		gbc_btnLeftWinsa.fill = GridBagConstraints.BOTH;
		gbc_btnLeftWinsa.gridx = 1;
		gbc_btnLeftWinsa.gridy = 0;
		controls_panel.add(btnLeftWinsa, gbc_btnLeftWinsa);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setBackground(SystemColor.controlDkShadow);
		btnNewButton_1.setIcon(new ImageIcon(MainWindow.class.getResource("/embiggener/Delete-16.png")));
		btnNewButton_1.setSelectedIcon(new ImageIcon(MainWindow.class.getResource("/embiggener/Delete-64.png")));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(5, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		controls_panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1.insets = new Insets(5, 0, 5, 5);
		gbc_separator_1.gridx = 3;
		gbc_separator_1.gridy = 0;
		controls_panel.add(separator_1, gbc_separator_1);
		
		JButton btnNewButton_3 = new JButton("Both Win (Q)");
		btnNewButton_3.setBackground(SystemColor.controlDkShadow);
		btnNewButton_3.setIcon(new ImageIcon(MainWindow.class.getResource("/embiggener/Checked-16.png")));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_3.insets = new Insets(5, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 4;
		gbc_btnNewButton_3.gridy = 0;
		controls_panel.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JButton btnSkip = new JButton("Skip (S)");
		btnSkip.setBackground(SystemColor.controlDkShadow);
		btnSkip.setIcon(new ImageIcon(MainWindow.class.getResource("/embiggener/Refresh-16.png")));
		btnSkip.setSelectedIcon(new ImageIcon(MainWindow.class.getResource("/embiggener/Refresh-64.png")));
		GridBagConstraints gbc_btnSkip = new GridBagConstraints();
		gbc_btnSkip.insets = new Insets(5, 0, 5, 5);
		gbc_btnSkip.fill = GridBagConstraints.BOTH;
		gbc_btnSkip.gridx = 5;
		gbc_btnSkip.gridy = 0;
		controls_panel.add(btnSkip, gbc_btnSkip);
		
		JButton btnNewButton_4 = new JButton("Both Lose (E)");
		btnNewButton_4.setBackground(SystemColor.controlDkShadow);
		btnNewButton_4.setIcon(new ImageIcon(MainWindow.class.getResource("/embiggener/Cancel-16.png")));
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_4.insets = new Insets(5, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 6;
		gbc_btnNewButton_4.gridy = 0;
		controls_panel.add(btnNewButton_4, gbc_btnNewButton_4);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.fill = GridBagConstraints.BOTH;
		gbc_separator_3.insets = new Insets(5, 0, 5, 5);
		gbc_separator_3.gridx = 7;
		gbc_separator_3.gridy = 0;
		controls_panel.add(separator_3, gbc_separator_3);
		
		JButton btnDeleteRight = new JButton("Delete");
		btnDeleteRight.setBackground(SystemColor.controlDkShadow);
		btnDeleteRight.setIcon(new ImageIcon(MainWindow.class.getResource("/embiggener/Delete-16.png")));
		GridBagConstraints gbc_btnDeleteRight = new GridBagConstraints();
		gbc_btnDeleteRight.fill = GridBagConstraints.BOTH;
		gbc_btnDeleteRight.insets = new Insets(5, 0, 5, 5);
		gbc_btnDeleteRight.gridx = 8;
		gbc_btnDeleteRight.gridy = 0;
		controls_panel.add(btnDeleteRight, gbc_btnDeleteRight);
		
		JButton btnRightWinsd = new JButton("Right Wins (D)");
		btnRightWinsd.setBackground(SystemColor.controlDkShadow);
		btnRightWinsd.setIcon(new ImageIcon(MainWindow.class.getResource("/embiggener/Circled Right-16.png")));
		GridBagConstraints gbc_btnRightWinsd = new GridBagConstraints();
		gbc_btnRightWinsd.fill = GridBagConstraints.BOTH;
		gbc_btnRightWinsd.weightx = 1.0;
		gbc_btnRightWinsd.insets = new Insets(5, 0, 5, 5);
		gbc_btnRightWinsd.gridx = 9;
		gbc_btnRightWinsd.gridy = 0;
		controls_panel.add(btnRightWinsd, gbc_btnRightWinsd);
		
		JButton btnEmbiggenRight = new JButton("Embiggen");
		btnEmbiggenRight.setBackground(SystemColor.controlDkShadow);
		btnEmbiggenRight.setIcon(new ImageIcon(MainWindow.class.getResource("/embiggener/Resize-16.png")));
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
		btnReset.setIcon(new ImageIcon(MainWindow.class.getResource("/embiggener/Restart-16.png")));
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
		btnNewButton_2.setIcon(new ImageIcon(MainWindow.class.getResource("/embiggener/Save-16.png")));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 0;
		progress_panel.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(SystemColor.controlDkShadow);
		btnCancel.setIcon(new ImageIcon(MainWindow.class.getResource("/embiggener/Cancel-16.png")));
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

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
