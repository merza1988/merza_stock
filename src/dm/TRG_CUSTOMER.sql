CREATE OR REPLACE TRIGGER TRG_CUSTOMER
BEFORE INSERT or UPDATE on CUSTOMER
FOR EACH ROW
DECLARE
BEGIN
  IF (:NEW.CREDITS < 0 ) THEN
      RAISE_APPLICATION_ERROR(-20999,'The credits has to be greater or equal to 0');      
  END IF;
   IF (sysdate <  :NEW.BIRTHDAY) THEN
     RAISE_APPLICATION_ERROR(-20968, 'The birthdate must be below the current date');
  END IF;  
  IF (UPDATING) THEN
          :NEW.BIRTHDAY := :OLD.BIRTHDAY;
           
  END IF;
  
END TRG_CUSTOMER;