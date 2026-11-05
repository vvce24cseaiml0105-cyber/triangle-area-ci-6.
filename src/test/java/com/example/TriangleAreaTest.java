package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TriangleAreaTest {

    @Test
    void testArea() {

        assertEquals(25.0,
            TriangleArea.calculateArea(10, 5));

    }

    @Test
    void testZeroHeight() {

        assertEquals(0.0,
            TriangleArea.calculateArea(10, 0));

    }

    @Test
    void testAnotherArea() {

        assertEquals(50.0,
            TriangleArea.calculateArea(10, 10));

    }

    @Test
    void testDecimalValues() {

        assertEquals(12.5,
            TriangleArea.calculateArea(5, 5));

    }

}