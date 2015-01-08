
package service.webservice;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the service.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: service.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TransactionArray }
     * 
     */
    public TransactionArray createTransactionArray() {
        return new TransactionArray();
    }

    /**
     * Create an instance of {@link QueryConfig }
     * 
     */
    public QueryConfig createQueryConfig() {
        return new QueryConfig();
    }

    /**
     * Create an instance of {@link Receipt }
     * 
     */
    public Receipt createReceipt() {
        return new Receipt();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Transaction }
     * 
     */
    public Transaction createTransaction() {
        return new Transaction();
    }

}
