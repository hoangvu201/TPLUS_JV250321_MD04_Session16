package dao;

public class StatisticAuthor {
    private String authorName;
    private int count;

    public StatisticAuthor() {
    }
    public StatisticAuthor(String authorName, int count) {
        this.authorName = authorName;
        this.count = count;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
