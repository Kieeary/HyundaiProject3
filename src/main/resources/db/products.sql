-- 생성자 Oracle SQL Developer Data Modeler 22.2.0.165.1149
--   위치:        2023-01-31 12:29:23 KST
--   사이트:      Oracle Database 11g
--   유형:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE brand (
    bno   NUMBER NOT NULL,
    bname VARCHAR2(80 BYTE) NOT NULL
);

ALTER TABLE brand ADD CONSTRAINT brand_pk PRIMARY KEY ( bno );

ALTER TABLE brand ADD CONSTRAINT brand__un UNIQUE ( bname );

CREATE TABLE category (
    cateno     NUMBER NOT NULL,
    depth1name VARCHAR2(50 BYTE) NOT NULL,
    depth2name VARCHAR2(50 BYTE) NOT NULL,
    depth3name VARCHAR2(50 BYTE) NOT NULL
);

ALTER TABLE category ADD CONSTRAINT category_pkv2 PRIMARY KEY ( cateno );

ALTER TABLE category
    ADD CONSTRAINT category__unv1 UNIQUE ( depth1name,
                                           depth2name,
                                           depth3name );

CREATE TABLE depth1 (
    depth1name VARCHAR2(50 BYTE) NOT NULL
);

ALTER TABLE depth1 ADD CONSTRAINT depth1_pk PRIMARY KEY ( depth1name );

CREATE TABLE depth2 (
    depth2name VARCHAR2(50 BYTE) NOT NULL
);

ALTER TABLE depth2 ADD CONSTRAINT depth2_pk PRIMARY KEY ( depth2name );

CREATE TABLE depth3 (
    depth3name VARCHAR2(50 BYTE) NOT NULL
);

ALTER TABLE depth3 ADD CONSTRAINT depth3_pk PRIMARY KEY ( depth3name );

CREATE TABLE prodcuct_stock (
    psid    VARCHAR2(25 BYTE) NOT NULL,
    psstock NUMBER NOT NULL,
    psize   VARCHAR2(20) NOT NULL,
    pcid    VARCHAR2(25 BYTE) NOT NULL
);

ALTER TABLE prodcuct_stock ADD CONSTRAINT prodcut_stock_pk PRIMARY KEY ( psid );

CREATE TABLE product_category (
    cateno NUMBER NOT NULL,
    pid    VARCHAR2(15 BYTE) NOT NULL
);

ALTER TABLE product_category ADD CONSTRAINT product_category_pk PRIMARY KEY ( pid,
                                                                              cateno );

CREATE TABLE product_color (
    pcid         VARCHAR2(25 BYTE) NOT NULL,
    pcimg1       VARCHAR2(90 BYTE) NOT NULL,
    pcimg2       VARCHAR2(90 BYTE) NOT NULL,
    pcimg3       VARCHAR2(90 BYTE) NOT NULL,
    pcchipimg    VARCHAR2(90 BYTE) NOT NULL,
    pccolorcode  VARCHAR2(30 BYTE) NOT NULL,
    pcprice      NUMBER(10) NOT NULL,
    preleasedate DATE,
    pid          VARCHAR2(15 BYTE) NOT NULL
);

ALTER TABLE product_color ADD CONSTRAINT product_color_pk PRIMARY KEY ( pcid );

CREATE TABLE product_common (
    pid     VARCHAR2(15 BYTE) NOT NULL,
    pname   VARCHAR2(80 BYTE) NOT NULL,
    pnote   VARCHAR2(700 BYTE),
    pstatus NUMBER(1),
    bno     NUMBER NOT NULL
);

ALTER TABLE product_common ADD CONSTRAINT product_common_pk PRIMARY KEY ( pid );

CREATE TABLE with_product (
    pcid     VARCHAR2(25 BYTE) NOT NULL,
    withpcid VARCHAR2(25 BYTE) NOT NULL
);

ALTER TABLE with_product ADD CONSTRAINT with_product_pk PRIMARY KEY ( pcid,
                                                                      withpcid );

ALTER TABLE category
    ADD CONSTRAINT category_depth1_fk FOREIGN KEY ( depth1name )
        REFERENCES depth1 ( depth1name );

ALTER TABLE category
    ADD CONSTRAINT category_depth2_fk FOREIGN KEY ( depth2name )
        REFERENCES depth2 ( depth2name );

ALTER TABLE category
    ADD CONSTRAINT category_depth3_fk FOREIGN KEY ( depth3name )
        REFERENCES depth3 ( depth3name );

ALTER TABLE prodcuct_stock
    ADD CONSTRAINT prodcut_stock_product_color_fk FOREIGN KEY ( pcid )
        REFERENCES product_color ( pcid );

ALTER TABLE product_category
    ADD CONSTRAINT product_category_category_fk FOREIGN KEY ( cateno )
        REFERENCES category ( cateno );

ALTER TABLE product_category
    ADD CONSTRAINT product_category_common_fk FOREIGN KEY ( pid )
        REFERENCES product_common ( pid );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE product_color
    ADD CONSTRAINT product_color_pr_common_fk FOREIGN KEY ( pid )
        REFERENCES product_common ( pid );

ALTER TABLE product_common
    ADD CONSTRAINT product_common_brand_fk FOREIGN KEY ( bno )
        REFERENCES brand ( bno );

ALTER TABLE with_product
    ADD CONSTRAINT with_product_product_color_fk FOREIGN KEY ( pcid )
        REFERENCES product_color ( pcid )
            ON DELETE CASCADE;

ALTER TABLE with_product
    ADD CONSTRAINT with_product_product_color_fk2 FOREIGN KEY ( withpcid )
        REFERENCES product_color ( pcid )
            ON DELETE CASCADE;


