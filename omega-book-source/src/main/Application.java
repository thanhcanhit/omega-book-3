package main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

import bus.impl.ShiftsManagement_BUSImpl;
import entity.Account;
import entity.Employee;
import entity.Shift;
import gui.Login_GUI;
import gui.MainView;
import gui.Welcome_GUI;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class Application extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2088519052512921965L;
	public static Application app;
	private final MainView mainForm;
	public static Employee employee = null;
	private final Login_GUI loginForm;
	private static ShiftsManagement_BUSImpl shift_BUS = new ShiftsManagement_BUSImpl();
	private static Shift shift;

	public Application() {
		initComponents();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setSize(new Dimension(1366, 768));
		setTitle("Omega Book");
		setIconImage(new FlatSVGIcon(new File("resources/imgs/icon.svg")).getImage());
		mainForm = new MainView();
		loginForm = new Login_GUI();
		setContentPane(loginForm);
		Notifications.getInstance().setJFrame(this);

		// Handle on close
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(null, "Bạn có thật sự muốn tắt OmegaBook?", "Đóng ứng dụng?",
						JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if (employee != null) {
						try {
							// Đóng kết nối
							shift.setEndedAt(new Date());
						} catch (Exception ex) {
							Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
						}
						try {
							shift_BUS.createShifts(shift);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.exit(0);
				}
			}
		});
	}

	public static void showForm(Component component) {
		component.applyComponentOrientation(app.getComponentOrientation());
		app.mainForm.showForm(component);
	}

	public static void refreshMainView() throws RemoteException {
		app.mainForm.refreshSalesForm();
	}

	public static void setSelectedMenu(int index, int subIndex) {
		app.mainForm.setSelectedMenu(index, subIndex);
	}

	public static void login(Employee employee) throws Exception {
//        Update UI
		FlatAnimatedLafChange.showSnapshot();
		app.setContentPane(app.mainForm);
		app.mainForm.applyComponentOrientation(app.getComponentOrientation());
		app.mainForm.hideMenu();
		setSelectedMenu(0, 0);
		SwingUtilities.updateComponentTreeUI(app.mainForm);
		FlatAnimatedLafChange.hideSnapshotWithAnimation();
		shift = new Shift(shift_BUS.renderID(), new Date(), new Account(employee));
//        Update state
		Application.employee = employee;
		MainView.rerenderMenuByEmployee();
		Notifications.getInstance().show(Notifications.Type.SUCCESS, "Đăng nhập vào hệ thống thành công");
	}

	public static void logout() throws Exception {
//        Update UI
		FlatAnimatedLafChange.showSnapshot();
		app.setContentPane(app.loginForm);
		app.loginForm.applyComponentOrientation(app.getComponentOrientation());
		SwingUtilities.updateComponentTreeUI(app.loginForm);
		FlatAnimatedLafChange.hideSnapshotWithAnimation();

//        Update state
		Application.employee = null;
		shift.setEndedAt(new Date());
		shift_BUS.createShifts(shift);
		Notifications.getInstance().show(Notifications.Type.INFO, "Đăng xuất khỏi hệ thống thành công");
	}

	public static void close() {
		System.exit(0);
	}

	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Omega Book");
		setMinimumSize(new java.awt.Dimension(550, 768));
		setPreferredSize(new java.awt.Dimension(1366, 768));
		addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				formKeyTyped(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 719, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 521, Short.MAX_VALUE));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void formKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_formKeyTyped

	}// GEN-LAST:event_formKeyTyped

	public static void main(String args[]) {
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("theme");
		UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 15));
		FlatMacLightLaf.setup();

//        Contact native 
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

//        Fake loading
		new Welcome_GUI().setVisible(true);
//        Connect db
//        try {
//            ConnectDB.connect();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Không thể kết nối đến database!", "Không thể khởi động ứng dụng", JOptionPane.DEFAULT_OPTION);
//            System.exit(0);
//        }

		app = new Application();
//        Delay render
		Timer timer = new Timer(2500, (ActionEvent evt) -> {
			java.awt.EventQueue.invokeLater(() -> {
				app.setVisible(true);
			});
		});
		timer.setRepeats(false);
		timer.start();

	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables
}