package GUI;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import DB.CommInterface;
import DB.Util;

public class GetLowPriceFilmDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JFormattedTextField priceField;

    public GetLowPriceFilmDialog() {
        setTitle("Get Low Price Films");
        setBounds(100, 100, 289, 306);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contentPanel.setBounds(0, 0, 263, 228);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel);
        contentPanel.setLayout(null);

        {
            JLabel lblPrice = new JLabel("Max Price:");
            lblPrice.setBounds(26, 31, 75, 14);
            contentPanel.add(lblPrice);
        }
        {
            JLabel lblStore = new JLabel("Store:");
            lblStore.setBounds(26, 65, 93, 14);
            contentPanel.add(lblStore);
        }
        {

            NumberFormat format = NumberFormat.getIntegerInstance();
            format.setGroupingUsed(false);
            NumberFormatter formatter = new NumberFormatter(format);
            formatter.setValueClass(Integer.class);
            formatter.setMinimum(0);
            formatter.setMaximum(Integer.MAX_VALUE);
            formatter.setAllowsInvalid(false);
            formatter.setCommitsOnValidEdit(true);
            priceField = new JFormattedTextField(formatter);
            //priceField = new JTextField();
            priceField.setBounds(143, 34, 115, 20);
            contentPanel.add(priceField);
            priceField.setColumns(10);
        }

        JComboBox<String> storeComboBox = new JComboBox<String>(CommInterface.getStoreNames());
        storeComboBox.setBounds(143, 62, 115, 20);
        contentPanel.add(storeComboBox);

        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(0, 228, 263, 33);
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane);
            {
                JButton okButton = new JButton("Search");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        if (priceField.getText().equals("")) {
                            Util.Alert("Please enter all data");
                        } else {
                            String Array[];
                            Array=(CommInterface.getLowPriceFilms(priceField.getText()));
                            for (int i=0; i<Array.length;i++) {
                                System.out.print(Array[i]);
                            }
                            dispose();
                        }
                    }
                });
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
}
