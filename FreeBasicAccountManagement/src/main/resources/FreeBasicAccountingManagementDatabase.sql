
CREATE TABLE freeacc_customer ( CUSTOMER_ID char(10) NOT NULL, CUSTOMER_NAME varchar(50), CUSTOMER_ADDRESS varchar(300), CUSTOMER_CONTRACT varchar(50), CUSTOMER_PHONE char(14), CUSTOMER_FAX char(14), CUSTOMER_EMAIL varchar(300), REMARK varchar(500), REGISTER_DATE datetime, LAST_CONTRACT_DATE datetime, CREATE_BY varchar(50), CREATE_DATE datetime, UPDATE_BY varchar(50), UPDATE_DATE datetime, IS_ACTIVE int, PRIMARY KEY (CUSTOMER_ID) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE freeacc_invoice_detail ( INVOICE_HEAD_ID char(10) NOT NULL, INVOICE_DETAIL_ID char(10) NOT NULL, ITEM_NO int, MATERIAL_ID char(10), PRICE_PER_UNIT double, UNIT_ID char(10), QUANTITY double, TOTAL_VALUE double, CREATE_BY varchar(50), CREATE_DATE datetime, UPDATE_BY varchar(50), UPDATE_DATE datetime, IS_ACTIVE int, PRIMARY KEY (INVOICE_HEAD_ID, INVOICE_DETAIL_ID) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE freeacc_invoice_head ( INVOICE_HEAD_ID char(10) NOT NULL, CUSTOMER_ID char(10), PO_NUMBER char(10), TOTAL_AMOUNT double, DISCOUNT double, VAT double, NET_AMOUNT double, NET_AMOUNT_TEXT varchar(300), PAYMENT_TYPE varchar(20), INVOICE_DUE_DATE datetime, PAYMENT_DUE_DATE datetime, CREATE_BY varchar(50), CREATE_DATE datetime, UPDATE_BY varchar(50), UPDATE_DATE datetime, IS_ACTIVE int, PRIMARY KEY (INVOICE_HEAD_ID) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE INDEX INVOICE_CUSTOMER ON freeacc_invoice_head (CUSTOMER_ID);

CREATE TABLE freeacc_material ( MATERIAL_ID char(10) NOT NULL, MATERIAL_NAME varchar(40), MATERIAL_DESC varchar(200), MATERIAL_PRICE double, UNIT_ID varchar(10), CREATE_BY varchar(50), CREATE_DATE datetime, UPDATE_BY varchar(50), UPDATE_DATE datetime, IS_ACTIVE int, PRIMARY KEY (MATERIAL_ID) ) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE INDEX FOREIGN_MATERIAL_UNIT ON freeacc_material (UNIT_ID);

CREATE TABLE freeacc_message ( MESSAGE_ID char(10) NOT NULL, MESSAGE_NAME char(20), MESSAGE_TEXT text, CREATE_BY varchar(50), CREATE_DATE datetime, UPDATE_BY varchar(50), UPDATE_DATE datetime, IS_ACTIVE int, PRIMARY KEY (MESSAGE_ID) ) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE freeacc_parameter ( PARAM_ID char(10) NOT NULL, PARAM_TYPE char(30) NOT NULL, PARAM_SEQ int(10) NOT NULL, TEXT1 varchar(40), TEXT2 varchar(80), TEXT3 text, NUMBER_1 int(10), NUMBER_2 double, IS_ACTIVE char(1), CREATE_BY varchar(50), CREATE_DATE datetime, UPDATE_BY varchar(50), UPDATE_DATE datetime, PRIMARY KEY (PARAM_ID, PARAM_TYPE, PARAM_SEQ) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE freeacc_quotation_detail ( QUOTATION_HEAD_ID char(10) NOT NULL, QUOTATION_DETAIL_ID char(10) NOT NULL, ITEM_NO int, MATERIAL_ID char(10), QUANTITY double, UNIT_ID char(10), PRICE_PER_UNIT double, TOTAL_PRICE double, CREATE_BY varchar(50), CREATE_DATE datetime, UPDATE_BY varchar(50), UPDATE_DATE datetime, IS_ACTIVE int, PRIMARY KEY (QUOTATION_HEAD_ID, QUOTATION_DETAIL_ID) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE freeacc_quotation_head ( QUOTATION_HEAD_ID char(10) NOT NULL, QUOTATION_TITLE varchar(200), CUSTOMER_ID char(10), QUOTATION_DATE datetime, DELIVERY_PLACE varchar(200), LEAD_DATE datetime, PAYMENT_TERM varchar(50), TOTAL_VALUE double, VAT double, NET_VALUE double, NET_VALUE_TEXT varchar(300), CREATE_BY varchar(50), CREATE_DATE datetime, UPDATE_BY varchar(50), UPDATE_DATE datetime, IS_ACTIVE int, PRIMARY KEY (QUOTATION_HEAD_ID) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE INDEX QUOTATION_CUSTOMER ON freeacc_quotation_head (CUSTOMER_ID);

CREATE TABLE freeacc_sequence ( SEQUENCE_ID char(10) NOT NULL, SEQUENCE_NAME char(10), SEQUENCE_CURR_VALUE char(10), SEQUENCE_CURR_NO int, PRIMARY KEY (SEQUENCE_ID) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE freeacc_unit ( UNIT_ID char(10) NOT NULL, UNIT_NAME varchar(10), UNIT_DESC varchar(50), CREATE_BY varchar(50), CREATE_DATE datetime, UPDATE_BY varchar(50), UPDATE_DATE datetime, IS_ACTIVE int, PRIMARY KEY (UNIT_ID) ) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE freeacc_user_info ( USER_ID char(50) NOT NULL, PASSWORD blob NOT NULL, FIRST_NAME varchar(50), LAST_NAME varchar(50), CREATE_BY varchar(50), CREATE_DATE datetime, UPDATE_BY varchar(50), UPDATE_DATE datetime, IS_ACTIVE int, PRIMARY KEY (USER_ID) ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
ALTER TABLE freeacc_invoice_detail ADD CONSTRAINT INVOICE_DETAIL_HEAD FOREIGN KEY (INVOICE_HEAD_ID) REFERENCES freeacc_invoice_head (INVOICE_HEAD_ID);
ALTER TABLE freeacc_invoice_head ADD CONSTRAINT INVOICE_CUSTOMER FOREIGN KEY (CUSTOMER_ID) REFERENCES freeacc_customer (CUSTOMER_ID);
ALTER TABLE freeacc_quotation_detail ADD CONSTRAINT QUOTATION_DETAIL_HEAD FOREIGN KEY (QUOTATION_HEAD_ID) REFERENCES freeacc_quotation_head (QUOTATION_HEAD_ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE freeacc_quotation_head ADD CONSTRAINT QUOTATION_CUSTOMER FOREIGN KEY (CUSTOMER_ID) REFERENCES freeacc_customer (CUSTOMER_ID) ON DELETE NO ACTION ON UPDATE NO ACTION;