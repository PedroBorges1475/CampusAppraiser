package controller;

import view.AdminView;
import view.AvaliadorView;
import view.GerenteView;
import view.LoginView;

public class Main {
	
	private static LoginView loginView = new LoginView();
	private static AdminView adminView = new AdminView();
	private static AvaliadorView avaliadorView = new AvaliadorView();
	private static GerenteView gerenteView = new GerenteView();
	
	public static void main(String[] args) {
		loginView.setVisible(true);
	}
	
	public static void callAdminFrame() {
		adminView.setVisible(true);
	}
	
	public static void callAvaliadorFrame() {
		avaliadorView.setVisible(true);
	}
	
	public static void callGerenteFrame() {
		gerenteView.setVisible(true);
	}
	
}
