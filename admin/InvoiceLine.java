public class Invoice {

    private int partNumber ;
    private String partDescription;
    private int quantity;
    private double price;

    public Invoice(int partNumber, String partDescription, int quantity, double price) {
        this.partNumber = partNumber;
        this.partDescription = partDescription;
        this.quantity = quantity;
        this.price = price;
    }

  
    public String toString() {
        return "Invoice{" +
                "partNumber=" + partNumber +
                ", partDescription=" + partDescription +" "+
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String getDescriptionQuantity() {
        return String.format("%s %s", partDescription, quantity);
    }

    public String getDescriptionTotalPrice() {
       return String.format("%s %s", partDescription, quantity * price);
    }

    public double getValue() {
       return quantity * price;        // getting the invoice total value
    }
    
   public boolean isWithinRange(double min, double max) {
      return getValue() >= min && getValue() <=max;
   }
}
// Invoice Generator

import java.util.ArrayList;

public class InvoiceGenerator {

    private ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();

    public InvoiceGenerator() {
        invoiceList.add(new Invoice(101,"some desc",1,100));
        invoiceList.add(new Invoice(102,"some descA",2,101));
        invoiceList.add(new Invoice(103,"some descB",3,102));
        invoiceList.add(new Invoice(104,"some descC",4,103));
        invoiceList.add(new Invoice(105,"some descD",5,104));
        invoiceList.add(new Invoice(106,"some descH",6,105));
        invoiceList.add(new Invoice(107,"some descE",7,106));
        invoiceList.add(new Invoice(108,"some descF",8,107));
        invoiceList.add(new Invoice(109,"some descG",9,108));
        invoiceList.add(new Invoice(110,"some descI",10,109));
    }

    public ArrayList<Invoice> getInvoiceList(){
        return  invoiceList;
    }
}
// Invoice Handler

import java.util.ArrayList;

import static java.util.Comparator.comparing;

public class InvoiceHandler {

    private final ArrayList<Invoice> invoices;

    private InvoiceHandler(){
        this.invoices = new InvoiceGenerator().getInvoiceList();
    }

    private void sortByPrice(){
        invoices.stream().sorted(comparing(Invoice::getPrice)).forEach(System.out::println);
    }

    private void sortByDescription(){
        invoices.stream().sorted(comparing(Invoice::getPartDescription)).forEach(System.out::println);
    }

    private void mappingDescriptionAndPrice(){
            invoices.stream().sorted(comparing(Invoice::getQuantity)).map(Invoice::getDescriptionQuantity).forEach(System.out::println);

        // sorted method requires a value, so we have sorted on the basis of quantity.
        // we can also call it as sorted(invoice -> invoice.getQuantity())
        // sorted, map, filter function take in lambda functions which are of the form  functionName(input -> {return expression})
}

    private void mappingDescriptionAndValue() {
        invoices.stream().sorted(comparing(Invoice::getValue)).map(Invoice::getDescriptionTotalPrice).forEach(System.out::println);
    }

    private void filterByRange(double min, double max) {
        invoices.stream().sorted(comparing(Invoice::getValue)).filter(invoice -> invoice.isWithinRange(min, max)).map(Invoice::getDescriptionTotalPrice).forEach(System.out::println);
    }

    public static void main(String[] args) {
        InvoiceHandler invoiceHandler = new InvoiceHandler();
        System.out.println("nSorted by description:n");
        invoiceHandler.sortByDescription();
        System.out.println("nSorted by price:n");
        invoiceHandler.sortByPrice();
        System.out.println("nMapping each invoice to its description and quantity:n");
        invoiceHandler.mappingDescriptionAndPrice();
        System.out.println("nMapping each invoice to its description and value:n");
        invoiceHandler.mappingDescriptionAndValue();
        System.out.println("nFilter value between range and map each Invoice to its description and value:n");
        invoiceHandler.filterByRange(200,500);
    }

}
