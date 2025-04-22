package com.zindigi.account_migration.util.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfs.commonservice.dto.Response;
import com.mfs.commonservice.util.CustomDataNotFoundException;
import com.zindigi.account_migration.util.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Utility {


    @Value("${franchise.level}")
    private  String franchiseLevel;

    @Value("${retailer.level}")
    private String retailerLevel;

    @Value("${handler.level}")
    private String handlerLevel;

    @Value("${corporate.level}")
    private String corporateLevel;

    private Utility() {

    }

    static Random random = new Random();

    public static List<String> getTokensWithCollection(final String str, final String delimiter) {
        return Collections.list(new StringTokenizer(str, delimiter)).stream()
                .map(String::valueOf)
                .collect(Collectors.toList());

    }

    public static String getCode(String text) {

        int randomNumber = random.nextInt(100);

        return text + randomNumber;


    }


    public static int getStartingIndexOfRule(String rule) {

        return Integer.valueOf(rule.substring(2, 3));
    }

    public static int getEndingIndexOfRule(String rule) {
        int endingIndex = 100;
        for (int i = 100; i >= 0; i--) {
            if (rule.contains("@_" + i)) {
                endingIndex = i;
                break;
            }


        }
        return endingIndex;
    }

    public static String convertCharactersToAscii(String x) {

        byte[] bytes = x.getBytes(StandardCharsets.US_ASCII);
        List<Integer> result = new ArrayList<>();   // convert bytes to ascii
        for (byte aByte : bytes) {
            int ascii = (int) aByte;                // byte -> int
            result.add(ascii);
        }
        String finalResult = result.toString().replace("[", "");
        finalResult = finalResult.replace("]", "");
        finalResult = finalResult.replace(",", "");
        finalResult = finalResult.replace(" ", "");
        return finalResult;

    }


    public static String genrateCheckDigit(String x) {
        String result;
        long sum = 0;
        for (int i = 0; i < x.length(); i++) {
            sum = sum + Long.valueOf(x.charAt(i));
        }
        sum = sum % 97;
        sum = 98 - sum;
        if (sum < 10) {
            result = "0" + sum;
        } else {
            result = String.valueOf(sum);
        }
        return result;
    }

    public static String generateRandomNumberForAccountNumber() {
        int length = Integer.valueOf(9);
        Random random = new SecureRandom();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return new String(digits);
    }

    public static String getStringValue(Object obj) {
        return obj != null ? obj.toString() : "";
    }

    public static String generateIban(String accountNumber) {
        String bankCode = convertCharactersToAscii("JSBL");
        String countryCode = convertCharactersToAscii(convertCharactersToAscii("PK"));
        String checkDigit = genrateCheckDigit(bankCode + accountNumber + countryCode + "00");
        String iban = "PK" + checkDigit + "JSBL" + accountNumber;
        return iban;
    }

    public static BigDecimal getBigDecimalValue(Object obj) {
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        } else if (obj instanceof Number) {
            return new BigDecimal(obj.toString());
        } else {
            return BigDecimal.ZERO;
        }
    }



    public static String generatePinSalt() {

        int length = Integer.valueOf(4);
        Random random = new SecureRandom();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return new String(digits);
    }

    public static String setEmpty(String value) {
        if(value==null){
            value= Constants.empty;
        }
        return value;
    }

    public static String formatNumber(String number) {
        StringBuilder formattedNumber = new StringBuilder();

        formattedNumber.append(number.substring(0, 5));
        formattedNumber.append("-");
        formattedNumber.append(number.substring(5, 12));
        formattedNumber.append("-");
        formattedNumber.append(number.substring(12));

        return formattedNumber.toString();
    }

    public static void setErrorMessage(Response response) throws com.mfs.commonservice.util.CustomDataNotFoundException {
        String errorMessage = response.getMessage();
        if (!isNullOrEmpty(response.getErrors())) {
            String errors = response.getErrors().stream()
                    .map(e -> e.getErrorDescr())
                    .collect(Collectors.joining(", "));
            errorMessage += Constants.ERROR_SERVICE + errors;
        }
        throw new CustomDataNotFoundException(errorMessage);
    }

    public static <T> boolean isNullOrEmpty(T input) {
        return input == null || (input instanceof String && ((String) input).isEmpty()) ||
                (input instanceof List && ((List<?>) input).isEmpty()) ||
                (input instanceof Map && ((Map<?, ?>) input).isEmpty());
    }

    public static boolean IsNullOrEmpty(String value) {
        if (value != null && !value.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static Response formDataPostRequest(String url, HttpHeaders headers, MultiValueMap<String, Object> formData) {
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(formData, headers);
        WebClient webClient = WebClient.create(url); // Replace with your actual API URL

        String response = webClient.post()
                .uri("") // If the full URL is in create(), you can leave the URI empty or use the path
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(String.class)  // Assuming the response body is a String
                .block();
        try {
            Response response1=new ObjectMapper().readValue(response,Response.class);
            return response1;
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> createPostParamBackOffice(Object data) {
        // Create a new HashMap object to store the post parameters
        Map<String, Object> postParam = new HashMap<>();
        // Convert the data object to JSON and store it in the "data" parameter
        postParam.put(Constants.requestData, convertObjecttoJson(data));
        // Return the populated postParam object
        return postParam;
    }

    //this method is used to convert json to string
    public static String convertObjecttoJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            return jsonString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generateSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

        // Convert byte array to a hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    //for Qr
    public static String autoGenerate(String type, int length) {

        String response = Constants.empty;
        if ("Trace".equals(type)) {
            response = formatCurrentDateTime();
            response = response.substring(response.length() - 6);
            return new String(shuffleArray(response.toCharArray()));
        } else if ("WCNRandom".equals(type)) {
            response = formatCurrentDateTime();
            response = response.substring(response.length() - 15);
            return new String(shuffleArray(response.toCharArray()));
        } else if ("TrackingId".equals(type)) {
            response = formatCurrentDateTime();
            response = response.substring(response.length() - 10);
            return new String(shuffleArray(response.toCharArray()));
        } else if ("TransactionID".equals(type)) {
            response = formatCurrentDateTime();
            response = response.substring(response.length() - 15);
            return new String(shuffleArray(response.toCharArray()));
        } else if ("Transaction".equals(type)) {
            response = formatCurrentDateTime()
                    + new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
            response = response.substring(response.length() - length);
            return new String(shuffleArray(response.toCharArray()));
        } else if ("RandomNumber".equals(type)) {
            response = "";
            Random rand = new Random();
            String ticks = String.valueOf(System.currentTimeMillis());
            String extractedNumbers = ticks.substring(ticks.length() - 4);
            String ranNumber = String.valueOf(rand.nextInt()).substring(1, length - 4);
            return ranNumber + extractedNumbers;
        } else if ("TraceL".equals(type)) {
            response = formatCurrentDateTime();
            response = response.substring(response.length() - length);
            return new String(shuffleArray(response.toCharArray()));
        } else if ("Numeric".equals(type)) {
            response = formatCurrentDateTime()
                    + new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
            response = response.substring(response.length() - length);
            return new String(shuffleArray(response.toCharArray()));
        } else if ("AlphaNumeric".equals(type)) {
            response = "A1B2C3D4E5F6G7H8I9J10K11L12M13N1";
            response = response.substring(response.length() - length);
            return new String(shuffleArray(response.toCharArray()));
        } else if ("Nonce".equals(type)) {
            response = String.valueOf(UUID.randomUUID());
            return response;
        } else if ("RRN".equals(type)) {
            if (length == 0) {
                response = formatCurrentDateTime();
            } else if (length == 13) {
                response = formatCurrentDateTime().substring(0, 12);
            } else {
                response = formatCurrentDateTime();
            }

            return new String(shuffleArray(response.toCharArray()));
        } else if ("RequestId".equals(type)) {
            response = formatCurrentDateTime();
            return new String(shuffleArray(response.toCharArray()));
        } else if ("TelcoDateTime".equals(type)) {
            response = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date());
            return response;
        } else if ("DateTime".equals(type)) {
            response = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            return response;
        } else if ("TransactionDateTime".equals(type)) {
            response = new SimpleDateFormat("MMddHHmmss").format(new Date());
            return response;
        } else if ("Date".equals(type)) {
            response = new SimpleDateFormat("MMdd").format(new Date());
            return response;
        } else if ("LongTime".equals(type)) {
            response = formatCurrentDateTime();
            return response;
        } else if ("LongTime24".equals(type)) {
            response = formatCurrentDateTime();
            return response;
        } else if ("EvenOdd".equals(type)) {
            response = String.valueOf(new Random().nextInt(12) + 1);
            return response;
        } else if ("Time24".equals(type)) {
            response = new SimpleDateFormat("HHmmss").format(new Date());
            return response;
        } else {
            response = new SimpleDateFormat("HHmmss").format(new Date());
            return response;
        }
    }

    public static String formatCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSSS");
        return currentDateTime.format(formatter);
    }

    private static char[] shuffleArray(char[] array) {
        List<Character> characters = new ArrayList<>();
        for (char c : array) {
            characters.add(c);
        }
        Collections.shuffle(characters);
        char[] shuffledArray = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            shuffledArray[i] = characters.get(i);
        }
        return shuffledArray;
    }

    public static String setlength(String str) {
        String length = "00";
        if (str.length() <= 9) {
            length = "0" + str.length();
        } else {
            length = String.valueOf(str.length());
        }
        return length;
    }

    public static String generateCRC(String value) {
        byte[] data = value.getBytes(StandardCharsets.US_ASCII);
        int result = calculateCRC16(data, 0, data.length);
        return Integer.toHexString(result).toUpperCase();
    }

    public static int calculateCRC16(byte[] data, int offset, int length) {
        if (data == null || offset < 0 || offset > data.length - 1 || offset + length > data.length) {
            return 0;
        }

        int crc = 0xFFFF;
        for (int i = 0; i < length; ++i) {
            crc ^= (data[offset + i] & 0xFF) << 8;
            for (int j = 0; j < 8; ++j) {
                crc = (crc & 0x8000) > 0 ? (crc << 1) ^ 0x1021 : crc << 1;
            }
        }
        return crc & 0xFFFF;
    }

    public static String generateNonce() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String generateTransactionCode(String channel, String terminal) {
        LocalDate currentDate = LocalDate.now();
        return String.valueOf(currentDate.getMonthValue()) + currentDate.getDayOfMonth() + currentDate.getYear() + channel + terminal + generatePinSalt();
    }

    public static boolean areNumbersEqual(Number num1, Number num2) {
        // Null check
        if (num1 == null || num2 == null) {
            return false;
        }
        // Compare the two numbers
        return Long.compare(num1.longValue(), num2.longValue()) == 0;
    }
}
