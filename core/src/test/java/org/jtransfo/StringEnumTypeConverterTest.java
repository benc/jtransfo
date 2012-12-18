/*
 * This file is part of jTransfo, a library for converting to and from transfer objects.
 * Copyright (c) PROGS bvba, Belgium
 *
 * The program is available in open source according to the Apache License, Version 2.0.
 * For full licensing details, see LICENSE.txt in the project root.
 */

package org.jtransfo;

import org.jtransfo.object.Gender;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Test for StringEnumTypeConverterTest.
 */
public class StringEnumTypeConverterTest {

    private TypeConverter<String, Gender> enumTypeConverter;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() throws Exception {
        enumTypeConverter = new StringEnumTypeConverter(Gender.class);
    }

    @Test
    public void testCanConvert() throws Exception {
        assertThat(enumTypeConverter.canConvert(String.class, Gender.class)).isTrue();
        assertThat(enumTypeConverter.canConvert(Object.class, Gender.class)).isFalse();
        assertThat(enumTypeConverter.canConvert(String.class, String.class)).isFalse();
        assertThat(enumTypeConverter.canConvert(Gender.class, String.class)).isFalse();
    }

    @Test
    public void testConvert() throws Exception {
        assertThat(enumTypeConverter.convert(Gender.MALE.name())).isEqualTo(Gender.MALE);
        assertThat(enumTypeConverter.convert(Gender.FEMALE.name())).isEqualTo(Gender.FEMALE);
        assertThat(enumTypeConverter.convert(null)).isNull();
        assertThat(enumTypeConverter.convert("")).isNull();

        exception.expect(IllegalArgumentException.class);
        assertThat(enumTypeConverter.convert("blabla"));
    }

    @Test
    public void testReverse() throws Exception {
        assertThat(enumTypeConverter.reverse(Gender.MALE)).isEqualTo(Gender.MALE.name());
        assertThat(enumTypeConverter.reverse(Gender.FEMALE)).isEqualTo(Gender.FEMALE.name());
        assertThat(enumTypeConverter.reverse(null)).isNull();
    }

}
