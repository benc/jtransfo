/*
 * This file is part of jTransfo, a library for converting to and from transfer objects.
 * Copyright (c) PROGS bvba, Belgium
 *
 * The program is available in open source according to the Apache License, Version 2.0.
 * For full licensing details, see LICENSE.txt in the project root.
 */

package org.jtransfo.jodatime;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.jtransfo.TypeConverter;
import org.jtransfo.object.Gender;
import org.junit.Test;

import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Test for DateDateMidnightConverter.
 */
public class DateDateMidnightConverterTest {

    private TypeConverter<Date, DateMidnight> typeConverter = new DateDateMidnightConverter();

    private DateMidnight dm1 = new DateMidnight(2012, 10, 15);
    private DateMidnight dm2 = new DateMidnight(2012, 2, 29);
    private DateMidnight dm3 = new DateMidnight(1974, 3, 31);
    private DateTime dm3t = new DateTime(1974, 3, 31, 11, 47);

    @Test
    public void testCanConvert() throws Exception {
        assertThat(typeConverter.canConvert(Date.class, DateMidnight.class)).isTrue();
        assertThat(typeConverter.canConvert(java.sql.Date.class, DateMidnight.class)).isTrue();
        assertThat(typeConverter.canConvert(Object.class, DateMidnight.class)).isFalse();
        assertThat(typeConverter.canConvert(Date.class, String.class)).isFalse();
        assertThat(typeConverter.canConvert(Gender.class, String.class)).isFalse();
    }

    @Test
    public void testConvert() throws Exception {
        assertThat(typeConverter.convert(null, DateMidnight.class)).isNull();
        assertThat(typeConverter.convert(dm1.toDate(), DateMidnight.class)).isEqualTo(dm1);
        assertThat(typeConverter.convert(dm2.toDate(), DateMidnight.class)).isEqualTo(dm2);
        assertThat(typeConverter.convert(dm3.toDate(), DateMidnight.class)).isEqualTo(dm3);
        assertThat(typeConverter.convert(dm3t.toDate(), DateMidnight.class)).isEqualTo(dm3);
    }

    @Test
    public void testReverse() throws Exception {
        assertThat(typeConverter.reverse(null, Date.class)).isNull();
        assertThat(typeConverter.reverse(dm1, Date.class)).isEqualTo(dm1.toDate());
        assertThat(typeConverter.reverse(dm2, Date.class)).isEqualTo(dm2.toDate());
        assertThat(typeConverter.reverse(dm3, Date.class)).isEqualTo(dm3.toDate());
    }
}
