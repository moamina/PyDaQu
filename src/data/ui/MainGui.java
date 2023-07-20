package data.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class MainGui extends JFrame {

	public static void main(String args[]) {
        new MainGui();
    }

    private JFrame frame = new JFrame("Rest Testing");
    private JPanel checkboxes;

    public MainGui() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                frame.setSize(500, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                final JTextField path = new JTextField("getresponse.xls");

                JPanel fields = new JPanel();
                fields.add(path);

                JButton upload = new JButton("Upload");
                fields.add(upload);

                frame.add(fields, BorderLayout.NORTH);

                checkboxes = new JPanel(new GridBagLayout());
                JScrollPane scrollPane = new JScrollPane(checkboxes);

                frame.add(scrollPane);

                upload.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {

                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.gridwidth = GridBagConstraints.REMAINDER;
                        gbc.anchor = GridBagConstraints.WEST;
                        gbc.weightx = 1;

                        checkboxes.add(new JLabel("<HTML>A<br>B<br>C<br>D<br>E<br>F<br>G<br>H<br></HTML>"), gbc);

                        try {
                            int noOfRows = 100;
                            for (int row = 0; row < noOfRows; row++, row++) {
                                int p = (int) ((Math.random() * 2) + 1);
                                System.out.println(p);
                                if (p == 1) {

                                    JCheckBox cb = new JCheckBox("CheckBox");
                                    checkboxes.add(cb, gbc);

                                } else if (p == 2) {

                                    JCheckBox cb1 = new JCheckBox("CheckBox ");
                                    JPanel stuff = new JPanel();
                                    stuff.add(cb1);
                                    stuff.add(new JTextField(10));
                                    checkboxes.add(stuff, gbc);

                                }
                            }

                        } catch (Exception e) {
                            System.out.println("Exception is " + e);
                        }

                        checkboxes.revalidate();
                        checkboxes.repaint();
                    }
                });

                //JOptionPane.showMessageDialog(null, "Done", "Alert", WIDTH);
                JCheckBox a = new JCheckBox("A");
                JCheckBox b = new JCheckBox("B");
                JLabel label = new JLabel("Option");

                JPanel stuff = new JPanel();
                stuff.add(label);
                stuff.add(a);
                stuff.add(b);
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.anchor = GridBagConstraints.WEST;
                gbc.weightx = 1;
                checkboxes.add(stuff, gbc);

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }
        });

    }
}
