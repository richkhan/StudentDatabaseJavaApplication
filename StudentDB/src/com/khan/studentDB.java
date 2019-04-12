package com.khan;

import java.awt.EventQueue;

import javax.swing.JFrame;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class studentDB {

	private JFrame frmStudentDatabase;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					studentDB window = new studentDB();
					window.frmStudentDatabase.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	private void filter(String searchText)
	{
		
		DefaultTableModel searchModel = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter <DefaultTableModel>(searchModel);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(searchText));
	}

	/**
	 * Create the application.
	 */
	public studentDB() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStudentDatabase = new JFrame();
		frmStudentDatabase.setTitle("Student Database");
		frmStudentDatabase.setBounds(100, 100, 680, 622);
		frmStudentDatabase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		JMenuBar menuBar = new JMenuBar();
		frmStudentDatabase.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnFile);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnHelp);
		frmStudentDatabase.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search Database: -");
		lblNewLabel.setBounds(52, 14, 128, 28);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		frmStudentDatabase.getContentPane().add(lblNewLabel);
		//tf.setEditable(false);

		Object[] row = new Object[5];
		
		
		
	

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 92, 566, 446);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmStudentDatabase.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"First Name", "Last Name", "CUNY ID", "GPA", "Venus Login"
			}
		));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(table);
		
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		
		
		JButton btnImportData = new JButton("Import Data");
		btnImportData.setToolTipText("Imports an existing txt file");
		btnImportData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// File chooser 
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Choose a txt file.");
				jfc.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
				jfc.addChoosableFileFilter(filter);
				
			
				int returnValue = jfc.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION)
				{
					//System.out.println(jfc.getSelectedFile().getPath());	
					

					String filePath = jfc.getSelectedFile().getPath();	// gets the file path 
					File file = new File (filePath);
					
					try {
						BufferedReader br = new BufferedReader(new FileReader(file));
						//String firstLine = br.readLine().trim();
						//String [] columnName = firstLine.split(",");
						
						Object [] tableLines = br.lines().toArray();
						
						
						
						for (int i = 0; i < tableLines.length; i++)
						{
							String line = tableLines[i].toString().trim();
							String [] dataRow = line.split(",");
							//DefaultTableModel model = (DefaultTableModel)table.getModel();
							//ArrayList<Integer> test = new ArrayList<Integer> (i);
							//model.addColumn(test);
							model.addRow(dataRow);
							
							//System.out.println(tableLines.length);
						}
						
						
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		
				}
			}
			

		});
		btnImportData.setBounds(52, 53, 128, 28);
		btnImportData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmStudentDatabase.getContentPane().add(btnImportData);
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setToolTipText("Add a row to the table");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				model.addRow(row);

				
				
//				if (spinner.getValue() == "First Name")
//				{
//					row[0] = textField.getText();
//				}
//				else if (spinner.getValue() == "Last Name")
//				{
//					row[1] = textField.getText();
//				}
//				else if (spinner.getValue() == "CUNY ID")
//				{
//					row[2] = textField.getText();
//				}
//				else if (spinner.getValue() == "GPA")
//				{
//					row[3] = textField.getText();
//				}
//				else if (spinner.getValue() == "Venus Login")
//				{
//					row [4] = textField.getText();
//				}
//				
//				for (int j = 0; j < row.length; j ++)
//				{
//					row [j] = "";
//				}	
				
			}
			
		});
		
		
		
		btnAdd.setBounds(549, 11, 105, 28);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmStudentDatabase.getContentPane().add(btnAdd);
		
		JButton btnExportData = new JButton("Export Data");
		btnExportData.setToolTipText("Export Data to an exsisting txt file");
		btnExportData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				// File chooser 
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Choose a txt file.");
				jfc.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
				jfc.addChoosableFileFilter(filter);
				
				int returnValue = jfc.showSaveDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION)
				{
		
					try {
					String filePath = jfc.getSelectedFile().getPath();	// gets the file path 
					File file = new File(filePath);
					if(!file.exists())
					{
						file.createNewFile();
					}
					
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					
					for(int i = 0; i < table.getRowCount(); i++)
					{
						for(int j = 0; j < table.getColumnCount();j++)
						{
							bw.write(table.getModel().getValueAt(i, j) + ",");
							
						}
						bw.append('\n');
					}
					bw.close();
					fw.close();
					
					JOptionPane.showMessageDialog(null, "Data Exported");
					
					
					}catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
				
			}
		});
	
		btnExportData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExportData.setBounds(218, 53, 128, 28);
		frmStudentDatabase.getContentPane().add(btnExportData);
		
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Deletes Row from the table");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if( i >= 0)
				{
					model.removeRow(i);
				}
			}
		});
		btnDelete.setBounds(549, 53, 105, 28);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmStudentDatabase.getContentPane().add(btnDelete);
		
		
		textField = new JTextField();
		textField.setToolTipText("Searchs from the table");
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				String text = textField.getText();

//				if (spinner.getValue() == "First Name")
//				{
//					filter(text,row[0]);
//				}
//				else if (spinner.getValue() == "Last Name")
//				{
//					filter(text,row[1]);
//				}
//				else if (spinner.getValue() == "CUNY ID")
//				{
//					filter(text,row[2]);
//				}
//				else if (spinner.getValue() == "GPA")
//				{
//					filter(text,row[3]);
//				}
//				else if (spinner.getValue() == "Venus Login")
//				{
//					filter(text,row[4]);;
//				}
				
				filter(text);
//				DefaultTableModel searchTable = (DefaultTableModel)table.getModel();
//				String search = textField.getText().toLowerCase();
//				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(searchTable);
//				table.setRowSorter(tr);
//				tr.setRowFilter(RowFilter.regexFilter(search));
				
			}
		});
		textField.setBounds(196, 15, 343, 28);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmStudentDatabase.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnClearData = new JButton("Clear Data");
		btnClearData.setToolTipText("Clears Data from the Table");
		btnClearData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel clearDataModel = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
			}
		});
		btnClearData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClearData.setBounds(384, 53, 128, 28);
		frmStudentDatabase.getContentPane().add(btnClearData);
		
	}
}
