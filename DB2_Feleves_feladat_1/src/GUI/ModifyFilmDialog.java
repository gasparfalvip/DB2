package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import sun.java2d.pipe.SpanShapeRenderer;

public class ModifyFilmDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField filmNameField;
	private JFormattedTextField priceField;
	private JDateChooser datepicker;
	
	/**
	 * Create the dialog.
	 */
	public ModifyFilmDialog(int id, String name, String date, int price, int fk) {
		setTitle("Modify Movie");
		setBounds(100, 100, 289, 306);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPanel.setBounds(0, 0, 263, 228);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblFilmName = new JLabel("Movie name:");
			lblFilmName.setBounds(26, 31, 93, 14);
			contentPanel.add(lblFilmName);
		}
		{
			filmNameField = new JTextField();
			filmNameField.setBounds(143, 28, 115, 20);
			filmNameField.setText(name);
			contentPanel.add(filmNameField);
			filmNameField.setColumns(10);
		}
		{
			JLabel lblReleaseDate = new JLabel("Release date:");
			lblReleaseDate.setBounds(26, 65, 93, 14);
			contentPanel.add(lblReleaseDate);
		}
		{
			JLabel lblPrice = new JLabel("Price:");
			lblPrice.setBounds(26, 93, 75, 14);
			contentPanel.add(lblPrice);
		}
		{
			JLabel lblStore = new JLabel("Store:");
			lblStore.setBounds(26, 118, 93, 14);
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
			priceField.setBounds(143, 90, 115, 20);
			priceField.setValue(price);
			contentPanel.add(priceField);
			priceField.setColumns(10);
		}

		JComboBox<String> storeComboBox = new JComboBox<String>(CommInterface.getStoreNames());
		storeComboBox.setBounds(143, 115, 115, 20);
		contentPanel.add(storeComboBox);
		{
			datepicker = new JDateChooser();
			//datepicker.setDateFormatString("yyyy.MM.dd");
			SimpleDateFormat formatted = new SimpleDateFormat("yyyy.MM.dd");
			try {
				Date fdate = formatted.parse(date);
				datepicker.setDate(fdate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			JTextFieldDateEditor editor = (JTextFieldDateEditor) datepicker.getDateEditor();
			editor.setEditable(false);
			datepicker.setBounds(143, 59, 115, 20);
			contentPanel.add(datepicker);
		}
		
		/*JComboBox<String> storeComboBox = new JComboBox<String>(CommInterface.getStoreNames());
		storeComboBox.setBounds(143, 115, 115, 20);
		contentPanel.add(storeComboBox);
		{
			dateField = new JTextField();
			dateField.setBounds(143, 28, 115, 20);
			dateField.setText(date);
			contentPanel.add(dateField);
			dateField.setColumns(10);
			dateField.setEditable(false);
		}*/
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 228, 263, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("Modify");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (filmNameField.getText().equals("") || priceField.getText().equals("")) {
							Util.Alert("Please enter all data");
						}else {
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
							String releaseDate = dateFormat.format(datepicker.getDate());
							CommInterface.ModifyFilm(id,filmNameField.getText(), releaseDate, priceField.getText(), storeComboBox.getSelectedItem());
							
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
