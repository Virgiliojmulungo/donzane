/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;

/**
 *
 * @author Virgilio Mulungo
 */
public class ErrorInserEstudanteDatabase extends Exception {

    public ErrorInserEstudanteDatabase(String message, SQLException ex) {
        super(message,ex);
    }
    
}
