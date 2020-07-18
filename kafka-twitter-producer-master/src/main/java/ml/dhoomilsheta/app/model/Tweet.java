package ml.dhoomilsheta.app.model;

import com.google.gson.annotations.SerializedName;

public class Tweet {
    private long id;
    private String text;
    private String lang;
    private User user;

    @SerializedName("retweet_count")
    private int retweetCount;

    @SerializedName("favorite_count")
    private int favoriteCount;

    public Tweet(long id, String text, String lang, User user, int retweetCount, int favoriteCount) {
        this.id = id;
        this.text = text;
        this.lang = lang;
        this.user = user;
        this.retweetCount = retweetCount;
        this.favoriteCount = favoriteCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    @Override
    public String toString() {
        if (user != null) {
            return "{" +
                    "\"id\":" + id +
                    "," + "\"text\":\"" + text + '\"' +
                    "," + "\"lang\":\"" + lang + '\"' +
                    "," + "\"retweetCount\":" + retweetCount +
                    "," + "\"favoriteCount\":" + favoriteCount +
                    "," + "\"user_Id\":" + user.getId() +
                    "," + "\"user_Name\":" + "\"" + user.getName() + "\"" +
                    "," + "\"user_ScreenName\":" + "\"" + user.getScreenName() + "\"" +
                    "," + "\"user_Location\":" + "\"" + user.getLocation() + "\"" +
                    "," + "\"user_FollowersCount\":" + user.getFollowersCount() +
                    '}';
        }else {
            return "{" +
                    "\"id\":" + id +
                    "," + "\"text\":\"" + text + '\"' +
                    "," + "\"lang\":\"" + lang + '\"' +
                    "," + "\"retweetCount\":" + retweetCount +
                    "," + "\"favoriteCount\":" + favoriteCount +
                    "," + "\"user_Id\":" + null +
                    "," + "\"user_Name\":" + "\"" + null + "\"" +
                    "," + "\"user_ScreenName\":" + "\"" + null + "\"" +
                    "," + "\"user_Location\":" + "\"" + null + "\"" +
                    "," + "\"user_FollowersCount\":" + null +
                    '}';
        }
    }
}
