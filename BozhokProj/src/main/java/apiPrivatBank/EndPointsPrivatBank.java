package apiPrivatBank;

public interface EndPointsPrivatBank {
    String  BASE_URL_PB = "https://api.privatbank.ua/p24api/exchange_rates";
    String BASE_URL_DATE = BASE_URL_PB + "?date={0}";
}