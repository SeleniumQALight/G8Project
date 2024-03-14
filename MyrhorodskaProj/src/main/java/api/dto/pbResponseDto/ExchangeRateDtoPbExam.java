package api.dto.pbResponseDto;

public class ExchangeRateDtoPbExam {
    String ccy;
    String base_ccy;
    String buy;
    String sale;
    public ExchangeRateDtoPbExam() {
    }
    public ExchangeRateDtoPbExam(String ccy, String base_ccy) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
    }

    public String getCcy() {
        return ccy;
    }

    public String getBase_ccy() {
        return base_ccy;
    }

    public String getBuy() {
        return buy;
    }

    public String getSale() {
        return sale;
    }
}
