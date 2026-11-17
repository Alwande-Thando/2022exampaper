package za.co.prog6112.q2;

/** Calculates totals for the three average property types in each province. */
public class PropertyPriceReport {
    private final String[] provinces = {"Gauteng", "Natal", "Cape"};
    private final String[] propertyTypes = {"Flat", "Townhouse", "House"};
    private final double[][] prices = {
        {800000, 1500000, 2000000},
        {700000, 1200000, 1600000},
        {750000, 1300000, 1800000}
    };

    public String[] getProvinces() { return provinces.clone(); }
    public String[] getPropertyTypes() { return propertyTypes.clone(); }
    public double[][] getPrices() {
        double[][] copy = new double[prices.length][];
        for (int i = 0; i < prices.length; i++) copy[i] = prices[i].clone();
        return copy;
    }

    public double totalForProvince(int provinceIndex) {
        double total = 0;
        for (double price : prices[provinceIndex]) total += price;
        return total;
    }

    public String createReport() {
        StringBuilder report = new StringBuilder();
        report.append(String.format("%-16s %14s %14s %14s %16s%n",
                "Province", "Flat", "Townhouse", "House", "Total"));
        for (int i = 0; i < provinces.length; i++) {
            report.append(String.format("%-16s R%13,.0f R%13,.0f R%13,.0f R%15,.0f%n",
                    provinces[i], prices[i][0], prices[i][1], prices[i][2], totalForProvince(i)));
        }
        return report.toString();
    }

    public static void main(String[] args) {
        System.out.println(new PropertyPriceReport().createReport());
    }
}
