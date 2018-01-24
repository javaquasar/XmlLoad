/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.data;

import examples.processing.JAXBProcessor;
import examples.schema.DtypeOne;
import examples.schema.Transactions;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;

/**
 *
 * @author artur
 */
public class DataTransactionsCardAuthorisation extends JAXBProcessor<Transactions.CardAuthorisation> {
    
    private static final String TAG_NAME = "Transactions/CardAuthorisation";

    public DataTransactionsCardAuthorisation() throws JAXBException, SAXException {
        super(Transactions.CardAuthorisation.class, TAG_NAME);
    }

    public DataTransactionsCardAuthorisation(String schemaFileName) throws JAXBException, SAXException {
        super(Transactions.CardAuthorisation.class, TAG_NAME, schemaFileName);
    }

    @Override
    public void doWork(Transactions.CardAuthorisation element) {
        System.out.println(element.getAccount().getNo());
    }
}
