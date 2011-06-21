package com.proserus.stocks.view.general;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import com.proserus.stocks.dao.PersistenceManager;
import com.proserus.stocks.model.common.ObservableModel;

public class Window extends JFrame implements WindowListener, Observer,PropertyChangeListener {

	private static final String IMAGES_BOOK_GIF = "images/book.gif";

	private static final String COMBO_BOX_SELECTION_BACKGROUND = "ComboBox.selectionBackground";

	private static final String CONTROL_HIGHLIGHT = "controlHighlight";

	private static final String PROSERUS_STOCKS_PORTFOLIO_0_1_BETA = "Proserus Stocks Portfolio " + Version.VERSION;

	static private Window window = new Window();
	
	JSplitPane split;

//TODO Add icons
	//http://www.iconki.com/pack.asp?ico=136&Customicondesign-office-iconset
	public void start() {
		new File(ObservableModel.config).mkdir();
		UIManager.put(CONTROL_HIGHLIGHT, new Color(255, 148, 0));
		UIManager.put(COMBO_BOX_SELECTION_BACKGROUND, new Color(255, 148, 0));
		setLayout(new BorderLayout());
		setJMenuBar(Menu.getInstance());

//		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,new FilterPanel(),);
//		split.setName("Filter");
//		split.setResizeWeight(0);
//		split.setOneTouchExpandable(true);
//		//split.setContinuousLayout(true);
//		split.setEnabled( false );
//		split.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, this);
//		UIManager.put("SplitPane.dividerSize", new Integer(70));
//		SwingUtilities.updateComponentTreeUI(window);
		
		add(FilterPanelImpl.getInstance(), BorderLayout.NORTH);
		add(new SummaryStatusBar(), BorderLayout.SOUTH);
		FilterPanelImpl.getInstance().setVisible(false);
		add(new Tab(), BorderLayout.CENTER);
		setSize(1024, 768);
		setTitle(PROSERUS_STOCKS_PORTFOLIO_0_1_BETA);
		
		setVisible(true);
		addWindowListener(this);
		setIconImage(new ImageIcon(getClass().getClassLoader().getResource(IMAGES_BOOK_GIF)).getImage());
		setVisible(true);
//		split.setDividerLocation(1);
	}

	private Window() {
	}

	static public Window getInstance() {
		return window;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		PersistenceManager.close();
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}

	@Override
	public void update(Observable arg0, Object UNUSED) {
	}

	@Override
    public void propertyChange(PropertyChangeEvent evt) {
		Integer newValue = (Integer)evt.getNewValue();
		Integer oldValue = (Integer)evt.getOldValue();
	    if(newValue>oldValue && oldValue>1){
	    	JSplitPane split = (JSplitPane)evt.getSource();
	    	split.setDividerLocation(oldValue);
	    }
    }
}