package arbitration;

public class ArbitrationJobWithBLOBs extends ArbitrationJob {
    private String field18;

    private String field21;

    private String field22;

    private String field25;

    private String investsCode;

    private String investsMoney;

    private String arbitrationMsg;

    public String getField18() {
        return field18;
    }

    public void setField18(String field18) {
        this.field18 = field18 == null ? null : field18.trim();
    }

    public String getField21() {
        return field21;
    }

    public void setField21(String field21) {
        this.field21 = field21 == null ? null : field21.trim();
    }

    public String getField22() {
        return field22;
    }

    public void setField22(String field22) {
        this.field22 = field22 == null ? null : field22.trim();
    }

    public String getField25() {
        return field25;
    }

    public void setField25(String field25) {
        this.field25 = field25 == null ? null : field25.trim();
    }

    public String getInvestsCode() {
        return investsCode;
    }

    public void setInvestsCode(String investsCode) {
        this.investsCode = investsCode == null ? null : investsCode.trim();
    }

    public String getInvestsMoney() {
        return investsMoney;
    }

    public void setInvestsMoney(String investsMoney) {
        this.investsMoney = investsMoney == null ? null : investsMoney.trim();
    }

    public String getArbitrationMsg() {
        return arbitrationMsg;
    }

    public void setArbitrationMsg(String arbitrationMsg) {
        this.arbitrationMsg = arbitrationMsg == null ? null : arbitrationMsg.trim();
    }
}