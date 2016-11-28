-- MySQL Workbench Forward Engineering


-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema dms
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dms
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dms` ;
USE `dms` ;

-- -----------------------------------------------------
-- Table `dms`.`organization`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dms`.`organization` (
  `ORGNIZATION_ID` BIGINT(10) NOT NULL COMMENT 'This table maintains the dace information of organization / Company / Individual',
  `ORG_NAME` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `ORG_TYPE` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `USER_ID` BIGINT(10) NOT NULL COMMENT '',
  PRIMARY KEY (`ORGNIZATION_ID`)  COMMENT '',
  INDEX `fk_ORGANIZATION_USERS` (`USER_ID` ASC)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dms`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dms`.`product` (
  `PRODUCT_ID` BIGINT(10) NOT NULL COMMENT 'Primary Key',
  `PRODUCT_NAME` VARCHAR(45) NOT NULL COMMENT 'Product Name ',
  `DESCRIPTION` VARCHAR(100) NULL DEFAULT NULL COMMENT 'Product Description',
  `PRODUCT_CODE` VARCHAR(100) NULL DEFAULT NULL COMMENT 'This column can be used to maintain the products uniqued id main by Organization in their system.\n\nThis can be numeric, alphanumeric , alphabets in said organization, we need to decide , how we can use it effectively',
  `ORGNIZATION_ID` BIGINT(10) NOT NULL COMMENT '',
  PRIMARY KEY (`PRODUCT_ID`)  COMMENT '',
  INDEX `fk_PRODUCT_ORGANIZATION1` (`ORGNIZATION_ID` ASC)  COMMENT '',
  CONSTRAINT `fk_PRODUCT_ORGANIZATION1`
    FOREIGN KEY (`ORGNIZATION_ID`)
    REFERENCES `dms`.`organization` (`ORGNIZATION_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dms`.`product_doc_conf`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dms`.`product_doc_conf` (
  `PR_DOC_CONF_ID` BIGINT(10) NOT NULL COMMENT 'Primary Key',
  `PRODUCT_ID` BIGINT(10) NULL DEFAULT NULL COMMENT 'References to Product Table ',
  `DOC_TYPE_CODE` VARCHAR(10) NULL DEFAULT NULL COMMENT 'Document type code, references to document type, Example : PDF, VIDEO, IMAGE, EXCEL and So',
  `DESCRIPTION` VARCHAR(45) NULL DEFAULT NULL COMMENT 'Description of this document type',
  `IS_MANDATORY` TINYINT(1) NULL DEFAULT NULL COMMENT 'determines if this documendatory for this type of product configuration',
  `GROUP_ID` INT(11) NULL DEFAULT NULL COMMENT 'Not decided use of this column, but idea is to maintain the group of documents for given product configuration',
  `IS_MULTIPLE_ITEM_ALLOWED` TINYINT(1) NULL DEFAULT NULL COMMENT 'To See for this configuration , multiple columns allowed, \nNeed to see how this can be used',
  PRIMARY KEY (`PR_DOC_CONF_ID`)  COMMENT '',
  INDEX `fk_PRODUCT_DOC_CONF_PRODUCT1_idx` (`PRODUCT_ID` ASC)  COMMENT '',
  INDEX `fk_PRODUCT_DOC_CONF_PRODUCT_DOC_TYPE1_idx` (`DOC_TYPE_CODE` ASC)  COMMENT '',
  CONSTRAINT `fk_PRODUCT_DOC_CONF_PRODUCT1`
    FOREIGN KEY (`PRODUCT_ID`)
    REFERENCES `dms`.`product` (`PRODUCT_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'This table maintains the documents configurations for the gi';


-- -----------------------------------------------------
-- Table `dms`.`product_doc_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dms`.`product_doc_type` (
  `PRODUCT_DOC_TYPE_CODE` VARCHAR(10) NOT NULL COMMENT 'This code determines document type, such as PDF, IMAGE, VIDEO and So on',
  `DESCRIPTION` VARCHAR(45) NULL DEFAULT NULL COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Product Document types, This can be used as lookup Table ';


-- -----------------------------------------------------
-- Table `dms`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dms`.`users` (
  `LOGIN_ID` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `user_id` BIGINT(10) NOT NULL AUTO_INCREMENT COMMENT '',
  `PASSWORD` VARCHAR(10) NULL DEFAULT NULL COMMENT '',
  `EMAIL_ID` VARCHAR(70) NOT NULL COMMENT '',
  `ORG_NAME` VARCHAR(50) NULL DEFAULT NULL COMMENT '',
  `FIRST_NAME` VARCHAR(50) NULL DEFAULT NULL COMMENT '',
  `LAST_NAME` VARCHAR(50) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`user_id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;

