package apiPrivatbank;

public interface EndPointsPrivatbank {

    String BASE_URL = "https://api.privatbank.ua/p24api/exchange_rates";
    String EXCHANGE_BY_DATE = BASE_URL + "?date={0}";
}
