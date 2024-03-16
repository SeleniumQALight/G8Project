package apiPrivatBank;

public interface EndPointsPrivatBank {
    String  BASE_URL_PB = "https://api.privatbank.ua/p24api";
    String BASE_URL_DATE = BASE_URL_PB + "/exchange_rates?date={0}";
    String BASE_URL_CURRENCY = BASE_URL_PB + "/pubinfo?json&exchange&coursid=5";
    String BASE_URL_UI = "https://privatbank.ua/";
}