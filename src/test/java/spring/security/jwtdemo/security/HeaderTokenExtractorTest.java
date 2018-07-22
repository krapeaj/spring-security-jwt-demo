package spring.security.jwtdemo.security;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HeaderTokenExtractorTest {
    private HeaderTokenExtractor extractor;

    @Before
    public void setUp() throws Exception {
        extractor = new HeaderTokenExtractor();
    }

    @Test
    public void extract() {
        String header = "Bearer fsdfasqewrqwcxvdasdfasd.adsfsddfgadsafsdf";
        assertThat(extractor.extract(header), is("fsdfasqewrqwcxvdasdfasd.adsfsddfgadsafsdf"));
    }
}