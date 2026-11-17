package za.co.prog6112.q2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PropertyPriceReportTest {
    @Test void gautengTotalIsCorrect() {
        assertEquals(4300000, new PropertyPriceReport().totalForProvince(0), 0.001);
    }
    @Test void natalTotalIsCorrect() {
        assertEquals(3500000, new PropertyPriceReport().totalForProvince(1), 0.001);
    }
    @Test void capeTotalIsCorrect() {
        assertEquals(3850000, new PropertyPriceReport().totalForProvince(2), 0.001);
    }
}
