
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.lang.reflect.Constructor;
import bdd.*;
import beans.*;
import cmd.*;

public class Controleur extends HttpServlet {
    private Map<String,Commande> map; //hashmap permettant d'associer Ã  chaque nom de commande la classe de commande associÃ©e
    private Map<String,String> mapDroits; //hashmap permettant d'associer Ã  chaque nom de commande son droit d'accÃ¨s (all ou admin)

    private static final String JSP_ERREUR_INIT = "/erreurs/erreurInitControleur.jsp";
    private static final String JSP_ERREUR_CMD = "/erreurs/erreurCommande.jsp";
    private static final String JSP_EXCEPTION_CMD = "/erreurs/exceptionCommande.jsp";
    public static final String JSP_ERREUR_DROIT = "/erreurs/erreurDroit.jsp";

    private static final String SERVLET_CONTROLE_SESSION = "/controleSession";

	public static final String JSP_LOGIN = "/login.jsp";

    //Nom de la commande de login (IMPOSE DANS CETTE VERSION)
    public static final String CMD_LOGIN = "login";

    //Liste des valeurs possibles des droits associÃ©s aux pages
    public static final String ALL = "all";
    public static final String ADMIN = "admin";

	/*
	 * code de l'erreur Ã©ventuellement gÃ©nÃ©rÃ©e pendant
           l'initialisation de la hashmap (mÃ©thode init)
	   0 : pas d'erreur
	   -1 : classe de commande inexistante
	   -2 : constructeur de la classe de commande inexistant
	   -3 : erreur de crÃ©ation d'une instance de la classe de commande
	   -4 : un paramÃ¨tre de l'application est mal initialisÃ© (valeur mal structurÃ©e)
	   -7 : on essaie d'insÃ©rer deux fois le mÃªme nom de commande dans la HashMap
	   -8 : utilisation d'un droit d'accÃ¨s inexistant pour une commande
	 */
	private int erreur;
	private String messageErreur; 

	public void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException { 
		
		//on vÃ©rifie si la mÃ©thode init a dÃ©clenchÃ© une erreur
		if (erreur !=0) {
		   	req.setAttribute("code", erreur);
			req.setAttribute("mess", messageErreur);
			RequestDispatcher rd1 = req.getRequestDispatcher(JSP_ERREUR_INIT); 
			rd1.forward(req, res);
			return;
		}

		//on indique que l'on est bien passÃ© par le controleur
		req.setAttribute("controleurOK",true);

		String next=null;
		//on rÃ©cupÃ¨re le nom de la commande Ã  activer dans la requÃªte reÃ§ue                               
        	String cmd = req.getParameter("cmd");      
        	if (cmd==null) { cmd="accueil"; }
		else {
			if (!map.containsKey(cmd)) {
				RequestDispatcher rd1 = req.getRequestDispatcher(JSP_ERREUR_CMD); 
				rd1.forward(req, res); 
				return;
			}
		}

		//on vÃ©rifie que la commande peut bien Ãªtre exÃ©cutÃ©e car il y a un utilisateur connectÃ©
		//on vÃ©rifie que la commande demandÃ©e a bien un droit compatible avec l'utilisateur connectÃ©
		//on ne le fait que si la commande demandÃ©e n'est pas la commande de login
		//Si l'un des deux points prÃ©cÃ©dents pose problÃ¨me, la servlet incluse 
		//va renvoyer vers la page de login avec un forward et donc la suite du controleur 
		//ne sera pas exÃ©cutÃ©e.
		//NB : il faut transmettre Ã  la servlet de controle de session le droit associÃ© Ã  la commande
		if (! cmd.equals(CMD_LOGIN)) {
			String droitCmd = mapDroits.get(cmd); //on rÃ©cupÃ¨re le droit associÃ© Ã  la commande
			req.setAttribute("droitCmd",droitCmd); //on le transmet dans la requÃªte
			//on dÃ©lÃ¨gue le controle Ã  la servlet de controle de session 
			RequestDispatcher rdSession = req.getRequestDispatcher(SERVLET_CONTROLE_SESSION);
			rdSession.include(req,res);
		} 

		//on teste si la servlet de controle de session a fait un forward
		Boolean forwardOK = (Boolean)req.getAttribute("forwardOK");
		if (forwardOK!=null) { return; }

		//NB : si on exÃ©cute cette partie, c'est que le controle de session a Ã©tÃ© validÃ©
		//on rÃ©cupÃ¨re l'objet de type Commande associÃ© au nom de commande prÃ©cÃ©dent
        	Commande cde = map.get(cmd);                 
		try {
			//on exÃ©cute la mÃ©thode execute de l'objet "commande" sÃ©lectionnÃ©
			//cette mÃ©thode renvoie le nom de la JSP qui devra Ãªtre activÃ©e ensuite
        		next = cde.execute(req);
		} catch(Exception ex){
		  req.setAttribute("exception",ex.getMessage());
		  req.setAttribute("classeException",ex.getClass().getSimpleName());
		  req.setAttribute("classe",cde.getClass().getName());
		  next = JSP_EXCEPTION_CMD;
		}
		//Pour le debuggage Ã©ventuel, on passe dans la requÃªte 
		//le nom de la classe de commande et le nom de la JSP (vue)
		req.setAttribute("classeCmd",cde.getClass().getName());
		req.setAttribute("jsp",next);		

		//on active la vue ie la page JSP cible
		RequestDispatcher rd = req.getRequestDispatcher(next); 
		rd.forward(req, res); 

	} //fin mÃ©thode service

