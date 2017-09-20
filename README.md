# JAXB Exmaple Project
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
