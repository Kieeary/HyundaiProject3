drop table cart cascade constraint purge;
drop table coupon_detail cascade constraint purge;
drop table event cascade constraint purge;
drop table home_img cascade constraint purge;
drop table home_order cascade constraint purge;
drop table likes cascade constraint purge;
drop table member cascade constraint purge;
drop table today cascade constraint purge;

drop SEQUENCE seq_coupon_detail;


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
    cpexpiredate TIMESTAMP NOT NULL,
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
'123123', '{noop}1111', '?????????', 'user1@gmail.com', '01023398197',
'0222222222', '07285', '??????????????? ???????????? ?????????9?????? 8 (?????????5???, ??????????????????)', '103???708???', '1997-07-27',
1, '1', 'Email', 1, 1,
'ROLE_USER', 1, 1000
);


insert into member 
(
mid, mpassword, mname, memail, mphone, 
mtel, mzipcode, maddress1, maddress2, mbirth,
mgender, mrefid, mlogintype, mtosno, menabled,
mrole, mgrade, mmileage
) 
values(
'123333', '{noop}1111', '?????????', 'wangjh789@gmail.com', '01023398194',
'0222222222', '07285', '??????????????? ???????????? ?????????9?????? 8 (?????????5???, ??????????????????)', '103???708???', '1997-07-27',
1, '1', 'Email', 1, 1,
'ROLE_USER', 1, 1000
);

insert into event 
(eno, etitle, econtent, eissuedate, eexpiredate, 
elimitcount, ecount, eimg, ediscount, estatus,
edetailimg, ecoupontitle) values
(123121, '?????????????????? ?????? ?????? ??????', 'http://cdn.thehandsome.com/mobile/event/detail/image/handsome_202212/event_newmember_dec_mob_img_01.jpg', sysdate, sysdate+1,
1000, 30, 'http://cdn.thehandsome.com/mobile/event/list/banner/20221213_57502766621122035_ko.jpg',30, 1,
'http://cdn.thehandsome.com/mobile/event/detail/image/handsome_202212/event_newmember_dec_mob_img_02.jpg','?????? ?????? ??????');


insert into cart (MID, PSID, PQUANTITY)
values ('123123', 'TM2D1TPC424W_IV_61', 1);

insert into coupon_detail 
(cpid, eno, mid, cpissuedate, cpexpiredate, 
cpusedate, cpstatus) values
('11111','123121','123123', '2023/1/1', '2023/3/30',
null, 1);

create sequence seq_coupon_detail;

