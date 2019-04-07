/*
 * This file is generated by jOOQ.
 */
package org.gdg.withgo.data.database;


import javax.annotation.Generated;

import org.gdg.withgo.data.database.tables.Account;
import org.gdg.withgo.data.database.tables.Applicant;
import org.gdg.withgo.data.database.tables.Event;
import org.gdg.withgo.data.database.tables.Organization;
import org.gdg.withgo.data.database.tables.OrganizationMember;
import org.gdg.withgo.data.database.tables.Ticket;
import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index ACCOUNT_EMAIL_KEY = Indexes0.ACCOUNT_EMAIL_KEY;
    public static final Index ACCOUNT_NAME_KEY = Indexes0.ACCOUNT_NAME_KEY;
    public static final Index ACCOUNT_PHONE_KEY = Indexes0.ACCOUNT_PHONE_KEY;
    public static final Index ACCOUNT_PKEY = Indexes0.ACCOUNT_PKEY;
    public static final Index APPLICANT_PKEY = Indexes0.APPLICANT_PKEY;
    public static final Index EVENT_PKEY = Indexes0.EVENT_PKEY;
    public static final Index ORGANIZATION_PKEY = Indexes0.ORGANIZATION_PKEY;
    public static final Index ORGANIZATION_MEMBER_PKEY = Indexes0.ORGANIZATION_MEMBER_PKEY;
    public static final Index TICKET_PKEY = Indexes0.TICKET_PKEY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index ACCOUNT_EMAIL_KEY = Internal.createIndex("account_email_key", Account.ACCOUNT, new OrderField[] { Account.ACCOUNT.EMAIL }, true);
        public static Index ACCOUNT_NAME_KEY = Internal.createIndex("account_name_key", Account.ACCOUNT, new OrderField[] { Account.ACCOUNT.NAME }, true);
        public static Index ACCOUNT_PHONE_KEY = Internal.createIndex("account_phone_key", Account.ACCOUNT, new OrderField[] { Account.ACCOUNT.PHONE }, true);
        public static Index ACCOUNT_PKEY = Internal.createIndex("account_pkey", Account.ACCOUNT, new OrderField[] { Account.ACCOUNT.ID }, true);
        public static Index APPLICANT_PKEY = Internal.createIndex("applicant_pkey", Applicant.APPLICANT, new OrderField[] { Applicant.APPLICANT.EVENT_ID, Applicant.APPLICANT.USER_ID }, true);
        public static Index EVENT_PKEY = Internal.createIndex("event_pkey", Event.EVENT, new OrderField[] { Event.EVENT.ID }, true);
        public static Index ORGANIZATION_PKEY = Internal.createIndex("organization_pkey", Organization.ORGANIZATION, new OrderField[] { Organization.ORGANIZATION.ID }, true);
        public static Index ORGANIZATION_MEMBER_PKEY = Internal.createIndex("organization_member_pkey", OrganizationMember.ORGANIZATION_MEMBER, new OrderField[] { OrganizationMember.ORGANIZATION_MEMBER.ORG_ID, OrganizationMember.ORGANIZATION_MEMBER.USER_ID }, true);
        public static Index TICKET_PKEY = Internal.createIndex("ticket_pkey", Ticket.TICKET, new OrderField[] { Ticket.TICKET.ID }, true);
    }
}
