/*
 * This file is part of jTransfo, a library for converting to and from transfer objects.
 * Copyright (c) PROGS bvba, Belgium
 *
 * The program is available in open source according to the Apache License, Version 2.0.
 * For full licensing details, see LICENSE.txt in the project root.
 */

package org.jtransfo.demo.domain;

import lombok.Data;
import org.jtransfo.DomainClass;
import org.jtransfo.MappedBy;

/**
 * Voice contact details.
 */
@Data
@DomainClass(domainClass = VoiceContact.class)
public class VoiceContactTo {

    @MappedBy(readOnly = true) // writable database id can cause Hibernate exceptions
    private Long id;
    private String type;
    private String voice;

}
