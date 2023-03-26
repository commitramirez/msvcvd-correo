package mx.ee.pr.correo.enums;

public enum EnumHttpStatus {

	//The request could not be understood by the server due to malformed syntax.	
	CLIENT_ERROR_BAD_REQUEST (400, "Bad Request"),

	//The request could not be completed due to a conflict with the current state of the resource (as experienced in a version control system).
	CLIENT_ERROR_CONFLICT (409, "Conflict"),

	//The server understood the request, but is refusing to fulfill it as it could be explained in the entity.
	CLIENT_ERROR_FORBIDDEN (403,  "Forbidden"),

	//The requested resource is no longer available at the server and no forwarding address is known.
	CLIENT_ERROR_GONE (410, "Gone"),

	//The method specified in the Request-Line is not allowed for the resource identified by the Request-URI.
	CLIENT_ERROR_METHOD_NOT_ALLOWED (405, "Method Not Allowed"),

	// The server has not found anything matching the Request-URI or the server does not wish to reveal exactly why the request has been refused, or no other response is applicable.
	CLIENT_ERROR_NOT_FOUND (404, "Not Found"),

	//The request requires user authentication.
	CLIENT_ERROR_UNAUTHORIZED (401, "Unauthorized"),

	//This status code means the server understands the content type of the request entity (syntactically correct) but was unable to process the contained instructions.
	CLIENT_ERROR_UNPROCESSABLE_ENTITY (422, "Unprocessable Entity"),

	//The server, while acting as a gateway or proxy, could not connect to the upstream server.
	SERVER_ERROR_GATEWAY_TIMEOUT (504, "Gateway Timeout"),

	//The server encountered an unexpected condition which prevented it from fulfilling the request.
	SERVER_ERROR_INTERNAL (500, "Internal Server Error"),

	//The server does not support the functionality required to fulfill the request.
	SERVER_ERROR_NOT_IMPLEMENTED (501, "Not Implemented"), 

	//The server is currently unable to handle the request due to a temporary overloading or maintenance of the server.
	SERVER_ERROR_SERVICE_UNAVAILABLE (503, "Service Unavailable"), 


	//The request has been accepted for processing, but the processing has not been completed.
	SUCCESS_ACCEPTED (202, "Accepted"),

	//The request has been fulfilled and resulted in a new resource being created.
	SUCCESS_CREATED (201, "Created"),

	//The server has fulfilled the request but does not need to return an entity-body (for example after a DELETE), and might want to return updated meta-information.
	SUCCESS_NO_CONTENT (204, "No Content" ), 

	//The request has succeeded.
	SUCCESS_OK (200, "OK" ),

	//The server has fulfilled the partial GET request for the resource assuming the request has included a Range header field indicating the desired range.
	SUCCESS_PARTIAL_CONTENT (206, "Partial Content"),

	;

	private Integer code;
	private String description;

	EnumHttpStatus(Integer code, String description)
	{
		this.code = code;
		this.description = description;

	}

	public Integer getCode()
	{
		return this.code;
	}

	public String getDescription()
	{
		return this.description;
	}
}
