/*
* create database hyb default character set utf8 DEFAULT COLLATE utf8_general_ci;
* SET storage_engine=INNODB;
* create user 'hyb'@'%' identified by 'hyb01';
* grant all privileges on *.* to 'hyb'@'%';
* grant all on hyb.* to hyb@'localhost';
* grant all on hyb.* to hyb@'%';
* update user set password=password('hyb01') where User='hyb';
* commit;
* flush privileges;
*/

CREATE TABLE COMTECOPSEQ
(
	TABLE_NAME            VARCHAR(20) NULL,
	NEXT_ID               NUMERIC(30) NULL,
	 PRIMARY KEY (TABLE_NAME)
)
;

CREATE TABLE VIBRATOR
(
	SN                    NUMERIC(6) NOT NULL,
	UUID                  VARCHAR(50) NOT NULL,
	TIMESTAMP             VARCHAR(20) NULL,
	 PRIMARY KEY (SN,UUID)
)
;



CREATE TABLE ACCELERATOR
(
	SN                    NUMERIC(6) NOT NULL,
	UUID                  VARCHAR(50) NOT NULL,
	XAXIS                 VARCHAR(20) NULL,
	YAXIS                 VARCHAR(20) NULL,
	ZAXIS                 VARCHAR(20) NULL,
	TIMESTAMP             VARCHAR(20) NULL,
	USEYN                 CHAR(1) NULL,
	 PRIMARY KEY (SN,UUID)
)
;



CREATE TABLE CONTACT_INFO
(
	ID                    CHAR(20) NOT NULL,
	NAME                  CHAR(60) NULL,
	TELNO                 CHAR(20) NULL,
	EMAILS                VARCHAR(50) NULL,
	USEYN                 CHAR(1) NULL,
	UUID                  VARCHAR(50) NOT NULL,
	 PRIMARY KEY (UUID,ID)
)
;



CREATE TABLE FILE_DETAIL_INFO
(
	FILE_SN               NUMERIC(20) NOT NULL,
	FILE_STRE_COURS       VARCHAR(2000) NULL,
	STRE_FILE_NM          VARCHAR(255) NULL,
	ORIGNL_FILE_NM        VARCHAR(255) NULL,
	FILE_EXTSN            VARCHAR(20) NULL,
	FILE_CN               BLOB NULL,
	FILE_SIZE             NUMERIC(8) NULL,
	 PRIMARY KEY (FILE_SN)
)
;



CREATE TABLE FILE_READER_WRITE
(
	SN                    NUMERIC(6) NOT NULL,
	UUID                  VARCHAR(50) NOT NULL,
	FILE_NM               VARCHAR(255) NULL,
	FILE_TYPE             VARCHAR(40) NULL,
	UPDT_DT               DATE NULL,
	USEYN                 CHAR(1) NULL,
	FILE_SN               NUMERIC(20) NOT NULL,
	FILE_COURS            VARCHAR(2000) NULL,
	FILE_SIZE             VARCHAR(40) NULL,
	 PRIMARY KEY (SN,UUID),
	FOREIGN KEY FILE_READER_WRITE_FK1 (FILE_SN) REFERENCES FILE_DETAIL_INFO(FILE_SN)
)
;



CREATE TABLE MEDIA
(
	SN                    NUMERIC(6) NOT NULL,
	UUID                  VARCHAR(50) NULL,
	MD_CODE               VARCHAR(40) NULL,
	MD_SJ                 VARCHAR(255) NULL,
	USEYN                 CHAR(1) NULL,
	REVIV_CO              VARCHAR(40) NULL,
	FILE_SN               NUMERIC(20) NOT NULL,
	 PRIMARY KEY (SN, UUID),
	FOREIGN KEY MEDIA_FK1 (FILE_SN) REFERENCES FILE_DETAIL_INFO(FILE_SN)
)
;



CREATE TABLE DEVICE
(
	SN                    NUMERIC(6) NOT NULL,
	UUID                  VARCHAR(50) NOT NULL,
	TELNO                 VARCHAR(15) NULL,
	STRGE_INFO            VARCHAR(40) NULL,
	NTWRK_DEVICE_INFO     VARCHAR(20) NULL,
	PG_VER                VARCHAR(40) NULL,
	DEVICE_NM             VARCHAR(200) NULL,
	USEYN                 CHAR(1) NULL,
	OS                  VARCHAR(15) NULL,
	 PRIMARY KEY (SN,UUID)
)
;



CREATE TABLE INTERFACE_EGOV
(
	SN                    NUMERIC(6) NOT NULL,
	UUID                  VARCHAR(50) NOT NULL,
	EMAILS                VARCHAR(50) NULL,
	USER_ID               VARCHAR(20) NULL,
	USER_PW               VARCHAR(2000) NULL,
	 PRIMARY KEY (SN,UUID)
)
;



CREATE TABLE COMPASS
(
	SN                    NUMERIC(6) NOT NULL,
	UUID                  VARCHAR(50) NOT NULL,
	DRC                   VARCHAR(40) NULL,
	ACCRCY                VARCHAR(40) NULL,
	USEYN                 CHAR(1) NULL,
	TIMESTAMP             VARCHAR(20) NULL,
	 PRIMARY KEY (SN,UUID)
)
;



CREATE TABLE GPS
(
	SN                    NUMERIC(6) NOT NULL,
	UUID                  VARCHAR(50) NOT NULL,
	LA                    VARCHAR(48) NULL,
	LO                    VARCHAR(48) NULL,
	ACCRCY                VARCHAR(10) NULL,
	USEYN                 CHAR(1) NULL,
	 PRIMARY KEY (SN,UUID)
)
;



CREATE TABLE CAMERA
(
	SN                    NUMERIC(6) NOT NULL,
	UUID                  VARCHAR(50) NOT NULL,
	PHOTO_SJ              VARCHAR(255) NULL,
	USEYN                 CHAR(1) NULL,
	FILE_SN               NUMERIC(20) NOT NULL,
	 PRIMARY KEY (SN,UUID),
	FOREIGN KEY CAMERA_FK1 (FILE_SN) REFERENCES FILE_DETAIL_INFO(FILE_SN)
)
;



CREATE TABLE NETWORK
(
	SN                    NUMERIC(6) NOT NULL,
	UUID                  VARCHAR(50) NOT NULL,
	NTWRK_TYPE            VARCHAR(20) NULL,
	USEYN                 CHAR(1) NULL,
	 PRIMARY KEY (SN,UUID)
)
;



CREATE TABLE PKI
(
	SN                    NUMERIC(6) NOT NULL,
	UUID                  VARCHAR(50) NOT NULL,
	CRTFC_DT              DATE NULL,
	ENTRPRS_SE_CODE       CHAR(15) NULL,
	DN                    VARCHAR(255) NULL,
	 PRIMARY KEY (SN,UUID)
)
;



CREATE TABLE CODE
(
	CODE                  CHAR(3) NOT NULL,
	CODE_ID               VARCHAR(6) NOT NULL,
	CODE_NM               VARCHAR(60) NULL,
	CODE_DC               VARCHAR(200) NULL,
	 PRIMARY KEY (CODE,CODE_ID)
)
;


