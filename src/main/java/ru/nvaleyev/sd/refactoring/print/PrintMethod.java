package ru.nvaleyev.sd.refactoring.print;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PrintMethod {
    void print(ResultSet rs) throws IOException, SQLException;
}
