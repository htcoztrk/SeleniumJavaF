package com.testinium;

import org.junit.jupiter.api.Test;

public class ExampleTest extends BaseTest {

Steps steps = new Steps();

@Test
public void exampleTest() {
    steps.goToPage("https://www.amazon.com.tr/");
    }
}
