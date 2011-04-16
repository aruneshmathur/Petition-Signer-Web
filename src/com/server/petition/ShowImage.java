package com.server.petition;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class ShowImage extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sid = (String) request.getParameter("sid");

		response.setContentType("image/png");

		Key k1 = KeyFactory.createKey(Signee.class.getSimpleName(), sid);

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Signee signee = (Signee) pm.getObjectById(Signee.class, k1);

		if (signee != null) {

			ByteArrayWrapperSerializable wrapper = signee.getSigneeSignature();
			byte[] signature = wrapper.getArray();

			if (signature != null) {

				BufferedOutputStream output = null;

				try {
					ByteArrayInputStream input = new ByteArrayInputStream(
							signature);
					output = new BufferedOutputStream(
							response.getOutputStream());
					byte[] buffer = new byte[4096];
					for (int length = 0; (length = input.read(buffer)) > 0;) {
						output.write(buffer, 0, length);
					}
				} finally {
					if (output != null)
						output.close();
				}
			}
		}

		pm.close();

	}

}
