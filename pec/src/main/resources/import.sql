INSERT INTO "USERS" (ID, FIRST_NAME,LAST_NAME,IDARUBA,EMAIL,PRIVATE_KEY) VALUES(101,'Mario','Rossi','b46535df-50b5-4353-b9ca-31ffd812139e','mario.rossi@gmail.com','/mario_rossi/privateKey/keystore.p12');
INSERT INTO "PEC" VALUES(1001,'test01@pec.it',101);
INSERT INTO "PEC" VALUES(1002,'test02@pec.it',101);
INSERT INTO "PEC" VALUES(1003,'test03@pec.it',101);
INSERT INTO "PEC" VALUES(1004,'test04@pec.it',101);
INSERT INTO "MESSAGES" (ID,SENDER,SUBJECT,TEXT,PRIORITY,ATTACHMENT,PEC_ID) VALUES(1,'tizio.caio@gmail.it','oggetto01','tutto ok!','alta','/mario_rossi/test_allegato.pdf',1001);
INSERT INTO "MESSAGES" (ID,SENDER,SUBJECT,TEXT,PRIORITY,ATTACHMENT,PEC_ID) VALUES(2,'tizio.caio@gmail.it','oggetto02','siamo arrivati!','alta','/mario_rossi/test_allegato.pdf',1001);
INSERT INTO "MESSAGES" (ID,SENDER,SUBJECT,TEXT,PRIORITY,ATTACHMENT,PEC_ID) VALUES(3,'tizio.caio@gmail.it','oggetto03','come deciso per le vie brevi!','bassa','/mario_rossi/test_allegato.pdf',1001);
INSERT INTO "MESSAGES" (ID,SENDER,SUBJECT,TEXT,PRIORITY,ATTACHMENT,PEC_ID) VALUES(4,'tizio.caio@gmail.it','oggetto04','ciao come stai!','bassa','/mario_rossi/test_allegato.pdf',1001);
INSERT INTO "MESSAGES" (ID,SENDER,SUBJECT,TEXT,PRIORITY,ATTACHMENT,PEC_ID) VALUES(5,'tizio.caio@gmail.it','oggetto04','vediamo domani!','bassa','/mario_rossi/test_allegato.pdf',1001);
INSERT INTO "MESSAGES" (ID,SENDER,SUBJECT,TEXT,PRIORITY,ATTACHMENT,PEC_ID) VALUES(6,'tizio.caio@gmail.it','oggetto04','email?','bassa','/mario_rossi/test_allegato.pdf',1001);
INSERT INTO "MESSAGES" (ID,SENDER,SUBJECT,TEXT,PRIORITY,ATTACHMENT,PEC_ID) VALUES(7,'tizio.caio@gmail.it','oggetto04','ci vediamo domani!','alta','/mario_rossi/test_allegato.pdf',1001);
INSERT INTO "MESSAGES" (ID,SENDER,SUBJECT,TEXT,PRIORITY,ATTACHMENT,PEC_ID) VALUES(8,'tizio.caio@gmail.it','oggetto05','speriamo vada bene!','alta','/mario_rossi/test_allegato.pdf',1001);
INSERT INTO "MESSAGES" (ID,SENDER,SUBJECT,TEXT,PRIORITY,ATTACHMENT,PEC_ID) VALUES(9,'tizio.caio@gmail.it','oggetto06','speriamo vada bene!','alta',null,1001);