drop table brand cascade constraint purge;
drop table category cascade constraint purge;
drop table depth1 cascade constraint purge;
drop table depth2 cascade constraint purge;
drop table depth3 cascade constraint purge;
drop table product_stock cascade constraint purge;
drop table product_category cascade constraint purge;
drop table product_color cascade constraint purge;
drop table product_common cascade constraint purge;
drop table with_product cascade constraint purge;

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

CREATE TABLE product_stock (
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

ALTER TABLE product_stock
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


insert into brand (bno, bname)
values (1,'TIME');

insert into depth1(depth1name) values ('we'); -- 여성
insert into depth2(depth2name) values ('we02');  -- 팬츠
insert into depth3(depth3name) values ('we021'); -- 캐쥬얼

insert into category(cateno, depth1name, depth2name, depth3name)
values (21,'we','we02','we021');

-- http://www.thehandsome.com/ko/HANDSOME/WOMEN/PANTS/CASUAL/%EB%B0%B1-%EB%B0%B4%EB%94%A9-%EC%A1%B0%EA%B1%B0-%ED%8C%AC%EC%B8%A0/p/TM2D1TPC424W_IV
-- time 브랜드 / 백 밴딩 조거 팬츠 /  61, 64 ,67
insert into product_common (pid, pname, pnote, pstatus, bno)
values ('TM2D1TPC424W', '백 밴딩 조거 팬츠', '상단은 여유롭고 밑단으로 갈수록 점차 좁아지는 실루엣의 팬츠로 면 혼방 소재를 사용하여 구겨짐이 적고 탄탄한 아웃핏을 연출해 줍니다. 허리 라인에 블록된 드로우스트링으로 실루엣 조절이 용이하며, 백 밴딩으로 편안한 착용감을 선사합니다.', 1, 1);

insert into product_color (pcid, pcimg1, pcimg2, pcimg3, pcchipimg, pccolorcode, pcprice, preleasedate, pid)
values ('TM2D1TPC424W_IV', 'http://newmedia.thehandsome.com/TM/2D/SS/TM2D1TPC424W_IV_W01.jpg/dims/resize/684x1032', 'http://newmedia.thehandsome.com/TM/2D/SS/TM2D1TPC424W_IV_W01.jpg/dims/resize/684x1032','http://newmedia.thehandsome.com/TM/2D/SS/TM2D1TPC424W_IV_W01.jpg/dims/resize/684x1032','http://newmedia.thehandsome.com/TM/2D/SS/TM2D1TPC424W_IV_C01.jpg/dims/resize/24x24',
'IV', 345000, sysdate, 'TM2D1TPC424W');

insert into product_stock (psid, psstock, psize, pcid)
values ('TM2D1TPC424W_IV_61', '7', '61', 'TM2D1TPC424W_IV');
insert into product_stock (psid, psstock, psize, pcid)
values ('TM2D1TPC424W_IV_64', '8', '64', 'TM2D1TPC424W_IV');
insert into product_stock (psid, psstock, psize, pcid)
values ('TM2D1TPC424W_IV_67', '7', '67', 'TM2D1TPC424W_IV');

insert into product_category (cateno, pid)
values (21, 'TM2D1TPC424W');
----------------------------------


insert into brand (bno, bname)
values (7, 'SYSTEM HOMME');

insert into depth1(depth1name) values ('me'); -- 남성
insert into depth2(depth2name) values ('me01');  -- 아우터
insert into depth3(depth3name) values ('me015'); -- 가디건/베스트

insert into depth2(depth2name) values ('me10'); -- 니트
insert into depth3(depth3name) values ('me102'); -- 점퍼/가디건

insert into category(cateno, depth1name, depth2name, depth3name)
values (15,'me','me01','me015');
insert into category(cateno, depth1name, depth2name, depth3name)
values (102,'me','me10','me102');

-- http://www.thehandsome.com/ko/HANDSOME/MEN/OUTER/CARDIGAN-VEST/%EB%9D%BC%EC%BF%A4-%EB%B8%94%EB%A0%8C%EB%93%9C-%EB%8B%88%ED%8A%B8-%EA%B0%80%EB%94%94%EA%B1%B4/p/SH2CAKCD036M_BK
-- system hemme 브랜드 / 라쿤-블렌드-니트-가디건 / 95, 100 ,105
insert into product_common (pid, pname, pnote, pstatus, bno)
values ('SH2CAKCD036M', '라쿤 블렌드 니트 가디건', '부분적으로 대비되는 컬러를 믹스하여 포인트를 준 이 니트 가디건은 라쿤 혼방 소재로 뛰어난 퀄리티와 편안한 착용감을 선사합니다. 바디 라인을 따라 알맞게 흐르는 실루엣이 매력적이며, 밑단 양 옆에 슬릿을 블록하여 한층 활동성을 높여주었습니다.', 1, 7);

insert into product_category (cateno, pid)
values (15, 'SH2CAKCD036M');
insert into product_category (cateno, pid)
values (102, 'SH2CAKCD036M');


-- bk
insert into product_color (pcid, pcimg1, pcimg2, pcimg3, pcchipimg, pccolorcode, pcprice, preleasedate, pid)
values ('SH2CAKCD036M_BK', 'http://newmedia.thehandsome.com/SH/2C/FW/SH2CAKCD036M_BK_W01.jpg/dims/resize/684x1032','http://newmedia.thehandsome.com/SH/2C/FW/SH2CAKCD036M_BK_W01.jpg/dims/resize/684x1032','http://newmedia.thehandsome.com/SH/2C/FW/SH2CAKCD036M_BK_W01.jpg/dims/resize/684x1032','http://newmedia.thehandsome.com/SH/2C/FW/SH2CAKCD036M_BK_C01.jpg/dims/resize/24x24',
'BK', 435000, sysdate, 'SH2CAKCD036M');

insert into product_stock (psid, psstock, psize, pcid)
values ('SH2CAKCD036M_BK_95', '14', '95', 'SH2CAKCD036M_BK');
insert into product_stock (psid, psstock, psize, pcid)
values ('SH2CAKCD036M_BK_100', '20', '100', 'SH2CAKCD036M_BK');
insert into product_stock (psid, psstock, psize, pcid)
values ('SH2CAKCD036M_BK_105', '11', '105', 'SH2CAKCD036M_BK');

-- MB
insert into product_color (pcid, pcimg1, pcimg2, pcimg3, pcchipimg, pccolorcode, pcprice, preleasedate, pid)
values ('SH2CAKCD036M_MB', 'http://newmedia.thehandsome.com/SH/2C/FW/SH2CAKCD036M_MB_W01.jpg/dims/resize/684x1032','http://newmedia.thehandsome.com/SH/2C/FW/SH2CAKCD036M_MB_W01.jpg/dims/resize/684x1032','http://newmedia.thehandsome.com/SH/2C/FW/SH2CAKCD036M_MB_W01.jpg/dims/resize/684x1032','http://newmedia.thehandsome.com/SH/2C/FW/SH2CAKCD036M_MB_C01.jpg/dims/resize/24x24',
'MB', 435000, sysdate, 'SH2CAKCD036M');

insert into product_stock (psid, psstock, psize, pcid)
values ('SH2CAKCD036M_MB_95', '14', '95', 'SH2CAKCD036M_MB');
insert into product_stock (psid, psstock, psize, pcid)
values ('SH2CAKCD036M_MB_100', '20', '100', 'SH2CAKCD036M_MB');
insert into product_stock (psid, psstock, psize, pcid)
values ('SH2CAKCD036M_MB_105', '11', '105', 'SH2CAKCD036M_MB');
-- ������ Oracle SQL Developer Data Modeler 22.2.0.165.1149
--   ��ġ:        2023-01-31 12:29:23 KST
--   ����Ʈ:      Oracle Database 11g
--   ����:      Oracle Database 11g



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


CREATE TABLE PRODUCT_COLOR_INFO (
    pcid       VARCHAR2(25 BYTE) NOT NULL ,
    pImg       VARCHAR2(90 BYTE) NOT NULL,
    colorName  VARCHAR2(30 BYTE) NOT NULL
);

ALTER TABLE PRODUCT_COLOR_INFO
    ADD CONSTRAINT with_product_product_color_info_fk FOREIGN KEY ( pcid )
        REFERENCES product_color ( pcid )
            ON DELETE CASCADE;

ALTER TABLE PRODUCT_COLOR_INFO ADD CONSTRAINT product_color_info_pk PRIMARY KEY ( pcid );