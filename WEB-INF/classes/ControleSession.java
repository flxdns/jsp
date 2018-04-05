import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ControleSession extends HttpServlet
{
	
	//cette mÃ©thode doit rÃ©aliser un "forward" vers la JSP 
	//dont le nom est passÃ© en dernier paramÃ¨tre
	private void forwardJSP( HttpServletRequest req , HttpServletResponse res , String pageJSP ) throws ServletException, IOException
	{
		//on indique d'abord au controleur qu'il y a eu forward
		req.setAttribute( "forwardOK" , true );
		//On fait le forward : A COMPLETER
		RequestDispatcher localRequestDispatcher = req.getRequestDispatcher( pageJSP );
		localRequestDispatcher.forward( req , res );
	}
	
	//cette mÃ©thode permet de rÃ©cupÃ©rer le droit 
	//de la personne connectÃ©e dans la session
	//NB : il peut Ãªtre "null" si personne n'est connectÃ©
	private Integer getDroitSession( HttpServletRequest req )
	{
		HttpSession localHttpSession = req.getSession( true );
		Integer     droitUtil        = ( Integer ) localHttpSession.getAttribute( "droit" );
		//A COMPLETER
		return droitUtil;
	}
	
	//cette mÃ©thode renvoie vrai si le droit d'accÃ¨s de la commande devant
	//Ãªtre prise en charge par le controleur (attribut droitCmd dans la requÃªte)
	//correspond au droit ADMIN (voir constante dans Controleur.java)
	private boolean evaluerDroitCommande( HttpServletRequest req )
	{
		boolean droitCommande = false;
		
		String droitCommandeStr = ( String ) req.getAttribute( "droitCmd" );
		if( droitCommandeStr.equals( "admin" ) ) droitCommande = true;
		return droitCommande;
	}
	
	//Le contenu de la mÃ©thode service ne doit pas Ãªtre modifiÃ©.
	//Lorsque les 3 mÃ©thodes prÃ©cÃ©dentes seront complÃ©tÃ©es, il faudra
	//juste enlever les commentaires /* ... */ de la mÃ©thode service.
	public void service( HttpServletRequest req , HttpServletResponse res ) throws ServletException, IOException
	{
		
		Integer droitUtil = getDroitSession( req );
		
		if( droitUtil == null ) {
			//personne n'est connectÃ©
			//on renvoie vers la page de login			
			forwardJSP( req , res , "/login.jsp" );
		}
		else {
			//il y a quelqu'un de connectÃ©.
			//il vaut vÃ©rifier que son droit d'accÃ¨s 
			//est compatible avec celui de la commande demandÃ©e
			boolean droitAdmin = evaluerDroitCommande( req );
			if( droitAdmin ) {
				//C'est une commande Ã  usage restreint (admin)
				if( droitUtil != 2 ) {
					//l'utilisateur n'a pas le droit admin
					//on renvoie vers la page d'erreur droit
					forwardJSP( req , res , Controleur.JSP_ERREUR_DROIT );
				}
			} //fin if droitCmd = ADMIN
		} //fin else droitUtil != null
	} //fin mÃ©thode service
} //fin de la classe
