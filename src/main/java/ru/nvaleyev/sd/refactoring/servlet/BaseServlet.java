package ru.nvaleyev.sd.refactoring.servlet;

import ru.nvaleyev.sd.refactoring.database.ProductDatabase;

import javax.servlet.http.HttpServlet;

public abstract class BaseServlet extends HttpServlet {
    final ProductDatabase database;

    public BaseServlet(ProductDatabase database) {
        this.database = database;
    }
}
