/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zindigi.account_migration.config.app;


import com.mfs.commonservice.dto.TokenData;

/**
 * Constants For Rule Engine
 *
 * @author Shahzad Sadiq
 * @since 29/09/2023
 */

public class ConstantsApp {

    private ConstantsApp(){

    }

    public static final String  API_DOWN_CODE = "9999";
    public static final String SALT = "ThisIsTestSaltValue";
    public static final String ENDPOINT_DOMAIN_LIST = "/list";

    public static final String MC_APPROVED = "A";

    public static final String MC_APPROVED_CODE = "2";
    public static final String ENDPOINT_DICTIONARY_LIST = "/view";

    public static final String ENDPOINT_DICTIONARY_TABLES_LIST = "/column/view";
    public static final String ENDPOINT_DOMAIN_ONE = "/";

    public static final String ENDPOINT_DOMAIN_ONE_MAKER_CHECKER = "/checker";

    public static final String ENDPOINT_RULE_ONE_MAKER_CHECKER = "/checker";

    public static final String ENDPOINT_DOMAIN_SAVE = "/create";
    public static final String ENDPOINT_DOMAIN_UPDATE = "/update";

    public static final String ENDPOINT_DOMAIN_INACTIVE = "/inactive";


    public static final String ENDPOINT_DEVICE_KEYPAIR = "/keypair";
    public static final String LOGIN = "/login";

    public static final String ENDPOINT_RULE_EXECUTE_BY_CODE = "/executebycode";

    public static final String ENDPOINT_RULE_ONE = "/";
    public static final String ENDPOINT_RULE_FIELD_ONE = "/";
    public static final String ENDPOINT_RULE_FIELD_DROPDOWN = "/lookup";

    public static final String ENDPOINT_RULE_SAVE = "/create";
    public static final String ENDPOINT_RULE_FIELD_SAVE = "/savedynamicrulesmodels";

    public static final String ENDPOINT_RULE_UPDATE = "/update";
    public static final String ENDPOINT_RULE_FIELD_UPDATE = "/updatedynamicrulesmodels";

    public static final String ENDPOINT_RULE_UPDATE_INACTIVE = "/activatedynamicrulesmodels";

    public static final String ENDPOINT_RULE_LIST = "/list";
    public static final String ENDPOINT_RULE_FIELD_LIST = "/getdynamicrulesmodels";

    public static final String  DICTIONARY_TABLE_VIEW_LOG = "Dictionary Table View!";

    public static final String  DICTIONARY_TABLE_COLUMNS_VIEW_LOG = "Dictionary Colums View!";




    public static final String VALID_YES = "1";
    public static final String VALID_NO = "0";


    public static final String DATATYPE_NUMBER = "NUM";
    public static final String DATATYPE_STRING = "STR";
    public static final String DATATYPE_DATE = "DAT";
    public static final String TIMESTAMP = "TIM";
    public static final String TEXT_DOUBLE_LEFT_BRACES = "\\{\\{";
    public static final String TEXT_DOUBLE_RIGHT_BRACES = "\\}\\}";

    public static final String TEXT_DOUBLE_LEFT_BRACKETS = "\\[\\[";
    public static final String TEXT_DOUBLE_RIGHT_BRACKETS = "\\]\\]";

    public static final String DELIMITER_DATATYPE = "#@#";
    public static final String DOUBLE_LEFT_BRACES_WITHOUT_FORWARD_SLASH = "{{";
    public static final String DOUBLE_RIGHT_BRACES_WITHOUT_FORWARD_SLASH = "}}";

    public static final String DOUBLE_LEFT_BRACKETS_WITHOUT_FORWARD_SLASH = "[[";
    public static final String DOUBLE_RIGHT_BRACKETS_WITHOUT_FORWARD_SLASH = "]]";

    public static final String NODE_ROOT_NAME = "NR";
    public static final String TABLE_NAME_DOMAIN = "TBL_DOMAIN";
    public static final String FORM_NAME_DOMAIN_ADD = "Domain - Add";

    public static final String FORM_NAME_DOMAIN_UPDATE = "Domain - Edit";
    public static final String REQUEST_TYPE_SAVE = "I";
    public static final String EMPTY = "";

    public static final String S_1 = "*";
    public static final String DOMAIN_CHECKER_ACTION = "/domainCheckerAction";
    public static final String TABLE_NAME_RULE = "TBL_RULE";
    public static final String FORM_NAME_RULE_ADD = "Rule - Add";

    public static final String FORM_NAME_RULE_UPDATE = "Rule - Edit";
    public static final String RULE_CHECKER_ACTION = "/ruleCheckerAction";
    public static final String RULE_FIELD_CHECKER_ACTION = "/ruleFieldCheckerAction";

