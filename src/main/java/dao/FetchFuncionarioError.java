/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;

public class FetchFuncionarioError extends Exception {

    public FetchFuncionarioError(String message, SQLException ex) {
        super(message,ex);
    }
    
}
