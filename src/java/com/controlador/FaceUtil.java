/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controlador;

import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static javax.faces.application.FacesMessage.SEVERITY_WARN;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;


/**
 *
 * @author User
 */
public class FaceUtil {
        /**
     * Muestra un mensaje de error en la pagina 
     * @param message: representa el mensaje a mostrar
     * @param detail: representa los posibles detalles del mensaje (opcional)
     */
    public static void addErrorMessage(String message, String details) {        
        addMessage(SEVERITY_ERROR, message, details);
    }
    
    public static boolean retardo(){
        try
              {
              Thread.sleep(2000);  

              }catch (InterruptedException ie)
              {
              System.out.println(ie.getMessage());
              }
        return false;
    }
    
    /**
     * Muestra un mensaje de error en la pagina, pero sin detalles
     * @param message 
     */
    public static void addErrorMessage(String message) {
        addMessage(SEVERITY_ERROR, message, null);
    }
    
    
    /**
     * Muestra un mensaje de informacion en la pagina 
     * @param message: representa el mensaje a mostrar
     * @param detail: representa los posibles detalles del mensaje (opcional)
     */
    public static void addInfoMessage(String message, String details) {        
        addMessage(SEVERITY_INFO, message, details);
    }
    
    /**
     * Muestra un mensaje de nformacion en la pagina, pero sin detalles
     * @param message 
     */
    public static void addInfoMessage(String message) {
        addMessage(SEVERITY_INFO, message, null);
    }
    
    
    /**
     * Muestra un mensaje de advertencia en la pagina 
     * @param message: representa el mensaje a mostrar
     * @param detail: representa los posibles detalles del mensaje (opcional)
     */
    public static void addWarnMessage(String message, String details) {        
        addMessage(SEVERITY_WARN, message, details);
    }
    
    /**
     * Muestra un mensaje de advertencia en la pagina, pero sin detalles
     * @param message 
     */
    public static void addWarnMessage(String message) {
        addMessage(SEVERITY_WARN, message, null);
    }
    
    
    private static void addMessage(Severity severity, String message, String details) {
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(severity, message, details));
    }

    
}
