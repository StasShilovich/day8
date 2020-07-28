package com.shilovich.day8.model.service;

import com.shilovich.day8.exception.ServiceException;
import com.shilovich.day8.model.service.impl.CustomBookServiceImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CustomBookServiceTest {
    CustomBookService service;

    @BeforeClass
    public void setUp() {
        service = CustomBookServiceImpl.getInstance();
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindByIdNegative() throws ServiceException {
        service.find(0L);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testSortBookByParameterNegative() throws ServiceException {
        service.sortBookByParameter("tit1e");
    }
}