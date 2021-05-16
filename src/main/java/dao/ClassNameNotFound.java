/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Virgilio Mulungo
 */
public class ClassNameNotFound extends Exception {

    public ClassNameNotFound(Exception e, String message) {
          super(message,e);
    }
    
}
