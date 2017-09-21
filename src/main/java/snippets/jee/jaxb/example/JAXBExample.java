package snippets.jee.jaxb.example;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class JAXBExample {

    public static void main (String... args) throws Exception {
        doJAXBXml();
    }

    //Create XML from Java object and then vice versa
    public static void doJAXBXml () throws Exception {
        Course course = new Course(1, "Coruse-1", 5);
        course.setTeacher(new Teacher(1, "Teacher-1"));

        JAXBContext context = JAXBContext.newInstance(Course.class);

        //Marshal Java object to XML
        Marshaller marshaller = context.createMarshaller();
        //Set option to format generated XML
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        //Marshal Course object and write to the StringWriter
        marshaller.marshal(course, stringWriter);
        //Get String from the StringWriter
        String courseXML = stringWriter.getBuffer().toString();
        stringWriter.close();
        //Print course XML
        System.out.println(courseXML);

        //Now unmarshal courseXML to create Course object
        Unmarshaller unmarshaller = context.createUnmarshaller();
        //Create StringReader from courseXml
        StringReader stringReader = new StringReader(courseXML);
        //Create StreamSource which will be used by JAXB unmarshaller
        StreamSource streamSource = new StreamSource(stringReader);
        Course unmarshalledCourse = unmarshaller.unmarshal(streamSource, Course.class).getValue();
        System.out.println("--------------------------\nUnmarshalled course name - " + unmarshalledCourse.getName());
        stringReader.close();
    }
}
