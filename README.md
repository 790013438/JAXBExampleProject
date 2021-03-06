# JAXB Example Project
## Course and Teacher

```java
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Course {

    @XmlAttribute
    private int id;
    @XmlElement(namespace="http://snippets.jee.jaxb.example")
    private String name;
    private int credits;
    @XmlElement(name="course_teacher")
    private Teacher teacher;

    public Course() {}

    public Course (int id, String name, int credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
    }
}
```

- When a Course is marshalled to an XML document, we want the course element to be the root. Therefore, the class is annotated with @XmlRootElement. You can specify a different name for the root element (other than the class name) by specifying the name attribute, for example:

```java
@XmlRootElement(name="school_course")
```
- The id field is marked as an attribute of the root element. You don't have to mark fields specifically as elements if there are public getters/setters for them. However, if you want to set additional attributes, then you need to annotate them with @ XmlElement. For example, we have specified namespace for the name element/field.  The credits field is not annotated, but it will still be marshalled as an XML element.

## Marshall Java object to XML and  Unmarshall courseXML to create Course object
- We are not annotating the Teacher class with JAXB because we are not going to marshal it directly. It will be marshalled by JAXB when an instance of Course is marshalled.  Let's create a class with the main method.  Create the JAXBExample class

```java
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
        String courseXMLString = stringWriter.getBuffer().toString();
        stringWriter.close();
        //Print course XML
        System.out.println(courseXMLString);

        //Now unmarshal courseXML to create Course object
        Unmarshaller unmarshaller = context.createUnmarshaller();
        //Create StringReader from courseXml
        StringReader stringReader = new StringReader(courseXMLString);
        //Create StreamSource which will be used by JAXB unmarshaller
        StreamSource streamSource = new StreamSource(stringReader);
        Course unmarshalledCourse = unmarshaller.unmarshal(streamSource, Course.class).getValue();
        System.out.println("--------------------------\nUnmarshalled course name - " + unmarshalledCourse.getName());
        stringReader.close();
    }
```
## json
```java
 public static void main (String... args) throws Exception {
        doJAXBJson();
    }

    //Create JSON from Java object and then vice versa
    public static void doJAXBJson() throws Exception {
        
        Course course = new Course(1, "Course-1", 5);
        course.setTeacher(new Teacher(1, "Teacher-1"));
        
        JAXBContext context = JAXBContext.newInstance(Class.class);
        
        //Marshl Java object to JSON
        Marshaller marshaller = context.createMarshaller();
        //Set option to format generated JSON
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
        marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
        
        StringWriter stringWriter = new StringWriter();
        //Marshal Course object and wirte to the StringWriter
        marshaller.marshal(course, stringWriter);
        //Get String from the StringWriter
        String courseJson = stringWriter.getBuffer().toString();
        stringWriter.close();
        //print couse JSON
        System.out.println(courseJson);
        
        //Now, umarshall courseJson to create Course object
        Unmarshaller unmarshaler = context.createUnmarshaller();
        unmarshaler.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
        unmarshaler.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
        
        //Create StringReader from courseJson
        StringReader stringReader = new StringReader(courseJson);
        //Create StringSource which will be used by JAXB unmarshaller
        StreamSource streamSource = new StreamSource(stringReader);
        Course unmarshalledCourse = unmarshaler.unmarshal(streamSource, Course.class).getValue();
        System.out.println("----------------------------------\nUnmarshalled course name - " + unmarshalledCourse.getName());
        stringReader.close();
    }
```
[这里](https://github.com/790013438/JAXBExampleProject/tree/json%23marshal)是json项目
[代码](https://github.com/790013438/JAXBExampleProject/blob/json%23marshal/src/main/java/snippets/jee/jaxb/example/JAXBExample.java)
[项目](https://github.com/790013438/JAXBExampleProject/network)分支
