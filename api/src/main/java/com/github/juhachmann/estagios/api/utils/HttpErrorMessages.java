package com.github.juhachmann.estagios.api.utils;

public class HttpErrorMessages {

	private static final String BAD_REQUEST = "Invalid data was sent in the request.";
	private static final String UNAUTHORIZED = "Authentication failed. You must be correctly authenticated to access this resource.";
	private static final String TOO_MANY_REQUESTS = "Exceeded the rate limit for requests.";
	private static final String FORBIDDEN = "Authorization failed. Looks like you don't have access to this resource.";
	private static final String NOT_FOUND = "Resource not found or not available for you.";
	
	public static final String BAD_REQUEST_MSG = "{\"code\":400,\"message\": \"" + BAD_REQUEST + "\"}";
	public static final String UNAUTHORIZED_MSG = "{\"code\":401,\"message\": \"" + UNAUTHORIZED + "\"}";
	public static final String TOO_MANY_REQUESTS_MSG = "{\"code\":429,\"message\": \"" + TOO_MANY_REQUESTS + "\"}";
	public static final String FORBIDDEN_MSG = "{\"code\":403,\"message\": \"" + FORBIDDEN + "\"}";
	public static final String NOT_FOUND_MSG = "{\"code\":404,\"message\": \"" + NOT_FOUND + "\"}";
	
}
