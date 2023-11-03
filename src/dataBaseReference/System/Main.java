package dataBaseReference.System;
import dataBaseReference.CRUD.CRUD_Information;
import dataBaseReference.Menu.Menu;

public class Main {
    public static void main(String[] args) {
    	CRUD_Information.helpInformation();
    	CRUD_Information.aboutProgram();
    	Menu menu = new Menu();
    	menu.displayChoiceMenu();
        menu.close();
    }
}
