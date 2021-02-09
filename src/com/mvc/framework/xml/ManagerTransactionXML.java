/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.xml;

import com.mvc.framework.transaction.Transaction;
import com.mvc.framework.xml.constances.MessagesError;
import com.mvc.framework.xml.exceptions.BadConfigException;
import com.mvc.framework.xml.exceptions.NoFileConfigException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author emman
 */
public class ManagerTransactionXML implements XMLManager<Transaction> {

    private final String configFile;
    static final String NAME = "name";
    static final String TRANSACTION = "transaction";
    static final String CONTROLLER = "controller";
    static final String MODEL = "model";
    static final String MODEL_NAME_FUNC = "nameFunction";

    public ManagerTransactionXML(String configFile) {
        this.configFile = configFile;
    }

    @Override
    public List<Transaction> readData() throws NoFileConfigException, BadConfigException {
        List<Transaction> items = new ArrayList<Transaction>();
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(configFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            Transaction transaction = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have an item element, we create a new item
                    String elementName = startElement.getName().getLocalPart();
                    Iterator<Attribute> attributes = startElement.getAttributes();
                    switch (elementName) {
                        case TRANSACTION:
                            transaction = new Transaction();
                            // We read the attributes from this tag and add the date
                            // attribute to our object

                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(NAME)) {
                                    transaction.setName(attribute.getValue());
                                }
                            }
                            break;
                        case CONTROLLER:

                            event = eventReader.nextEvent();
                            transaction.setController(event.asCharacters().getData());

                            break;

                        case MODEL:

                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(MODEL_NAME_FUNC)) {
                                    transaction.setModelFunction(attribute.getValue());
                                }
                            }
                            event = eventReader.nextEvent();
                            transaction.setModel(event.asCharacters().getData());
                            break;
                    }
                }
                // If we reach the end of an item element, we add it to the list

                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(TRANSACTION)) {
                        if (!isCorrectTransaction(transaction)) {
                            throw new BadConfigException(MessagesError.MSG_ERROR_BAD_CONFIG.toString());
                        }

                        items.add(transaction);
                    }
                }

            }
        } catch (FileNotFoundException ex) {
            throw new NoFileConfigException(MessagesError.MSG_ERROR_NO_CONFIG_FILE_EXISTS.toString());
        } catch (XMLStreamException ex) {
            throw new BadConfigException(MessagesError.MSG_ERROR_BAD_CONFIG.toString());
        }

        return items;
    }

    private Boolean isCorrectTransaction(Transaction transaction) {
        return transaction.getName() != null && transaction.getController() != null && transaction.getModel() != null
                && transaction.getModelFunction() != null;
    }

}
