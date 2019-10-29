package de.roshanlam.ct.translator.yandex.xml;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Translation")

public class Translation {
    
    private String code;
    private String encoding;
    private List<String> text;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }
}