	//Cette mÃ©thode est exÃ©cutÃ©e une seule fois lors de la crÃ©ation
	//de la servlet par tomcat. Ceci arrive lors de la premiÃ¨re invocation
	//de la servlet
	public void init(ServletConfig config) throws ServletException {

		//une hashmap est utilisÃ©e pour mÃ©moriser les triplets :
		//<nom commande, commande Ã  utiliser, page cible>
		//"commande Ã  utiliser" correspond Ã  un objet instance d'une classe
		//de commande dont la mÃ©thode "execute" devra Ãªtre exÃ©cutÃ©e par le controleur
		//Suite Ã  cette exÃ©cution, la JSP appelÃ©e "page cible" devra Ãªtre activÃ©e par tomcat.
		//Pour cela, le nom de cette page va Ãªtre mÃ©morisÃ©e dans l'objet commande activÃ© pour
		//Ãªtre renvoyÃ© par la mÃ©thode execute. 
		map = new HashMap<String,Commande>();
		mapDroits = new HashMap<String,String>();
		erreur = 0;

		//Enumeration<String> listeParametres = config.getServletContext().getInitParameterNames();
		Enumeration listeParametres = config.getServletContext().getInitParameterNames();

	    	while (listeParametres.hasMoreElements()) {
		    String nomParamCommande = ((String)listeParametres.nextElement());
			//chaque couple(classe,jsp) est stockÃ© dans les paramÃ¨tres d'initialisation sous la forme
			//d'un paramÃ¨tre dont le nom est cmd-nom, avec nom : le nom de la commande
			if (!nomParamCommande.startsWith("cmd-")) {
			    continue;
			    // Si ce n'est pas une dÃ©claration de commande, on ignore
			}
		    String ligne = config.getServletContext().getInitParameter(nomParamCommande);

			//la valeur du paramÃ¨tre est une chaine de caractÃ¨res oÃ¹ les Ã©lÃ©ments
			//du couple sont sÃ©parÃ©s par des points-virgule
        		String [] ligneDecomposee = ligne.split(";");
			if (ligneDecomposee.length != 3) {
				erreur = -4;
				messageErreur = "Dans le fichier web.xml, un param&egrave;tre est mal initialis&eacute; (";
				messageErreur += ligne + ")";
				return;
			}
			//on enlÃ¨ve les Ã©ventuels blancs "en trop" pour chacune des infos Ã  rÃ©cupÃ©rer
        		String nomCommande = nomParamCommande.substring(4).trim();
        		String nomClasseCommande = ligneDecomposee[0].trim();
        		String pageSuivante = ligneDecomposee[1].trim();
			String droit = ligneDecomposee[2].trim();

			//On vÃ©rifie que le droit est bien soit "all" soit "admin"
			if (!(droit.equals(ALL) || droit.equals(ADMIN))) {
				erreur = -8;
				messageErreur = "Utilisation dans le fichier web.xml d'un droit inexistant pour la commande  "+ nomCommande;
				return;				
			}

			//on demande Ã  la machine virtuelle Java de charger la classe de l'objet commande
			//qui devra Ãªtre activÃ©e
			Class<?> classeCommande = null;
			Constructor<?> consCommande = null;
			Commande commande = null;

			try {
			    classeCommande = Class.forName(nomClasseCommande);
			}
			catch (ClassNotFoundException ex) {
			      erreur = -1;
			      messageErreur = "Utilisation dans le fichier web.xml d'une classe de Commande inexistante (";
			      messageErreur += nomClasseCommande + ")";
			      return;
			}
			//on rÃ©cupÃ¨re dynamiquement le constructeur de cette classe
			//"String.class" signifie que le constructeur que l'on recherche prend un
			//Ã©lÃ©ment de type String en paramÃ¨tre (le nom de la page JSP cible)
			try {
        			consCommande = classeCommande.getConstructor(String.class);
			}
			catch (NoSuchMethodException ex) {
			      erreur = -2;
			      messageErreur = "Constructeur d'une classe de Commande inexistant";
			      return;
			}
			//on crÃ©e donc une objet instance de la classe de Commande concernÃ©e.
			//on passe en paramÃ¨tre au constructeur le nom de la page JSP cible ie.
			//qui devra Ãªtre activÃ©e par tomcat Ã  la fin de l'exÃ©cution de la 
			//mÃ©thode execute de l'objet commande activÃ©.
			try {
        			commande = ((Commande) consCommande.newInstance(pageSuivante));
			}
			catch (Exception ex) {
			      erreur = -3;
			      messageErreur = "Erreur pendant la cr&eacute;ation d'une instance d'une classe de Commande <br/>";
			      messageErreur += "Il est possible qu'une classe de commande utilisÃ©e dans le fichier web.xml";
			      messageErreur += " n'implante pas l'interface Commande.";
			      return;
			}
			//on ajoute le "triplet" dans le Hashmap
			if (map.containsKey(nomCommande)) {
				erreur = -7;
				messageErreur = "Un nom de commande est utilis&eacute; 2 fois dans le fichier web.xml (";
				messageErreur += nomCommande + ")";
				return;
			}
        		map.put(nomCommande,commande);
			mapDroits.put(nomCommande,droit);
	    } //fin du for                 
	} //fin mÃ©thode init

} //fin de la classe