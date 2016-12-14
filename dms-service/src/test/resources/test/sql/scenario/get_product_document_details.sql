INSERT INTO users (LOGIN_ID,user_id,PASSWORD,EMAIL_ID,ORG_NAME,FIRST_NAME,LAST_NAME) VALUES ('usarname1',2000001,'test','mailtosmj@REDIFFMAILCOM.com','Org1','User1','Surname');
INSERT INTO organization (ORGANIZATION_ID,ORG_NAME,ORG_TYPE,USER_ID) VALUES (1000001,'Test Organization Name','Used car dealer',2000001);

INSERT INTO product (product_id,PRODUCT_NAME,DESCRIPTION,PRODUCT_CODE,ORGANIZATION_ID) VALUES (10,'Sedan Car','Sedan Car','SEDAN_CAR_CODE',1000001);
INSERT INTO product (product_id,PRODUCT_NAME,DESCRIPTION,PRODUCT_CODE,ORGANIZATION_ID) VALUES (11,'Sports Car','Sports Car','SPORTS_CAR_CODE',1000001);

INSERT INTO product_doc_conf (PR_DOC_CONF_ID,PRODUCT_ID,DOC_TYPE_CODE,DESCRIPTION,IS_MANDATORY,GROUP_ID,IS_MULTIPLE_ITEM_ALLOWED) VALUES (300003,10,'RIGHTIMAGE','Vehicle Right side Image',true,0,false);
INSERT INTO product_doc_conf (PR_DOC_CONF_ID,PRODUCT_ID,DOC_TYPE_CODE,DESCRIPTION,IS_MANDATORY,GROUP_ID,IS_MULTIPLE_ITEM_ALLOWED) VALUES (300004,10,'LEFTIMAGE','Vehicle Left side Image',true,0,false);
INSERT INTO product_doc_conf (PR_DOC_CONF_ID,PRODUCT_ID,DOC_TYPE_CODE,DESCRIPTION,IS_MANDATORY,GROUP_ID,IS_MULTIPLE_ITEM_ALLOWED) VALUES (300005,10,'FRONTIMAGE','Vehicle Front side Image',true,0,false);
INSERT INTO product_doc_conf (PR_DOC_CONF_ID,PRODUCT_ID,DOC_TYPE_CODE,DESCRIPTION,IS_MANDATORY,GROUP_ID,IS_MULTIPLE_ITEM_ALLOWED) VALUES (300006,10,'BACKIMAGE','Vehicle Back side Image',true,0,false);


INSERT INTO product_doc_details (product_doc_detail_id,product_id,product_doc_conf_id,doc_url) VALUES (80008,10,300003,'http://devdmsproducts01.s3.amazonaws.com/dev/maruti-suzuki-swift-image-9925.jpg');
INSERT INTO product_doc_details (product_doc_detail_id,product_id,product_doc_conf_id,doc_url) VALUES (80009,10,300004,'http://devdmsproducts01.s3.amazonaws.com/dev/maruti-suzuki-swift-image-9925.jpg');
INSERT INTO product_doc_details (product_doc_detail_id,product_id,product_doc_conf_id,doc_url) VALUES (80010,10,300005,'http://devdmsproducts01.s3.amazonaws.com/dev/maruti-suzuki-swift-image-9925.jpg');