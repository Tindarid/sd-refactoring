package ru.nvaleyev.sd.refactoring.servlet;

import ru.nvaleyev.sd.refactoring.database.ProductDatabase;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {
    final ProductDatabase database;

    public BaseServlet(ProductDatabase database) {
        this.database = database;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGetInternal(request, response);
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected abstract void doGetInternal(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
