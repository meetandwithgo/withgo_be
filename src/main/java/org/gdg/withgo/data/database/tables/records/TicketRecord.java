/*
 * This file is generated by jOOQ.
 */
package org.gdg.withgo.data.database.tables.records;


import javax.annotation.Generated;

import org.gdg.withgo.data.database.tables.Ticket;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


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
public class TicketRecord extends UpdatableRecordImpl<TicketRecord> implements Record6<Integer, Integer, Integer, String, String, Integer> {

    private static final long serialVersionUID = -1529461182;

    /**
     * Setter for <code>public.ticket.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.ticket.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.ticket.max</code>.
     */
    public void setMax(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.ticket.max</code>.
     */
    public Integer getMax() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.ticket.event_id</code>.
     */
    public void setEventId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.ticket.event_id</code>.
     */
    public Integer getEventId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>public.ticket.name</code>.
     */
    public void setName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.ticket.name</code>.
     */
    public String getName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.ticket.description</code>.
     */
    public void setDescription(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.ticket.description</code>.
     */
    public String getDescription() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.ticket.price</code>.
     */
    public void setPrice(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.ticket.price</code>.
     */
    public Integer getPrice() {
        return (Integer) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, Integer, Integer, String, String, Integer> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, Integer, Integer, String, String, Integer> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Ticket.TICKET.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return Ticket.TICKET.MAX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return Ticket.TICKET.EVENT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Ticket.TICKET.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Ticket.TICKET.DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return Ticket.TICKET.PRICE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getMax();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component3() {
        return getEventId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component6() {
        return getPrice();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getMax();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getEventId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getPrice();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TicketRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TicketRecord value2(Integer value) {
        setMax(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TicketRecord value3(Integer value) {
        setEventId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TicketRecord value4(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TicketRecord value5(String value) {
        setDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TicketRecord value6(Integer value) {
        setPrice(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TicketRecord values(Integer value1, Integer value2, Integer value3, String value4, String value5, Integer value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TicketRecord
     */
    public TicketRecord() {
        super(Ticket.TICKET);
    }

    /**
     * Create a detached, initialised TicketRecord
     */
    public TicketRecord(Integer id, Integer max, Integer eventId, String name, String description, Integer price) {
        super(Ticket.TICKET);

        set(0, id);
        set(1, max);
        set(2, eventId);
        set(3, name);
        set(4, description);
        set(5, price);
    }
}