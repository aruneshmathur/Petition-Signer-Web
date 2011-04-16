package com.server.petition;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import redstone.xmlrpc.XmlRpcServlet;

@SuppressWarnings("serial")
public class Petition_ServerServlet extends XmlRpcServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.getXmlRpcServer().addInvocationHandler("PetitionServer",
				new RequestsHandler());

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		getXmlRpcServer().execute(req.getInputStream(), res.getWriter());
	}

}
