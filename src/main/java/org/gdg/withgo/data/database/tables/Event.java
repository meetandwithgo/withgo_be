/*
 * This file is generated by jOOQ.
 */
package org.gdg.withgo.data.database.tables;


import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.gdg.withgo.data.database.Indexes;
import org.gdg.withgo.data.database.Keys;
import org.gdg.withgo.data.database.Public;
import org.gdg.withgo.data.database.tables.records.EventRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Event extends TableImpl<EventRecord> {

    private static final long serialVersionUID = -452667571;

    /**
     * The reference instance of <code>public.event</code>
     */
    public static final Event EVENT = new Event();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<EventRecord> getRecordType() {
        return EventRecord.class;
    }

    /**
     * The column <code>public.event.id</code>.
     */
    public final TableField<EventRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('event_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.event.owner_id</code>.
     */
    public final TableField<EventRecord, Integer> OWNER_ID = createField("owner_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.event.title</code>.
     */
    public final TableField<EventRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR(300).nullable(false), this, "");

    /**
     * The column <code>public.event.thumbnail</code>.
     */
    public final TableField<EventRecord, String> THUMBNAIL = createField("thumbnail", org.jooq.impl.SQLDataType.VARCHAR(300).nullable(false), this, "");

    /**
     * The column <code>public.event.content</code>.
     */
    public final TableField<EventRecord, String> CONTENT = createField("content", org.jooq.impl.SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.event.sales_start</code>.
     */
    public final TableField<EventRecord, Date> SALES_START = createField("sales_start", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");

    /**
     * The column <code>public.event.sales_end</code>.
     */
    public final TableField<EventRecord, Date> SALES_END = createField("sales_end", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");

    /**
     * The column <code>public.event.start_date</code>.
     */
    public final TableField<EventRecord, Date> START_DATE = createField("start_date", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");

    /**
     * The column <code>public.event.end_date</code>.
     */
    public final TableField<EventRecord, Date> END_DATE = createField("end_date", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");

    /**
     * Create a <code>public.event</code> table reference
     */
    public Event() {
        this(DSL.name("event"), null);
    }

    /**
     * Create an aliased <code>public.event</code> table reference
     */
    public Event(String alias) {
        this(DSL.name(alias), EVENT);
    }

    /**
     * Create an aliased <code>public.event</code> table reference
     */
    public Event(Name alias) {
        this(alias, EVENT);
    }

    private Event(Name alias, Table<EventRecord> aliased) {
        this(alias, aliased, null);
    }

    private Event(Name alias, Table<EventRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Event(Table<O> child, ForeignKey<O, EventRecord> key) {
        super(child, key, EVENT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.EVENT_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<EventRecord, Integer> getIdentity() {
        return Keys.IDENTITY_EVENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<EventRecord> getPrimaryKey() {
        return Keys.EVENT_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<EventRecord>> getKeys() {
        return Arrays.<UniqueKey<EventRecord>>asList(Keys.EVENT_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<EventRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<EventRecord, ?>>asList(Keys.EVENT__EVENT_OWNER_ID_FKEY);
    }

    public Account account() {
        return new Account(this, Keys.EVENT__EVENT_OWNER_ID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event as(String alias) {
        return new Event(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event as(Name alias) {
        return new Event(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Event rename(String name) {
        return new Event(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Event rename(Name name) {
        return new Event(name, null);
    }
}
