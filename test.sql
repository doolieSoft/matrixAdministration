BEGIN TRANSACTION;
CREATE TABLE element (chemin TEXT, element_id INTEGER PRIMARY KEY, nom TEXT, date TEXT, type TEXT, casier TEXT);
INSERT INTO element VALUES('',2,'crapanzano fabrice','2010/10/23','oeil de','cxxx');
INSERT INTO element VALUES('/home/fabrice/javadoc/docs/index.html',3,'feyereisen emilie','2011/12/12','oeil m','cx');
INSERT INTO element VALUES('',4,'crapanzano fabrice','2010/10/23','p','cxxx');
INSERT INTO element VALUES('/home/fabrice/netbeans-6.9.1/identity/VERSION.txt',5,'feyereisen émilie','2010/12/23','poils','cxxx');
COMMIT;
