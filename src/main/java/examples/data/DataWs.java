/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.data;

import examples.processing.JAXBProcessor;
import examples.schema.WSType;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;

/**
 *
 * @author artur
 */
public class DataWs extends JAXBProcessor<WSType> {
    
    private static final String TAG_NAME = "data/WS";

    public DataWs() throws JAXBException, SAXException {
        super(WSType.class, TAG_NAME);
    }

    public DataWs(String schemaFileName) throws JAXBException, SAXException {
        super(WSType.class, TAG_NAME, schemaFileName);
    }

    @Override
    public void doWork(WSType element) {
        System.out.println("Three P1 = " + element.getDtypeThree().getP1());
    }
}
