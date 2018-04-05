package ru.kbond.exchange;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test.
 * @author kbondarenko
 * @since 30.03.2018
 * @version 1
 */
public class OrderBookTest {
    /**
     * The order for "issuerAsk1" is compared with the bid
     * for the sale with a lower price of "issuerBid",
     * the volume of applications from both sides is deducted.
     * In the application "issuerAsk1", after the operation,
     * there is 1 volume, then, according to the same scheme,
     * the application for "issuerAsk1" continues to check
     * the list for sale, finding a further position there
     * is a further subtraction. If the emitter in the output
     * to the screen has offers with one price, then their
     * volumes are summed.
     */
    @Test
    public void whenAddAskAndAddBidThenAskDeductedFromListWithLowerPrice() {
        OrderBook orderBook = new OrderBook();
        Book bid = new Book(2, "Gazprom", "add", "bid", 135, 12);
        Book bid1 = new Book(4, "Gazprom", "add", "bid", 131, 14);
        Book ask = new Book(1, "Gazprom", "add", "ask", 130, 10);
        Book ask1 = new Book(3, "Gazprom", "add", "ask", 140, 13);
        Book asus = new Book(6, "Asus", "add", "bid", 110, 11);
        orderBook.setBidOrAsk(bid);
        orderBook.setBidOrAsk(bid1);
        orderBook.setBidOrAsk(ask);
        orderBook.setBidOrAsk(ask1);
        orderBook.setBidOrAsk(asus);

        StringBuilder expected = new StringBuilder();
        expected.append("Ask      Price     Bid\n");
        expected.append("    Book: Gazprom\n");
        expected.append(" 10      130\n");
        expected.append("           131      13\n");
        expected.append("    Book: Asus\n");
        expected.append("           110      11\n");
        StringBuilder result = new StringBuilder();
        result.append(orderBook.show());

        assertThat(result.toString(), is(expected.toString()));
    }
    /**
     * Test.
     */
    @Test
    public void whenAddAskAndAddBidThenPrintAllRequestsOnTheScreen() {
        OrderBook orderBook = new OrderBook();
        Book bid = new Book(2, "Gazprom", "add", "bid", 100, 12);
        Book bid1 = new Book(4, "Gazprom", "add", "bid", 110, 13);
        Book ask = new Book(1, "Gazprom", "add", "ask", 80, 10);
        Book ask1 = new Book(3, "Gazprom", "add", "ask", 90, 11);
        Book bidAsus = new Book(6, "Asus", "add", "bid", 130, 15);
        Book bidAsus1 = new Book(7, "Asus", "add", "bid", 120, 14);
        Book askAsus1 = new Book(8, "Asus", "add", "ask", 110, 16);
        orderBook.setBidOrAsk(ask);
        orderBook.setBidOrAsk(ask1);
        orderBook.setBidOrAsk(bid);
        orderBook.setBidOrAsk(bid1);
        orderBook.setBidOrAsk(bidAsus);
        orderBook.setBidOrAsk(bidAsus1);
        orderBook.setBidOrAsk(askAsus1);

        StringBuilder expected = new StringBuilder();
        expected.append("Ask      Price     Bid\n");
        expected.append("    Book: Gazprom\n");
        expected.append(" 11       90\n");
        expected.append(" 10       80\n");
        expected.append("           110      13\n");
        expected.append("           100      12\n");
        expected.append("    Book: Asus\n");
        expected.append(" 16      110\n");
        expected.append("           130      15\n");
        expected.append("           120      14\n");
        StringBuilder result = new StringBuilder();
        result.append(orderBook.show());

        assertThat(result.toString(), is(expected.toString()));
    }
    /**
     * Test.
     */
    @Test
    public void whenDeleteRequestThenPrintRequestsOnTheScreenWithoutRemove() {
        OrderBook orderBook = new OrderBook();
        Book bid = new Book(2, "Gazprom", "add", "bid", 100, 12);
        Book bid1 = new Book(4, "Gazprom", "add", "bid", 110, 13);
        Book ask = new Book(1, "Gazprom", "add", "ask", 80, 10);
        Book ask1 = new Book(3, "Gazprom", "add", "ask", 90, 11);
        Book bidAsus = new Book(6, "Asus", "add", "bid", 130, 15);
        Book bidAsus1 = new Book(7, "Asus", "add", "bid", 120, 14);
        Book askAsus1 = new Book(8, "Asus", "add", "ask", 110, 16);
        orderBook.setBidOrAsk(ask);
        orderBook.setBidOrAsk(ask1);
        orderBook.setBidOrAsk(bid);
        orderBook.setBidOrAsk(bid1);
        orderBook.setBidOrAsk(bidAsus);
        orderBook.setBidOrAsk(bidAsus1);
        orderBook.setBidOrAsk(askAsus1);
        orderBook.delete(2, "Gazprom", "bid");

        StringBuilder expected = new StringBuilder();
        expected.append("Ask      Price     Bid\n");
        expected.append("    Book: Gazprom\n");
        expected.append(" 11       90\n");
        expected.append(" 10       80\n");
        expected.append("           110      13\n");
        expected.append("    Book: Asus\n");
        expected.append(" 16      110\n");
        expected.append("           130      15\n");
        expected.append("           120      14\n");
        StringBuilder result = new StringBuilder();
        result.append(orderBook.show());

        assertThat(result.toString(), is(expected.toString()));
    }
}