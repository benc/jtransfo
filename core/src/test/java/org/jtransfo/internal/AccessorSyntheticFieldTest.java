/*
 * This file is part of jTransfo, a library for converting to and from transfer objects.
 * Copyright (c) PROGS bvba, Belgium
 *
 * The program is available in open source according to the Apache License, Version 2.0.
 * For full licensing details, see LICENSE.txt in the project root.
 */

package org.jtransfo.internal;

import org.jtransfo.JTransfoException;
import org.jtransfo.object.SimpleBaseDomain;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Field;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for AccessorSyntheticField.
 */
public class AccessorSyntheticFieldTest {

    private AccessorSyntheticField accessorSyntheticField;

    private SimpleBaseDomain domain;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        domain = spy(new SimpleBaseDomain());

        Field field = SimpleBaseDomain.class.getDeclaredField("a");
        ReflectionHelper reflectionHelper = new ReflectionHelper();
        reflectionHelper.makeAccessible(field);
        accessorSyntheticField = new AccessorSyntheticField(reflectionHelper, SimpleBaseDomain.class, field);
    }

    @Test
    public void testGet() throws Exception {
        accessorSyntheticField.get(domain);

        verify(domain).getA();
    }

    @Test
    public void testGetNoGetter() throws Exception {
        ReflectionTestUtils.setField(domain, "a", "zzz");
        ReflectionTestUtils.setField(accessorSyntheticField, "getter", null);

        assertThat(accessorSyntheticField.get(domain)).isEqualTo("zzz");

        verify(domain, times(0)).getA(); // only in setup
    }

    @Test
    public void testGetInvocationTargetException() throws Exception {
        when(domain.getA()).thenThrow(new JTransfoException("xxx"));

        exception.expect(JTransfoException.class);
        exception.expectMessage("Trying to use getA on object of type org.jtransfo.object.SimpleBaseDomain");

        accessorSyntheticField.get(domain);
    }

    @Test
    public void testSet() throws Exception {
        accessorSyntheticField.set(domain, "bla");

        verify(domain).setA("bla");
    }

    @Test
    public void testSetNoSetter() throws Exception {
        ReflectionTestUtils.setField(accessorSyntheticField, "setter", null);

        accessorSyntheticField.set(domain, "bla");

        verify(domain, times(0)).setA("bla");
        assertThat(domain.getA()).isEqualTo("bla");
    }

    @Test
    public void testSetInvocationTargetException() throws Exception {
        doThrow(new JTransfoException("xxx")).when(domain).setA("bla");

        exception.expect(JTransfoException.class);
        exception.expectMessage("Trying to use setA on object of type org.jtransfo.object.SimpleBaseDomain");

        accessorSyntheticField.set(domain, "bla");
    }

    @Test
    public void testGetName() throws Exception {
        assertThat(accessorSyntheticField.getName()).isEqualTo("a");
    }

    @Test
    public void testGetType() throws Exception {
        assertThat(accessorSyntheticField.getType()).isEqualTo(String.class);
    }
}
