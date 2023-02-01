drop table order_item cascade constraint purge;
drop table orders cascade constraint purge;
drop table payment_method cascade constraint purge;

CREATE TABLE order_item (
    psid         VARCHAR2(25 BYTE) NOT NULL,
    oid   VARCHAR2(40 BYTE) NOT NULL,
    oicount      NUMBER NOT NULL,
    oitotalprice NUMBER(8) NOT NULL
);

ALTER TABLE order_item ADD CONSTRAINT order_item_pk PRIMARY KEY ( psid, oid );

CREATE TABLE orders (
    oid          VARCHAR2(40 BYTE) NOT NULL,
    pmcode       VARCHAR2(30 BYTE) NOT NULL,
    mid          VARCHAR2(15 BYTE) NOT NULL,
    oreceiver    VARCHAR2(10 BYTE) NOT NULL,
    oaddress1    VARCHAR2(100 BYTE) NOT NULL,
    oaddress2    VARCHAR2(100 BYTE),
    ozipcode     VARCHAR2(6 BYTE) NOT NULL,
    ophone       VARCHAR2(11 BYTE) NOT NULL,
    oemail       VARCHAR2(60 BYTE),
    otel         VARCHAR2(11 BYTE),
    omemo        VARCHAR2(60 BYTE),
    ousedmileage NUMBER,
    obeforeprice NUMBER(8) NOT NULL,
    oafterprice  NUMBER(8) NOT NULL,
    ostatus      VARCHAR2(15 BYTE) NOT NULL,
    odate        DATE,
    cpid         VARCHAR2(30 BYTE)
);

ALTER TABLE orders ADD CONSTRAINT orders_pk PRIMARY KEY ( oid );

CREATE TABLE payment_method (
    pmcode    VARCHAR2(30 BYTE) NOT NULL,
    pmcompany VARCHAR2(30 BYTE),
    pmmethod  NUMBER(1)
);

ALTER TABLE payment_method ADD CONSTRAINT payment_method_pk PRIMARY KEY ( pmcode );

ALTER TABLE payment_method ADD CONSTRAINT payment_method__un UNIQUE ( pmcompany,
                                                                      pmmethod );

ALTER TABLE order_item
    ADD CONSTRAINT order_item_orders_fk FOREIGN KEY ( oid )
        REFERENCES orders ( oid )
            ON DELETE CASCADE;

ALTER TABLE orders
    ADD CONSTRAINT orders_payment_method_fk FOREIGN KEY ( pmcode )
        REFERENCES payment_method ( pmcode );