/**
 * 
 */
package com.honeywell.json.validator.service;

import org.everit.json.schema.ValidationException;
import org.json.JSONObject;

/**
 * @author Praveen PS
 *
 */
public interface IJSONValidation {

	public JSONObject validateJson(JSONObject jsonObject,  String schemaId) throws ValidationException;
	
	public boolean createSchema(String schemaString, String schemaId);
}
