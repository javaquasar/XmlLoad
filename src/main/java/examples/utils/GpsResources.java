/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.utils;

import java.io.InputStream;

/**
 *
 * @author artur
 */
public class GpsResources {
    public InputStream getXml() {
        InputStream xstream = this.getClass().getResourceAsStream("/xml/GPS.xml"); 
        return xstream;
    }
}
