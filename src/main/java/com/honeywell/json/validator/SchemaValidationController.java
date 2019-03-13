package com.honeywell.json.validator;

import java.util.concurrent.atomic.AtomicLong;

import org.everit.json.schema.ValidationException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.honeywell.json.validator.service.JsonValidationImpl;
import com.honeywell.json.validator.utils.GenericResponseEntity;

/**
 * This is the Controller class. Responsible to keep all the endpoints here.
 * 
 * @author Praveen PS
 *
 */
@RestController
public class SchemaValidationController {

	@Autowired
	JsonValidationImpl jsonValidationImpl;

	/**
	 * This method validates the json object as per the schema. Schema ID will be
	 * obtained from the path param
	 * 
	 * @param jsonObject
	 * @param schemaId
	 * @return
	 */
	@PostMapping(path = "/jsonvalidation/{schema_id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<JSONObject> validateSchema(@RequestBody String jsonString,
			@PathVariable("schema_id") String schemaId) {

		/**
		 * Do the validation here.
		 * 
		 * String conversion to the corresponding JSONObject.
		 * 
		 * As per the response, create the Generic Response object and send it back to
		 * the caller
		 */

		JSONObject obj = new JSONObject(jsonString);
		JSONObject objResponse;
		try {
			objResponse = jsonValidationImpl.validateJson(obj, schemaId);

		} catch (ValidationException e) {
			GenericResponseEntity<String> response = new GenericResponseEntity<String>(e.getMessage(), 400);
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		}
		GenericResponseEntity<String> response = new GenericResponseEntity<String>("Valid", 200);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	/**
	 * This method is responsible for the creation of a new Schema object in the
	 * System. The schema name will be taken from the path param and the file will
	 * be created.
	 * 
	 * @param schemaString
	 * @param schemaId
	 * @return
	 */
	@PostMapping(path = "/createSchema/{schema_id}")
	public ResponseEntity<JSONObject> createSchema(@RequestBody String schemaString,
			@PathVariable("schema_id") String schemaId) {

		boolean isCreated = jsonValidationImpl.createSchema(schemaString, schemaId);
		GenericResponseEntity<String> response = new GenericResponseEntity<String>(String.valueOf(isCreated), 200);

		return new ResponseEntity(response, HttpStatus.OK);
	}
}
