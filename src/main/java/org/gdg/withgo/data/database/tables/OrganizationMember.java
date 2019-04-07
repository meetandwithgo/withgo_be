/*
 * This file is generated by jOOQ.
 */
package org.gdg.withgo.data.database.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.gdg.withgo.data.database.Indexes;
import org.gdg.withgo.data.database.Keys;
import org.gdg.withgo.data.database.Public;
import org.gdg.withgo.data.database.tables.records.OrganizationMemberRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
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
public class OrganizationMember extends TableImpl<OrganizationMemberRecord> {

    private static final long serialVersionUID = -606683960;

    /**
     * The reference instance of <code>public.organization_member</code>
     */
    public static final OrganizationMember ORGANIZATION_MEMBER = new OrganizationMember();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OrganizationMemberRecord> getRecordType() {
        return OrganizationMemberRecord.class;
    }

    /**
     * The column <code>public.organization_member.user_id</code>.
     */
    public final TableField<OrganizationMemberRecord, Integer> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.organization_member.org_id</code>.
     */
    public final TableField<OrganizationMemberRecord, Integer> ORG_ID = createField("org_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>public.organization_member</code> table reference
     */
    public OrganizationMember() {
        this(DSL.name("organization_member"), null);
    }

    /**
     * Create an aliased <code>public.organization_member</code> table reference
     */
    public OrganizationMember(String alias) {
        this(DSL.name(alias), ORGANIZATION_MEMBER);
    }

    /**
     * Create an aliased <code>public.organization_member</code> table reference
     */
    public OrganizationMember(Name alias) {
        this(alias, ORGANIZATION_MEMBER);
    }

    private OrganizationMember(Name alias, Table<OrganizationMemberRecord> aliased) {
        this(alias, aliased, null);
    }

    private OrganizationMember(Name alias, Table<OrganizationMemberRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> OrganizationMember(Table<O> child, ForeignKey<O, OrganizationMemberRecord> key) {
        super(child, key, ORGANIZATION_MEMBER);
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
        return Arrays.<Index>asList(Indexes.ORGANIZATION_MEMBER_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<OrganizationMemberRecord> getPrimaryKey() {
        return Keys.ORGANIZATION_MEMBER_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<OrganizationMemberRecord>> getKeys() {
        return Arrays.<UniqueKey<OrganizationMemberRecord>>asList(Keys.ORGANIZATION_MEMBER_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<OrganizationMemberRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<OrganizationMemberRecord, ?>>asList(Keys.ORGANIZATION_MEMBER__ORGANIZATION_MEMBER_USER_ID_FKEY, Keys.ORGANIZATION_MEMBER__ORGANIZATION_MEMBER_ORG_ID_FKEY);
    }

    public Account account() {
        return new Account(this, Keys.ORGANIZATION_MEMBER__ORGANIZATION_MEMBER_USER_ID_FKEY);
    }

    public Organization organization() {
        return new Organization(this, Keys.ORGANIZATION_MEMBER__ORGANIZATION_MEMBER_ORG_ID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrganizationMember as(String alias) {
        return new OrganizationMember(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrganizationMember as(Name alias) {
        return new OrganizationMember(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public OrganizationMember rename(String name) {
        return new OrganizationMember(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OrganizationMember rename(Name name) {
        return new OrganizationMember(name, null);
    }
}