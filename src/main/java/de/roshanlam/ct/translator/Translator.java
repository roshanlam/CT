package de.roshanlam.ct.translator;

import com.google.common.collect.ImmutableMap;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import de.dhiller.babel.translator.yandex.xml.Translation;
import org.apache.http.entity.ContentType;

import java.io.StringReader;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import static com.google.common.base.Throwables.propagate;

public class Translator {

    private static final String apiKey = System.getProperty("translate.api.key");
    private final Unmarshaller unmarshaller;

    public Translator() {
        try {
            unmarshaller = JAXBContext.newInstance(Translation.class).createUnmarshaller();
        } catch (JAXBException e) {
            throw propagate(e);
        }
    }

    List<String> translate(List<String> input, Locale source, Locale target) {
        try {
            HttpResponse<String> stringHttpResponse = newTranslationResponse(input, source, target);
            return ((Translation) unmarshaller.unmarshal(
                    new StringReader(stringHttpResponse.getBody())))
                    .getText();
        } catch (UnirestException | JAXBException e) {
            throw propagate(e);
        }
    }

    private HttpResponse<String> newTranslationResponse(List<String> input, Locale source, Locale target)
            throws UnirestException {
        HttpRequest httpRequest = Unirest
                .get("https://translate.yandex.net/api/v1.5/tr/translate")
                .queryString(ImmutableMap.<String, Object>builder()
                                .put("key", apiKey)
                                .put("lang", source.getLanguage() + "-" + target.getLanguage())
                                .build()
                )
                .header("Accept", ContentType.APPLICATION_XML.toString());
        for (String line : input)
            httpRequest = httpRequest.queryString("text", line);
        return httpRequest.asString();
    }

}
