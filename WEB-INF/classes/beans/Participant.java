//Participant(idp,nom,age)
package beans;

import java.util.*;

public class Participant{
        private int idp;
        private String nom;
        private int age;

        public int getIdp(){return idp;}
        public String getNom(){return nom;}
        public int getAge(){return age;}
        public void setIdp(int idp){this.idp=idp;}
        public void setNom(String nom){this.nom=nom;}
        public void setAge(int age){this.age=age;}
        
    	public Participant(int idp , String nom, int age){
	    setIdp(idp);
	    setNom(nom);
	    setAge(age);
        }

	//constructeur Ã  utiliser quand on ne connait pas idp (nouveau tuple par exemple)
	public Participant(String nom, int age){
	    setIdp(-1);
	    setNom(nom);
	    setAge(age);
        }

        public String toString(){
                return idp+ "," + nom + "," + age;
        }
}
