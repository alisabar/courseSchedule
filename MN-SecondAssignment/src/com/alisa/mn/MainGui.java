package com.alisa.mn;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.alisa.mn.Guis.AtThePlaceGUI;
import com.alisa.mn.Guis.ClassGUI;
import com.alisa.mn.Guis.CourseGUI;
import com.alisa.mn.Guis.LecturerGUI;

public class MainGui extends JPanel {

	private JPanel _dynamicPanel;
	public MainGui()
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		_dynamicPanel = new JPanel();
		
		_dynamicPanel.add(new JLabel("test"));
		
		add(_dynamicPanel,BorderLayout.PAGE_START);
		add(CreateButtonsShowTablePanel(),BorderLayout.PAGE_START);
		add(CreateButtonsShowAddEntriesPanel(),BorderLayout.PAGE_START);
			
	}
	
	public static void addComponentsToPane (JFrame frame, Container pane)
	{
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
	//	pane.add(CreateButtonsShowTablePanel(),BorderLayout.PAGE_START);
	//	pane.add(CreateButtonsShowAddEntriesPanel(),BorderLayout.PAGE_START);
	///////////////////////////////////////////////////////////////////////////////////
	
	JButton addACourse= new JButton("Add a Course");
	addACourse.setPreferredSize(new Dimension(150, 50));
	//panelAddButtons.add(addACourse);
	addACourse.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			JPanel panelAddText=new JPanel();
			JDialog DialogAddText= new JDialog(frame,"Add a new course.");
			
			//panelAddButtons.add(panelAddText);
			BoxLayout boxText=new BoxLayout(panelAddText, BoxLayout.Y_AXIS);
			JTextField text1=new JTextField("Please enter course ID:");
			//text1.setBounds(10,10,50,20);
			panelAddText.add(text1);
			JTextField text2 =new JTextField("Please enter course name:");
			//text2.setBounds(30,30,50,20);
			panelAddText.add(text2);
			
		//	pane.add(panelAddText,BorderLayout.CENTER);
			JButton submitbut=new JButton("submit");
			submitbut.setBounds(60,10,20,10);
			submitbut.setSize(100, 100);
			panelAddText.add(submitbut);
		    pane.add(panelAddText,BorderLayout.CENTER);
			submitbut.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String[] input={"Course",text1.getText(),text2.getText()};
					AddRow.main(input);
				}
			});
			panelAddText.setSize(600, 200);
			panelAddText.setVisible(true);
			DialogAddText.add(panelAddText);
			
		}
	}) ;
	
	}

	private Component CreateButtonsShowAddEntriesPanel() {
		JPanel container=new JPanel();
		return container;
	}

	protected  JPanel CreateButtonsShowTablePanel() {
		JPanel panelbuttons=new JPanel();
		
		JButton buttonCourses = new JButton("Courses");
		buttonCourses.setPreferredSize(new Dimension(100, 50));
		buttonCourses.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CourseGUI.main(null);
			}
		}) ;
		panelbuttons.add(buttonCourses);
		
		JButton buttonClasses= new JButton("Classes");
		buttonClasses.setPreferredSize(new Dimension(100, 50));
		//pane.add(buttonClasses,BorderLayout.CENTER);
		buttonClasses.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClassGUI.main(null);
			}
		}) ;
		panelbuttons.add(buttonClasses);

		JButton buttonLecturers = new JButton("Lecturers");
		buttonLecturers.setPreferredSize(new Dimension(100, 50));
		//pane.add(buttonLecturers,BorderLayout.LINE_START);
		buttonLecturers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LecturerGUI.main(null);
			}
		}) ;
		panelbuttons.add(buttonLecturers);
		
		JButton buttonSchedule= new JButton("Schedule");
		buttonSchedule.setPreferredSize(new Dimension(100, 50));
		//pane.add(buttonSchedule,BorderLayout.PAGE_END);
		buttonSchedule.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AtThePlaceGUI.main(null);
			}
		}) ;
		panelbuttons.add(buttonSchedule);
		
		JButton buttonLecturersAndCourses= new JButton("LecturersAndCourses");
		buttonLecturersAndCourses.setPreferredSize(new Dimension(200, 50));
//	pane.add(buttonLecturersAndCourses,BorderLayout.LINE_END);
		
		buttonLecturersAndCourses.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LecturerGUI.main(null);
			}
		}) ;
		panelbuttons.add(buttonLecturersAndCourses);
		
		return panelbuttons;
	}
	
	private static void createAndShowGUI()
	{
		JFrame frame = new JFrame("Data base project.");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MainGui());
		frame.pack();
		frame.setSize(900, 900);
		frame.setVisible(true);
	}
	public static void main(String[] args)
	{
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
		        public void run() {
		        	SecondProg.main(null);
		            createAndShowGUI();
		        }
		    });
		}
	}

