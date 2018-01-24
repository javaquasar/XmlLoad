package namespaces;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
 
public class Demo {
 
    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Customer.class);
 
        Customer customer = new Customer();
        customer.setId(123);
        customer.setName("Jane Doe");
 
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(customer, System.out);
    }
}
