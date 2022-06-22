package com.geekbrains.lesson4.hw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.geekbrains.lesson4.hw.Triangle.calcAreaTriangle;

public class TriangleTest {
    private static Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    @BeforeAll
    static void beforeAll() {
        logger.info("info");
    }


    @Test
    void checkTriangleAreaFormula() throws Exception {
        Assertions.assertEquals(9.797, calcAreaTriangle(5, 7, 4), 0.001);
    }

    @Test
    void exceptionWhenBadTriangle() {
        Assertions.assertThrows(Exception.class, () -> calcAreaTriangle(5, 0, 1));
    }
}