    public static final String TABLE_NAME_RULE_FIELD = "TBL_RULE_FIELD";
    public static final String FORM_NAME_RULE_FIELD_ADD = "RuleField - Add";

    public static final String FORM_NAME_RULE_FIELD_UPDATE = "RuleField - Edit";


    public static final String REQUEST_MAPPING = "/v1";
    public static final String DOMAIN = "/domain";

    public static final String WORKFLOW_LAYER_ERROR = "WORKFLOW LAYER NOT GIVING RESPONSE";
    public static final String SIGN = "/sign";

    public static final String RULE_FIELD = "/rule/rulefield";
    public static final String DICTIONARY = "/table";


    public static final String REQUEST_TYPE_UPDATE = "U";
    public static final String REQUEST_TYPE_INACTIVE = "A";

    public static final String RULE_START_DELIMITER = "@_";
    public static final String DELIMITER_RULE_END = "#_";
    public static final String RULE_CONDITION_DELIMITER = ",";

    public static final String RULE_FULLCOLUMN_NAME_DELIMITER = ".";
    public static final String RULE_TOKEN_DELIMITER = "$";

    public static final String RULE_NAME_DELIMITER = "NR";
    public static final TokenData TokenUnhandleException = null;
    public static final String FIELD_VALIDATION_CODE = "1001";

    public static final String RULE_VALIDATION_PARAM_VALUE = "1";
    public static final String RULE_VALIDATION_PARAM_VALUE_DATE = "01-JAN-2023";


    public static final String SUCCESS = "Success";
    public static final String RECORD_NOT_SAVED = "Record Not Saved";
    public static final String RECORD_NOT_UPDATED = "Record Not Updated";
    public static final String INVALID_TOKEN = "Invalid Token";
    public static final String AUTHORIZATION1 = "Authorization";
    public static final String CONFIGURATION = "ruleengine";
    public static final String DATAACCESSEXCEPTION = "Data Access Exception";

    public static final String EXC_DOMAIN_NOT_EXIST = "Domain Not Exist";

    public static final String EXC_UNKNOWN_CODE = "4999";

    public static final String EXC_DOMAIN_NOT_EXIST_CODE = "4000";
    public static final String EXC_DICTIONARY_TABLE_NOT_EXIST_CODE = "4004";

    public static final String EXC_DOMAIN_NAME_ALREADY_EXIST_CODE = "4001";
    public static final String EXC_DOMAIN_FRIENDLY_NAME_ALREADY_EXIST_CODE = "4002";

    public static final String EXC_RULE_NAME_ALREADY_EXIST_CODE = "4005";
    public static final String EXC_RULE_FIELD_NAME_ALREADY_EXIST_CODE = "4111";

    public static final String EXC_RULE_NOT_EXIST_CODE = "4006";

    public static final String EXC_RULE_EXISTS_FOR_THIS_DOMAIN_CODE = "4007";

    public static final String EXC_RULE_INVALID_CODE = "4008";

    public static final String EXC_RULE_PARAMETER_NOT_GIVEN_CODE = "4009";

    public static final String EXC_RULE_INVALID_TOKENS_CODE = "4011";

    public static final String EXC_RULE_INVALID_QUERY_CODE = "4012";


    public static final String EXCEPTION = "Exception";
    public static final String SQL_EXCEPTION = "SQL Exception";
    public static final String JSON_EXCEPTION = "JSON Exception";
    public static final String SQL_RECOVERABLE_EXCEPTION = "SQL Recoverable Exception";
    public static final String ILLEGAL_ARGUMENT_EXCEPTION = "Illegal Argument Exception";
    public static final String CLASS_CAST_EXCEPTION = "Class Cast Exception";
    public static final String SOCKET_EXCEPTION = "Socket Exception";
    public static final String Y = "Y";
    public static final String N = "N";
    public static final String RECORD_NOT_FOUND = "Record Not Found";
    public static final String SECURITY_REQUIREMENT = "Bearer Authentication";
    public static final String BEARER = "Bearer";
    public static final String LIMITS = "limits";
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    public static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    public static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    public static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
    public static final String MAX_AGE = "3600";
    public static final String S1 = "*";
    public static final String S_1_TRUE = "true";
    public static final String EMPTY_STRING = "";
    public static final String USER_ID = "userId";
    public static final String LOGIN_ID = "loginId";
    public static final String USER_TYPE_ID = "userTypeId";
    public static final String EQUALS_IGNORE_CASE = "N";
    public static final String EXPIRED = "Expired";
    public static final int SEVEN = 7;
    public static final String CHARSET_NAME = "UTF-8";
    public static final String SHA_1 = "SHA-1";
    public static final String ALGORITHM = "AES";

