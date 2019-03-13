/**
 * 
 */
package com.honeywell.json.validator.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;

/**
 * @author Praveen PS
 *
 */
@Component
public class JsonValidationImpl implements IJSONValidation {

	/**
	 * Service layer code for validation of a JSON Object. This will be passed
	 * through the validate method where the saved json schema will be used as a
	 * reference.
	 */
	@Override
	public JSONObject validateJson(JSONObject jsonObject, String schemaId) throws ValidationException {
		InputStream inputStream = JsonValidationImpl.class.getResourceAsStream("/schemas/" + schemaId + ".json");
		if (inputStream != null) {
			JSONObject jsonObject1 = new JSONObject(((JSONObject) jsonObject).toMap());
			JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
			Schema schema = SchemaLoader.load(rawSchema);
			schema.validate(jsonObject1);

		}
		return jsonObject;
	}

	/**
	 * Here we will accept the schema string and will just create a file in the name
	 * which we got in the path variable
	 */
	@Override
	public boolean createSchema(String schemaString, String schemaId) {

		OutputStream os = null;
		try {
			/**
			 * TO-DO .... Unable to make it work with the relative path. Have to look
			 */
			os = new FileOutputStream(new File(
					"D:\\personal development\\honeywell\\gs-rest-service-master\\complete\\src\\main\\resources\\schemas\\"
							+ schemaId + ".json"));
			os.write(schemaString.getBytes(), 0, schemaString.length());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

}
