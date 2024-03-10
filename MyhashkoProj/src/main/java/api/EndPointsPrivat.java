package api;

import java.net.URI;

public interface EndPointsPrivat {
    String BASE_URL = "https://api.privatbank.ua";
    String EXCHANGE_RATES = BASE_URL + "/p24api/exchange_rates";
    String PRIVATE_EXAM = BASE_URL + "/p24api/pubinfo?json&exchange&coursid=5";
    String PRIVATE_START_PAGE_EXAM = "https://privatbank.ua/";
}