    public static final String PADDING = "AES/CBC/NOPADDING";


    public static final String SEC_KEY = "AIS_JSCASH";
    public static final String JWT_ID = "ID";
    public static final String JWT_SUBJECT = "Subject";
    public static final String JWT_ISSUER = "Issuer";
    public static final String JWT_EXPIRATION = "Expiration";
    public static final String JWT_EXPIRED = "Expired";
    public static final String JWT_EXPIRED_CHECK = "Y";
    public static final String CONTAINS = "JWT expired at";
    public static final String SUCCESS_CODE = "0000";
    public static final String UNHANDLE_EXCEPTION = null;


    public static final String VALIDATIONS_FAILED = "Validations Failed";


    public static final String RULE_NAME_REQUIRED = "Rule Name Is Required";

    public static final String RULE_FIELD_NAME_REQUIRED = "Rule Field Name Is Required";

    public static final String DOMAIN_ID_REQUIRED = "Domain ID Is Required";

    public static final String DOMAIN_NAME_REQUIRED = "Domain Name Is Required";

    public static final String DOMAIN_IS_NULL = "Domain Has No Value";
    public static final String DOMAIN_FRIENDLY_NAME_REQUIRED = "Domain Friendly Name Is Required";

    public static final String DOMAIN_NAME_MIN_SIZE = "Domain Name Must Be Atleast 3 Characters Long";
    public static final String RULE_NAME_MIN_SIZE = "Rule Name Must Be Atleast 3 Characters Long";
    public static final String RULE_FIELD_NAME_MIN_SIZE = "Rule Field Name Must Be Atleast 3 Characters Long";

    public static final String RULE_ID_MANDATORY = "Rule ID Is Mandatory";
    public static final String RULE_CODE_MANDATORY = "Rule Code Is Mandatory";

    public static final String RULE_ACTIVE_MANDATORY = "Rule STATUS Is Mandatory";

    public static final String SEARCH_VALIDATION = "Select Any One Option";
    public static final String SEARCH_VALIDATION_CODE = "400";

    public static final String MODULE_ID = "moduleId";

    public static final String RECORD_EXIST = "Record Already Exist";

    public static final String RECORD_NOT_FOUND_CODE = "161300";
    public static final String RECORD_NOT_FOUND_CODE_WITHOUT_MODULEID = "1300";

    public static final String ERROR_DECRYTION = "Error occurred during decryption: ";
    public static final String ERROR_ENCRYPTION = "Error occurred during encryption: ";
    public static final String AES_SECRET_KEY = "ais-567890123456789012345678-256";

    /**
     * MAKER CHECKER CONSTANTS
     */

    public static final String MAKER_ID_KEY = "makerId";
    public static final String MAKER_COMMENTS_KEY = "makerComments";
    public static final String FT_FLAG_KEY = "ftFlag";
    public static final String UPDATE_JSON_KEY = "updateJson";
    public static final String REF_TABLE_ID_KEY = "refTableId";
    public static final String MC_REQUEST_ID_KEY = "mcRequestId";
    public static final String MC_PEINDING_REQUEST_ID_KEY = "mcPeindingRequestId";
    public static final String CHECKER_ID_KEY = "checkerId";
    public static final String CHECKER_COMMENTS_KEY = "checkerComments";
    public static final String ACTION_KEY = "action";
    public static final String UPDATED_INDEX_KEY = "updatedIndex";
    public static final String MC_APPLICABLE_END_POINT = "/v1/management/ckeckMcApplicable";
    public static final String MC_REQUEST_END_POINT = "/v1/management/mcRequest";
    public static final String MC_ACTION_END_POINT = "/v1/management/mcAction";
    public static final String CONTENT_TYPE = "content-type";
    public static final String FORM_NAME_KEY = "formName";
    public static final String TABLE_NAME_KEY = "tableName";
    public static final String REQUEST_TYPE_KEY = "requestType";
    public static final String ACCEPT = "accept";
    public static final String APPLICATION_JSON = "application/json";
    public static final String MODULE_ID_KEY = "moduleId";
    public static final String AUTHORIZATION = "Authorization";
    public static final String REQUEST_DATA = "data";
    public static final String UPDATE_TYPE_KEY = "updateType";

    public static final int STATUS_PENDING = 1;
    public static final int STATUS_APPROVED = 2;


    public static final String RULE_EXECUTE_VALIDATE = "validateRule Method In Service Layer";


}
