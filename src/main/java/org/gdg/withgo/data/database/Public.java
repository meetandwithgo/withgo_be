/*
 * This file is generated by jOOQ.
 */
package org.gdg.withgo.data.database;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.gdg.withgo.data.database.tables.Account;
import org.gdg.withgo.data.database.tables.Applicant;
import org.gdg.withgo.data.database.tables.Event;
import org.gdg.withgo.data.database.tables.Ticket;
import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1862666999;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.account</code>.
     */
    public final Account ACCOUNT = org.gdg.withgo.data.database.tables.Account.ACCOUNT;

    /**
     * The table <code>public.applicant</code>.
     */
    public final Applicant APPLICANT = org.gdg.withgo.data.database.tables.Applicant.APPLICANT;

    /**
     * The table <code>public.event</code>.
     */
    public final Event EVENT = org.gdg.withgo.data.database.tables.Event.EVENT;

    /**
     * The table <code>public.ticket</code>.
     */
    public final Ticket TICKET = org.gdg.withgo.data.database.tables.Ticket.TICKET;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        List result = new ArrayList();
        result.addAll(getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.<Sequence<?>>asList(
            Sequences.ACCOUNT_ID_SEQ,
            Sequences.EVENT_ID_SEQ,
            Sequences.TICKET_ID_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Account.ACCOUNT,
            Applicant.APPLICANT,
            Event.EVENT,
            Ticket.TICKET);
    }
}
