package Models;
import java.util.Date;
public class Payment {
    private Long id;
    private String cardNumber;
    private String cardHolderName;
    private Date date;
    private String cvc;
    double commissionCompany;
    private Long workerId;
    private ConsultantResume resume;
    private double workerMoney;
    private  double price;
    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }
    public void setResume(ConsultantResume resume) {
        this.resume = resume;
    }
    public void setWorkerMoney(double workerMoney) {
        this.workerMoney = workerMoney;
    }
    public void setCommissionCompany(double commissionCompany) {
        this.commissionCompany = commissionCompany;
    }
    public Long getId() {
        return id;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public Date getDate() {
        return date;
    }
    public String getCvc() {
        return cvc;
    }
    public ConsultantResume getResume() {
        return resume;
    }
    public Long getWorkerId(){
        return workerId;
    }
    public String getCardHolderName(){
        return cardHolderName;
    }
    public double getWorkerMoney(){
        return workerMoney;
    }
    public double getCommissionCompany(){
        return commissionCompany;
    }
}
