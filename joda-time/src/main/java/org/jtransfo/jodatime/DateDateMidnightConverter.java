/*
 * This file is part of jTransfo, a library for converting to and from transfer objects.
 * Copyright (c) PROGS bvba, Belgium
 *
 * The program is available in open source according to the Apache License, Version 2.0.
 * For full licensing details, see LICENSE.txt in the project root.
 */

package org.jtransfo.jodatime;

import org.joda.time.DateMidnight;
import org.jtransfo.JTransfoException;
import org.jtransfo.TypeConverter;
import org.jtransfo.internal.SyntheticField;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Converter between {@link Date} and {@link DateMidnight}.
 */
public class DateDateMidnightConverter implements TypeConverter<Date, DateMidnight> {

    @Override
    public boolean canConvert(Type realToType, Type realDomainType) {
        return Date.class.equals(realToType) && DateMidnight.class.equals(realDomainType);
    }

    @Override
    public DateMidnight convert(Date object, SyntheticField domainField, Object domainObject, String... tags)
            throws JTransfoException {
        if (null == object) {
            return null;
        }
        return new DateMidnight(object.getTime());
    }

    @Override
    public Date reverse(DateMidnight object, SyntheticField toField, Object toObject, String... tags)
            throws JTransfoException {
        if (null == object) {
            return null;
        }
        return object.toDate();
    }
}
