CREATE TABLE cart (
    mid       VARCHAR2(30 BYTE) NOT NULL,
    psid      VARCHAR2(25 BYTE) NOT NULL,
    pquantity NUMBER(2) NOT NULL
);

ALTER TABLE cart ADD CONSTRAINT cart_pk PRIMARY KEY ( mid,
                                                      psid );

CREATE TABLE coupon_detail (
    cpid         VARCHAR2(30 BYTE) NOT NULL,
    eno          NUMBER NOT NULL,
    mid          VARCHAR2(30 BYTE) NOT NULL,
    cpissuedate  TIMESTAMP NOT NULL,
    cpexpriedate TIMESTAMP NOT NULL,
    cpusedate    TIMESTAMP,
    cpstatus     NUMBER(1)
);

ALTER TABLE coupon_detail ADD CONSTRAINT coupon_detail_pk PRIMARY KEY ( cpid );

ALTER TABLE coupon_detail ADD CONSTRAINT coupon_detail__un UNIQUE ( eno,
                                                                    mid );

CREATE TABLE event (
    eno          NUMBER NOT NULL,
    etitle       VARCHAR2(100 BYTE),
    econtent     VARCHAR2(200 BYTE),
    eissuedate   TIMESTAMP,
    eexpiredate  TIMESTAMP,
    elimitcount  NUMBER,
    ecount       NUMBER,
    eimg         VARCHAR2(400 BYTE),
    ediscount    NUMBER,
    estatus      NUMBER(1) NOT NULL,
    edetailimg   VARCHAR2(400 BYTE),
    ecoupontitle VARCHAR2(100)
);

ALTER TABLE event ADD CONSTRAINT event_pk PRIMARY KEY ( eno );

CREATE TABLE home_img (
    hiname VARCHAR2(100 BYTE) NOT NULL,
    himg   VARCHAR2(150 BYTE)
);

ALTER TABLE home_img ADD CONSTRAINT home_img_pk PRIMARY KEY ( hiname );

CREATE TABLE home_order (
    honame  VARCHAR2(50 BYTE),
    hoorder NUMBER NOT NULL,
    hiname  VARCHAR2(100 BYTE) NOT NULL
);

ALTER TABLE home_order ADD CONSTRAINT home_order_pk PRIMARY KEY ( hoorder );

CREATE TABLE likes (
    pid   VARCHAR2(15 BYTE) NOT NULL,
    mid   VARCHAR2(30 BYTE) NOT NULL,
    ltime NUMBER
);

ALTER TABLE likes ADD CONSTRAINT likes_pk PRIMARY KEY ( pid,
                                                        mid );

CREATE TABLE member (
    mid        VARCHAR2(30 BYTE) NOT NULL,
    mpassword  VARCHAR2(200 BYTE) NOT NULL,
    mname      VARCHAR2(80 BYTE) NOT NULL,
    memail     VARCHAR2(60 BYTE) NOT NULL,
    mphone     VARCHAR2(11 BYTE) NOT NULL,
    mtel       VARCHAR2(11 BYTE),
    mzipcode   VARCHAR2(11 BYTE),
    maddress1  VARCHAR2(200 BYTE),
    maddress2  VARCHAR2(200 BYTE),
    mbirth     DATE NOT NULL,
    mgender    NUMBER NOT NULL,
    mrefid     VARCHAR2(30 BYTE),
    mlogintype VARCHAR2(30 BYTE) NOT NULL,
    mtosno     NUMBER(1) NOT NULL,
    menabled   NUMBER(1) NOT NULL,
    mrole      VARCHAR2(100 BYTE) NOT NULL,
    mgrade     NUMBER(1),
    mmileage   NUMBER
);

ALTER TABLE member ADD CONSTRAINT member_pk PRIMARY KEY ( mid );

ALTER TABLE member ADD CONSTRAINT member__un UNIQUE ( mphone );

CREATE TABLE today (
    tvdate  DATE,
    tvcount NUMBER
);

ALTER TABLE cart
    ADD CONSTRAINT cart_member_fk FOREIGN KEY ( mid )
        REFERENCES member ( mid )
            ON DELETE CASCADE;

ALTER TABLE coupon_detail
    ADD CONSTRAINT coupon_detail_event_fk FOREIGN KEY ( eno )
        REFERENCES event ( eno );

ALTER TABLE coupon_detail
    ADD CONSTRAINT coupon_detail_member_fk FOREIGN KEY ( mid )
        REFERENCES member ( mid );

ALTER TABLE home_order
    ADD CONSTRAINT home_order_home_img_fk FOREIGN KEY ( hiname )
        REFERENCES home_img ( hiname );

ALTER TABLE likes
    ADD CONSTRAINT likes_member_fk FOREIGN KEY ( mid )
        REFERENCES member ( mid );

insert into member 
(
mid, mpassword, mname, memail, mphone, 
mtel, mzipcode, maddress1, maddress2, mbirth,
mgender, mrefid, mlogintype, mtosno, menabled,
mrole, mgrade, mmileage
) 
values(
'123123', '{noop}1111', '왕종휘', 'user1@gmail.com', '01023398197',
'0222222222', '07285', '서울특별시 영등포구 선유로9나길 8 (문래동5가, 문래두산위브)', '103동708호', sysdate,
1, '1', 'Email', 1, 1,
'ROLE_USER', 1, 1000
);