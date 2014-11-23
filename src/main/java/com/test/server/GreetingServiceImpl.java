package com.test.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.test.client.GreetingService;
import com.test.shared.FieldVerifier;
import com.test.shared.StringContainer;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
    GreetingService {

	@SuppressWarnings("unchecked")
	public <T extends IsSerializable> List<T> greetServer(List<T> input) throws IllegalArgumentException {
    // Verify that the input is valid.
	  
	  String name = input.get(0).toString();
    if (!FieldVerifier.isValidName(name)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException(
          "Name must be at least 4 characters long");
    }

    String serverInfo = getServletContext().getServerInfo();
    String userAgent = getThreadLocalRequest().getHeader("User-Agent");

    // Escape data from the client to avoid cross-site script vulnerabilities.
    name = escapeHtml(name);
    userAgent = escapeHtml(userAgent);

    List<T> returnList = new ArrayList<T>();
    
    returnList.add((T)new StringContainer("Hello, " + input + "!<br><br>I am running " + serverInfo
        + ".<br><br>It looks like you are using:<br>" + userAgent));
    return returnList;
  }

  /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   *
   * @param html the html string to escape
   * @return the escaped string
   */
  private String escapeHtml(String html) {
    if (html == null) {
      return null;
    }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
        ">", "&gt;");
  }
}
