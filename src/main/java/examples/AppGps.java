package examples;


import examples.bench.XMLBenchmarkInputStream;
import examples.data.DataThreeXSLT;
import examples.data.DataTransactionsCardAuthorisation;
import examples.processing.TagEngine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class AppGps {

    private static final String XML_SCHEMA_FILE_NAME = "GPS.xsd";

    private static void test_noverify() throws Throwable {
        TagEngine tagEngine = new TagEngine();

        DataTransactionsCardAuthorisation dta = new DataTransactionsCardAuthorisation();
        File initialFile = new File("./src/main/resources/xml/GPS_BIG_2.XML");
        System.out.println(initialFile.exists());
        InputStream xstream = new FileInputStream(initialFile);

        tagEngine.add(dta);

        long millis = System.currentTimeMillis();
        tagEngine.process(xstream);
        long endMillis = System.currentTimeMillis();
        System.out.println("Runtime: " + (endMillis - millis) + "ms, " +
               initialFile.length() + " bytes processed");
    }

    private static void test_noverify(int repeatCount) throws Throwable {
        TagEngine tagEngine = new TagEngine();

        DataTransactionsCardAuthorisation dta = new DataTransactionsCardAuthorisation();
        XMLBenchmarkInputStream xstream = 
                new XMLBenchmarkInputStream(
                        repeatCount, 
                        XML_TEST_HEADER, 
                        XML_TEST_BODY, 
                        XML_TEST_FOOTER)
                ;

        tagEngine.add(dta);

        long millis = System.currentTimeMillis();
        tagEngine.process(xstream);
        long endMillis = System.currentTimeMillis();
        System.out.println("Runtime: " + (endMillis - millis) + "ms, " +
                xstream.getByteCount() + " bytes processed");
    }

    private static void test_verify(int repeatCount) throws Throwable {
        TagEngine tagEngine = new TagEngine();
        
        DataTransactionsCardAuthorisation dta = new DataTransactionsCardAuthorisation(XML_SCHEMA_FILE_NAME);
        XMLBenchmarkInputStream xstream =
                new XMLBenchmarkInputStream(repeatCount, XML_TEST_HEADER, XML_TEST_BODY,
                        XML_TEST_FOOTER);

        tagEngine.add(dta);
  
        long millis = System.currentTimeMillis();
        tagEngine.process(xstream);
        long endMillis = System.currentTimeMillis();
        System.out.println("Runtime: " + (endMillis - millis) + "ms, " +
                xstream.getByteCount() + " bytes processed");
    }

    private static void test_XSLT(int repeatCount) throws Throwable {
        TagEngine tagEngine = new TagEngine();
        DataThreeXSLT dataThree = new DataThreeXSLT();
        XMLBenchmarkInputStream xstream =
                new XMLBenchmarkInputStream(repeatCount, XML_TEST_HEADER, XML_TEST_BODY,
                        XML_TEST_FOOTER);

        tagEngine.add(dataThree);

        long millis = System.currentTimeMillis();
        tagEngine.process(xstream);
        long endMillis = System.currentTimeMillis();
        System.out.println("Runtime: " + (endMillis - millis) + "ms, " +
                xstream.getByteCount() + " bytes processed");
    }



    public static void main(String[] args) throws Throwable {
        //generateHugeFile();
        Runtime runtime = Runtime.getRuntime();
        System.out.println("JAXB unmarshall without schema validation");
        test_noverify();
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024 + "MB");
        System.gc();
        System.out.println("JAXB unmarshall with schema validation");
        test_verify(1);
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024 + "MB");

        System.gc();
        System.out.println("XSLT processing");
        //test_XSLT(500000);
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024 + "MB");

    }
    
    private static void generateHugeFile() throws IOException {
        
        FileReader fr = new FileReader("./src/main/resources/xml/GPS_BIG_PART.XML");
        BufferedReader bfr = new BufferedReader(fr);
        FileWriter fw = new FileWriter("./src/main/resources/xml/GPS_BIG_2.XML");
        //BufferedFileWriter bfw = new BufferedFileWriter(fw);
        fw.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        fw.write("\n");
        fw.write(XML_TEST_HEADER);
        fw.write("\n");
        StringBuilder sb = new StringBuilder();
        String sCurrentLine;
        while ((sCurrentLine = bfr.readLine()) != null) {
            System.out.println(sCurrentLine);
            sb.append(sCurrentLine);
            sb.append("\n");
        }
            
        for(int i = 0; i < 1000; i++) {
            fw.write(sb.toString());
        }
        fw.write(XML_TEST_FOOTER);
        fr.close();
        fw.close();
    }
    
    public static final String XML_TEST_HEADER = "<Transactions xmlns:xsi=\"http://www.w3.org/2001/XMLSchema\">";
    public static final String XML_TEST_FOOTER = "</Transactions>";
    public static final String XML_TEST_BODY = 
        "<CardAuthorisation xmlns:xsi=\"http://www.w3.org/2001/XMLSchema\">\n" +
        "    <RecType>ADV</RecType>\n" +
        "    <AuthId>2439748700</AuthId>\n" +
        "    <AuthTxnID>222203964</AuthTxnID>\n" +
        "    <LocalDate>20171222000204</LocalDate>\n" +
        "    <SettlementDate>20171222</SettlementDate>\n" +
        "    <Card PAN=\"7506684975044129\" product=\"MCRD\" programid=\"DIP\" branchcode=\"\" productid=\"2018\" />\n" +
        "    <Account no=\"668497504\" type=\"01\" />\n" +
        "    <TxnCode direction=\"debit\" Type=\"pos\" Group=\"pos\" ProcCode=\"00\" Partial=\"NA\" FeeWaivedOff=\"0\" />\n" +
        "    <TxnAmt value=\"2.7000\" currency=\"985\" />\n" +
        "    <CashbackAmt value=\"0.00\" currency=\"985\" />\n" +
        "    <BillAmt value=\"2.70\" currency=\"985\" rate=\"1.000000\" clientfxrate=\"0.00000000\" />\n" +
        "    <ApprCode>114795</ApprCode>\n" +
        "    <Trace auditno=\"040304\" origauditno=\"040304\" Retrefno=\"020020491033\" />\n" +
        "    <MerchCode>2101357128     </MerchCode>\n" +
        "    <Term code=\"24227088\" location=\"AUTOMATY UVP           WARSZAWA      POL\" street=\"\" city=\"\" country=\"PL\" inputcapability=\"3\" authcapability=\"7\" />\n" +
        "    <Schema>MCRD</Schema>\n" +
        "    <Txn cardholderpresent=\"0\" cardpresent=\"yes\" cardinputmethod=\"7\" cardauthmethod=\"1\" cardauthentity=\"1\" />\n" +
        "    <MsgSource value=\"67\" domesticMaestro=\"no\" />\n" +
        "    <PaddingAmt value=\"0.00\" currency=\"985\" />\n" +
        "    <Rate_Fee value=\"0.00\" />\n" +
        "    <Fixed_Fee value=\"0.00\" />\n" +
        "    <CommissionAmt value=\"0.00\" currency=\"985\" />\n" +
        "    <Classification RCC=\"\" MCC=\"5814\" />\n" +
        "    <Response approved=\"yes\" actioncode=\"0\" responsecode=\"00\" additionaldesc=\" AUTOMATY UVP           WARSZAWA      POL\" />\n" +
        "    <OrigTxnAmt value=\"2.70\" currency=\"985\" />\n" +
        "    <ReversalReason />\n" +
        "  </CardAuthorisation>";
    
}
