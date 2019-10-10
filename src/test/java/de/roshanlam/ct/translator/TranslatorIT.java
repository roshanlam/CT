package de.roshanlam.ct.translator;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;
import java.util.Locale;

import static java.util.Arrays.asList;
import static java.util.Locale.ENGLISH;
import static java.util.Locale.FRENCH;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class TranslatorIT {

    private Translator underTest = new Translator();

    @Test
    public void translateEnglish() {
        assertThat(
                underTest.translate(asList("This is a test"), ENGLISH, FRENCH),
                Matchers.<List<String>>both(notNullValue()).and(not(empty()))
        );
    }

}
