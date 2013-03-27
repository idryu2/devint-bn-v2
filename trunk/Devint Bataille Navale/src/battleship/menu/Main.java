/*
* Copyright 2007-2011, H�l�ne Collavizza, Jean-Paul Stromboni
* 
* This file is part of project 'Modele_de_Jeu'
* 
* 'Modele_de_Jeu' is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* 
* 'Modele_de_Jeu'is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Lesser General Public License for more details.
* 
* You should have received a copy of the GNU Lesser General Public License
* along with 'Modele_de_Jeu'. If not, see <http://www.gnu.org/licenses/>.
*/
package battleship.menu;

import java.io.File;

//import t2s.SIVOXDevint;

/** classe pour lancer le jeu
 * Elle cr   simplement une instance de MenuJeu
 * 
 * @author helene
 *
 */
public class Main{

    public static void main(String args[]){
        Fenetre fe = new Fenetre("Bataille Navale");
        
        
        /*
         * Demande � l'utilisateur son nom
         * Charge les niveaux finis ainsi que l'avanc�e de l'utilisateur dans le jeu
         */
        String[] nomOptions = {"Lancer la partie", "Quitter"}; 
        Menu m = new Menu("Bataille Navale", nomOptions);
        fe.add(m);
        fe.setExtendedState(fe.MAXIMIZED_BOTH);
        m.requestFocus();
        //fe.getContentPane().add(pan);

    }
}