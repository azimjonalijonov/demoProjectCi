package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

class ForTestTest {
    @org.junit.jupiter.api.Test
    void testAdd() {
         ForTest forTest = new ForTest();

         int result = forTest.add(2, 3);

         assertEquals(5, result, "Adding 2 and 3 should equal 5");
    }

}