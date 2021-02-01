/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.xml;

import com.mvc.framework.transaction.Transaction;
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

    private String configFile;
    static final String NAME = "name";
    static final String TRANSACTION = "transaction";
    static final String CONTROLLER = "controller";
    static final String CONTROLLER_NAME_FUNC = "nameFunction";
    static final String VIEW = "view";
    static final String VIEW_NAME_FUNC = "nameFunction";
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

                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(CONTROLLER_NAME_FUNC)) {
                                    transaction.setController_func(attribute.getValue());
                                }
                            }
                            event = eventReader.nextEvent();
                            transaction.setController(event.asCharacters().getData());

                            break;
                        case VIEW:

                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(VIEW_NAME_FUNC)) {
                                    transaction.setView_func(attribute.getValue());
                                }
                            }
                            event = eventReader.nextEvent();
                            transaction.setView(event.asCharacters().getData());
                            break;
                        case MODEL:

                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(MODEL_NAME_FUNC)) {
                                    transaction.setModel_func(attribute.getValue());
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
                            throw new BadConfigException("Archivo de configuracion mal definido");
                        }
                        items.add(transaction);
                    }
                }

            }
        } catch (FileNotFoundException ex) {
            throw new NoFileConfigException("Archivo de configuracion inexistente");
        } catch (XMLStreamException ex) {
            throw new BadConfigException("Archivo de configuracion mal definido");
        }

        return items;
    }

    private Boolean isCorrectTransaction(Transaction transaction) {
        if (transaction.getController() == null || transaction.getController_func() == null || transaction.getModel() == null
                || transaction.getModel_func() == null) {
            return false;
        }
        return true;
    }

}