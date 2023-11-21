package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ForTestTest {

    @InjectMocks
    private ForTest forTest;

    @Mock
    private ResultRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdd() {
         long a = 5;
        int b = 3;
        ResultEntity result = forTest.add(a, b);

         assertNotNull(result);
        verify(repository, times(1)).save(any(ResultEntity.class));
    }
}
