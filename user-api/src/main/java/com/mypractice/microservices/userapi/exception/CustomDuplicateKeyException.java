/**
 * nasru - Jun 22, 2020
 * CustomDuplicateKeyException.java 
 */
package com.mypractice.microservices.userapi.exception;

import org.bson.BsonDocument;

import com.mongodb.DuplicateKeyException;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcernResult;

/**
 * @author nasru
 *
 */
public class CustomDuplicateKeyException extends DuplicateKeyException {

	/**
	 * @param response
	 * @param address
	 * @param writeConcernResult
	 */
	public CustomDuplicateKeyException(BsonDocument response, ServerAddress address,
			WriteConcernResult writeConcernResult) {
		super(response, address, writeConcernResult);
		// TODO Auto-generated constructor stub
		System.out.println("Exception called");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8111846879366559952L;

	

}
