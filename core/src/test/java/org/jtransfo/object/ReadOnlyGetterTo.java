package org.jtransfo.object;

import org.jtransfo.DomainClass;
import org.jtransfo.MappedBy;

/**
 * Ttransfer object which refers to a (read-only) getter in the domain object.
 */
@DomainClass(domainClass = ReadOnlyGetterDomain.class)
public class ReadOnlyGetterTo {

    private String id;
    @MappedBy(readOnly = true)
    private String twice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTwice() {
        return twice;
    }

    public void setTwice(String twice) {
        this.twice = twice;
    }
}
