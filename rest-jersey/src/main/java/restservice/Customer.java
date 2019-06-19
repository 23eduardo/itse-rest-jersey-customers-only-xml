package restservice;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
@XmlRootElement(name="customer")
public class Customer   {
  // Maps a object property to a XML element derived from property name.

  @XmlElement public int id;
  @XmlElement public String firstname;
  @XmlElement public String lastname;
  @XmlElement public String email;
}
