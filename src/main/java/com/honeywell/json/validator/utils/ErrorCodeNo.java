package com.honeywell.json.validator.utils;


public enum ErrorCodeNo {
	// DB Level Exception Custom Error Code will start 100
		// Service Level Code start 500
		// API level code start from 300
		// other Exception or erro will 400
	  NoRecord(100), DUPLICATE_DATA(101), MicroServiceUnavailable(510), InsufficientParams(302), Unauthorized(401);
  private final int value;

	ErrorCodeNo(final int newValue) {
      value = newValue;
  }

  public int getValue() { return value; }
}
