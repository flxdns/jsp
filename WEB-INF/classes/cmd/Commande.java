package cmd;

import javax.servlet.http.HttpServletRequest;

public interface Commande {
    String execute(HttpServletRequest req) throws Exception;
}     