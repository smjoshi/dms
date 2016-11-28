
-- Create table scripts  for H2



-- Users Table
CREATE TABLE users
(
   LOGIN_ID varchar(45),
   USER_ID bigint PRIMARY KEY auto_increment NOT NULL,
   PASSWORD varchar(10),
   EMAIL_ID varchar(70) NOT NULL,
   ORG_NAME varchar(50),
   FIRST_NAME varchar(50),
   LAST_NAME varchar(50)
);
CREATE INDEX PK_USERS ON users(user_id);


-- Create Organization table
CREATE TABLE organization
(
   ORGANIZATION_ID bigint PRIMARY KEY auto_increment NOT NULL,
   ORG_NAME varchar(45),
   ORG_TYPE varchar(45),
   USER_ID bigint NOT NULL
);

CREATE INDEX fk_ORGANIZATION_USERS ON organization(USER_ID);
CREATE INDEX PR_ORG_idx ON organization(ORGANIZATION_ID);


-- create Product Table
CREATE TABLE product
(
   PRODUCT_ID bigint PRIMARY KEY auto_increment NOT NULL,
   PRODUCT_NAME varchar(45) NOT NULL,
   DESCRIPTION varchar(100),
   PRODUCT_CODE varchar(100),
   ORGANIZATION_ID bigint NOT NULL
);
CREATE INDEX PK_PRODUCT_idx ON product(product_id);
CREATE INDEX fk_PRODUCT_ORGANIZATION1 ON product(ORGANIZATION_ID);

-- create product document types

CREATE TABLE product_doc_type
(
   PRODUCT_DOC_TYPE_CODE varchar(10) NOT NULL,
   DESCRIPTION varchar(45)
);


-- create product documents configuration
CREATE TABLE product_doc_conf
(
   PR_DOC_CONF_ID bigint PRIMARY KEY auto_increment NOT NULL,
   PRODUCT_ID bigint,
   DOC_TYPE_CODE varchar(10),
   DESCRIPTION varchar(45),
   IS_MANDATORY bit,
   GROUP_ID int,
   IS_MULTIPLE_ITEM_ALLOWED bit
);
CREATE INDEX fk_PRODUCT_DOC_CONF_PRODUCT_DOC_TYPE1_idx ON product_doc_conf(DOC_TYPE_CODE);
CREATE INDEX fk_PRODUCT_DOC_CONF_PRODUCT1_idx ON product_doc_conf(PRODUCT_ID);
CREATE INDEX PK_prod_doc_conf_idx ON product_doc_conf(PR_DOC_CONF_ID);

-- Product doc details
CREATE TABLE product_doc_details
(
   product_doc_detail_id bigint PRIMARY KEY auto_increment NOT NULL,
   product_id bigint NOT NULL,
   product_doc_conf_id bigint NOT NULL,
   doc_url varchar(100)
);
CREATE INDEX PRI_product_doc_details_idx ON product_doc_details(product_doc_detail_id);