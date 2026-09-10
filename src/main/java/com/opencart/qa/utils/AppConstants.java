package com.opencart.qa.utils;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final int SHORT_TIMEOUT=5;
	public static final int MEDIUM_TIMEOUT=10;
	public static final int LONG_TIMEOUT=15;
	public static final String LOGIN_PAGE_TITLE="Account Login";
	
	public static final String LOGIN_PAGE_URL="=account/login";
	
	public static final String HOME_PAGE_TITLE="My Account";
	
	public static final List<String> EXP_HEADERS_LIST= Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");

}
