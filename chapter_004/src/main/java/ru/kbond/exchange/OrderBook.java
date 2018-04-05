package ru.kbond.exchange;

import java.util.*;

/**
 * Order book.
 * A trading system that accepts bids
 * for the purchase and sale of shares.
 *
 * @author kbondarenko
 * @version 1
 * @since 25.03.2018
 */
public class OrderBook {
    private final static String BID = "bid";
    private final static String ASK = "ask";
    /**
     * Map storing glasses with applications.
     */
    private Map<String, Map<String, List<Book>>> orderBook = new HashMap<>();
    /**
     * The method accepts the application and places it in the desired glass.
     * When adding, the application is checked for the opposite action, if the
     * action is possible, then the shares are withdrawn from both bids.
     *
     * @param book incoming application.
     */
    public void setBidOrAsk(Book book) {
        if (this.orderBook.containsKey(book.getBook())) {
            if (book.getAction().equals(BID)) {
                if (!checkSellOrBuy(book)) {
                    this.orderBook.get(book.getBook()).get(book.getAction()).add(book);
                }
            } else if (book.getAction().equals(ASK)) {
                if (!checkSellOrBuy(book)) {
                    this.orderBook.get(book.getBook()).get(book.getAction()).add(book);
                }
            }
        } else {
            Map<String, List<Book>> bidAndAsk = new HashMap<>();
            bidAndAsk.put(BID, new ArrayList<>());
            bidAndAsk.put(ASK, new ArrayList<>());
            bidAndAsk.get(book.getAction()).add(book);
            this.orderBook.put(book.getBook(), bidAndAsk);
        }
    }
    /**
     * The method checks whether there is an application in the glass with
     * the opposite effect.
     *
     * @param book incoming application.
     * @return {@code true} if there is a deduction.
     */
    private boolean checkSellOrBuy(Book book) {
        boolean success = false;
        String oppositeActions = book.getAction();
        if (oppositeActions.equals(BID)) {
            oppositeActions = ASK;
        } else {
            oppositeActions = BID;
        }
        List<Book> tmp = this.orderBook.get(book.getBook()).get(oppositeActions);
        for (int i = 0; i < tmp.size(); i++) {
            if (book.getAction().equals(BID)
                    ? (tmp.get(i).getPrice() >= book.getPrice())
                    : (tmp.get(i).getPrice() <= book.getPrice())) {
                int remainder = tmp.get(i).getVolume() - book.getVolume();
                if (remainder == 0) {
                    this.orderBook.get(book.getBook()).get(oppositeActions).remove(i);
                    tmp.set(i, null);
                    success = true;
                    break;
                } else if (remainder > 0) {
                    tmp.get(i).setVolume(remainder);
                    this.orderBook.get(book.getBook()).get(oppositeActions).set(i, tmp.get(i));
                    success = true;
                    break;
                } else {
                    book.setVolume(Math.abs(remainder));
                    this.orderBook.get(book.getBook()).get(oppositeActions).remove(i);
                    i--;
                }
            }
        }
        return success;
    }
    /**
     * The method deletes the request.
     *
     * @param id     identification number.
     * @param book   incoming application.
     * @param action bid or ask.
     * @return {@code true} if the action is completed.
     */
    public boolean delete(int id, String book, String action) {
        boolean checkDelete = false;
        List<Book> list = this.orderBook.get(book).get(action);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.remove(i);
                this.orderBook.get(book).put(action, list);
                checkDelete = true;
                break;
            }
        }
        return checkDelete;
    }
    /**
     * The method displays the result on the screen. At the same time,
     * the volume of orders is added at the same price.
     *
     * @return text-based display applications.
     */
    public StringBuilder show() {
        StringBuilder result = new StringBuilder();
        result.append("Ask      Price     Bid\n");
        for (String key : this.orderBook.keySet()) {
            boolean flag = true;
            for (Map.Entry<String, List<Book>> books : this.orderBook.get(key).entrySet()) {
                books.getValue().sort(sortPrint);
                calc(books.getValue());
                for (Book i : books.getValue()) {
                    if (i.getAction().equals(ASK)) {
                        if (flag) {
                            result.append(String.format("    Book: %s\n", i.getBook()));
                        }
                        result.append(String.format("%3d %8d\n", i.getVolume(), i.getPrice()));
                        flag = false;
                    } else {
                        if (flag) {
                            result.append(String.format("    Book: %s\n", i.getBook()));
                        }
                        result.append(String.format("%14d %7d\n", i.getPrice(), i.getVolume()));
                        flag = false;
                    }
                }
            }
        }
        return result;
    }
    /**
     * The method makes the addition of orders with the same volume.
     *
     * @param list list of applications.
     * @return list after the change.
     */
    private List<Book> calc(List<Book> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getPrice() == list.get(j).getPrice()
                        &&
                        list.get(i).getBook().equals(list.get(j).getBook())) {
                    int tmp = list.get(i).getVolume() + list.get(j).getVolume();
                    list.remove(j);
                    list.get(i).setVolume(tmp);
                }
            }
        }
        return list;
    }
    /**
     * The comparator sorts by descending price.
     */
    private final Comparator<Book> sortPrint = new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            return -Integer.compare(o1.getPrice(), o2.getPrice());
        }
    };
}
