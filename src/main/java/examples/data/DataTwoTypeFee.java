/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.data;

import examples.processing.JAXBProcessor;
import examples.schema.DtypeTwo;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;

/**
 *
 * @author artur
 */
public class DataTwoTypeFee extends JAXBProcessor<DtypeTwo.DtypeTwoTypeFee> {
    private static final String TAG_NAME = "data/dtype_two/dtype_twoType_Fee";

    public DataTwoTypeFee() throws JAXBException, SAXException {
        super(DtypeTwo.DtypeTwoTypeFee.class, TAG_NAME);
    }

    public DataTwoTypeFee(String schemaFileName) throws JAXBException, SAXException {
        super(DtypeTwo.DtypeTwoTypeFee.class, TAG_NAME, schemaFileName);
    }

    @Override
    public void doWork(DtypeTwo.DtypeTwoTypeFee element) {
        System.out.println(element.getValue());
    }
}
