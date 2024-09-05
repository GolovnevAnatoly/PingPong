/* ****** автор АНАТОЛИЙ ГОЛОВНЕВ ******
Основное окно, которое надо запускать. Содержит элементы контроля и саму игру.
Контроль устанавливает скорость движения мяча и размер доски.
*/


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Run extends JFrame implements ItemListener, ChangeListener{

	private PingPong frame = new PingPong();
	private JPanel control = new JPanel();
	private JRadioButton sizes[] = new JRadioButton [3];
	private String optionSizes[] = {"маленький", "средний", "большой"};
	private ButtonGroup optGroup = new ButtonGroup();

	public Run(){
		JPanel options = new JPanel();
		for(int k = 0; k < sizes.length; k++){
			sizes[k] = new JRadioButton(optionSizes[k]);
			sizes[k].setHorizontalTextPosition(SwingConstants.CENTER);
			sizes[k].setVerticalTextPosition(SwingConstants.TOP);
			sizes[k].addItemListener(this);
			sizes[k].setFocusable(false);
			options.add(sizes[k]);
			optGroup.add(sizes[k]);
		}
		sizes[1].setSelected(true);
		control.add(options);
		
		JSlider speed = new JSlider(JSlider.HORIZONTAL, 1,10,5);
			frame.speed(speed.getValue());
		speed.addChangeListener(this);
		speed.setMajorTickSpacing(1);
		speed.setPaintTicks(true);
		
		Hashtable<Integer,JLabel> labelTable = new Hashtable<Integer,JLabel>();
		labelTable.put(speed.getMinimum(), new JLabel("медленно") );
		labelTable.put(speed.getMaximum(), new JLabel("быстро") );
		speed.setLabelTable(labelTable);
		speed.setPaintLabels(true);
		speed.setFocusable(false);
		control.add(speed);
		
		getContentPane().add(control,BorderLayout.NORTH);
		getContentPane().add(frame, BorderLayout.CENTER);
		setTitle("Игра");
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
	}//constructor

	public void itemStateChanged(ItemEvent e){
		for(int k = 0;k <sizes.length;k++)
			if(sizes[k].isSelected()){frame.changeSize(k-1);}
	}

	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) frame.speed((int)source.getValue());
	}


public static void main(String[] args){
	
	new Run();
	
}
}